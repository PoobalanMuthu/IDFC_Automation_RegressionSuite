package com.mop.qa.testbase;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import com.gargoylesoftware.htmlunit.javascript.host.html.Image;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.PNGDecodeParam;
import com.sun.media.jai.codec.SeekableStream;

/**
 * 
 * 
 * @author SAT Team
 *
 */
public class ScreenShotUtils implements ImageObserver 
{

	private static final Color BKGROUND_COLOR = Color.WHITE;
	private static boolean imageLoaded = false;

	private static BufferedImage matrixTransparentPixels(BufferedImage originalBufferedImage, Color fillColor)
			throws InterruptedException {
		int w = originalBufferedImage.getWidth();
		int h = originalBufferedImage.getHeight();
		BufferedImage newGeneratedBufferedImage;
		if (originalBufferedImage.getType() == BufferedImage.TYPE_4BYTE_ABGR) {
			newGeneratedBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		} else {
			newGeneratedBufferedImage = new BufferedImage(w, h, originalBufferedImage.getType());
		}
		// clone the original image
		Graphics2D graphics2D = null;
		try {
			graphics2D = newGeneratedBufferedImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
					RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			final boolean isDrawingSuccess = graphics2D.drawImage(originalBufferedImage, 0, 0, w, h, fillColor, null);
			if (!isDrawingSuccess) {
				while (!imageLoaded) {
					System.err.println("DRAWING IMAGE, PLEASE WAITING...");
					Thread.sleep(10000);
				}
			}
		} finally {
			if (graphics2D != null) {
				graphics2D.dispose();
			}
		}

		return newGeneratedBufferedImage;
	}

	public static void reduceScreenShotQuality(File screenshotSource, String location, String fileName,
			String fileExtension) throws Exception
	{

		float quality = 0.4f;

		ImageWriter writer = createImageWriter();

		ImageWriteParam iwp = writer.getDefaultWriteParam();

		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

		InputStream inputStream = new FileInputStream(screenshotSource);

		FileImageOutputStream output = getOutputStream(location, fileName, fileExtension);

		BufferedImage originalImage = ImageIO.read(inputStream);
		BufferedImage newBufferedImage;

		newBufferedImage = matrixTransparentPixels(originalImage, BKGROUND_COLOR);

			iwp.setCompressionQuality(quality);

			writeImage(writer, newBufferedImage, output, iwp);

			File fileOut2 = new File(location + fileName );
			
		

		inputStream.close();
		originalImage.flush();
		newBufferedImage.flush();
		writer.dispose();
	
}

	private static void writeImage(ImageWriter writer, BufferedImage bufferedImage, FileImageOutputStream output,
			ImageWriteParam iwp) {
		try {
			IIOImage image = new IIOImage(bufferedImage, null, null);
			writer.setOutput(output);

			if (iwp != null) {
				writer.write(null, image, iwp);
			} else {
				writer.write(image);
			}
			output.close();
		} catch (IOException e) {
			System.err.println("writeImage: IOException- " + e.getMessage());
		} finally {
			//System.err.println("End writeImage");
		}

	}

	private static FileImageOutputStream getOutputStream(String location, String fileName, String fileExtension)
			throws FileNotFoundException, IOException {
		File fileOut = new File(location + fileName);
		if (fileOut.exists()) {
			fileOut.delete();
		}
		FileImageOutputStream output = new FileImageOutputStream(fileOut);

		return output;
	}

	private static ImageWriter createImageWriter() {
		Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) iter.next();
		return writer;
	}

	public static void writeImageToLocation(File filePng, String location, String fileName, String extension)
			throws IOException {
		ImageWriter writer = createImageWriter();

		ImageWriteParam iwp = writer.getDefaultWriteParam();

		SeekableStream seekableStream = new FileSeekableStream(filePng);
		PNGDecodeParam pngParams = new PNGDecodeParam();
		ImageDecoder dec = ImageCodec.createImageDecoder("png", seekableStream, pngParams);
		RenderedImage pngImage = dec.decodeAsRenderedImage();

		BufferedImage newImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		newImage.createGraphics().drawImage(renderedImageToBufferedImage(pngImage), 0, 0, Color.BLACK, null);

		FileImageOutputStream output = getOutputStream(location, fileName, extension);
		writeImage(writer, newImage, output, iwp);

		newImage.flush();
		seekableStream.close();
		writer.dispose();
	}

	private static BufferedImage renderedImageToBufferedImage(RenderedImage img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		ColorModel cm = img.getColorModel();
		int width = img.getWidth();
		int height = img.getHeight();
		WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		Hashtable properties = new Hashtable();
		String[] keys = img.getPropertyNames();
		if (keys != null) {
			for (String key : keys) {
				properties.put(key, img.getProperty(key));
			}
		}
		BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
		img.copyData(raster);
		return result;
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		if (infoflags != ALLBITS) {
			// Image has not finished loading!
			// Return true to tell the image loading thread to keep drawing until image
			// fully loads.
			return true;
		} else {
			imageLoaded = true;
			return false;
		}
	}

	@Override
	public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}