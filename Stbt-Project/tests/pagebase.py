import requests
import datetime
import time
import os
import csv
import cv2
import numpy
import stbt
from time import sleep
from stbt import match, match_text, MatchParameters, press, press_until_match, wait_for_match, wait_for_motion, wait_until, draw_text, ocr,OcrMode, get_frame, Region

def key_press(key,Keyname):
    try:      
       press(key)
       
       sleep(2)
       res = requests.get('http://10.244.7.52/api/v1/device/screenshot.png')
       fh = open("imageToSave-"+str(datetime.datetime.now().strftime('%Y-%m-%d %H-%M-%S'))+".png" , "wb")
       fh.write(res.content)
       fh.close() 
       print "Pass/ The Icon "+Keyname+" pressed successfully. screenshot :"+fh.name
    except Exception,e:
        print  "Fail/ Error While pressing The Key. Exception/  %s " % e.args
        raise e
def passMessageWith_Screenshots(message):
    res = requests.get('http://10.244.7.52/api/v1/device/screenshot.png')
    fh = open("imageToSave-"+str(datetime.datetime.now().strftime('%Y-%m-%d %H-%M-%S'))+".png" , "wb")
    fh.write(res.content)
    fh.close() 
    print "Pass/ "+message+". screenshot :"+fh.name
def failMessageWith_Screenshots(message):
    res = requests.get('http://10.244.7.52/api/v1/device/screenshot.png')
    fh = open("imageToSave-"+str(datetime.datetime.now().strftime('%Y-%m-%d %H-%M-%S'))+".png" , "wb")
    fh.write(res.content)
    fh.close() 
    print "Fail/ "+message+". screenshot :"+fh.name
def infoMessageWith_Screenshots(message):
    res = requests.get('http://10.244.7.52/api/v1/device/screenshot.png')
    fh = open("imageToSave-"+str(datetime.datetime.now().strftime('%Y-%m-%d %H-%M-%S'))+".png" , "wb")
    fh.write(res.content)
    fh.close() 
    print "Info/ "+message+". screenshot :"+fh.name  
    
def passMessage(message):  
    print "Pass/ "+message 
def failMessage(message):  
    print "Fail/ "+message 
def infoMessage(message):  
    print "Info/ "+message     

def wait_for_element(image,elementName,timeout_secs): 
    try:
      if wait_until(lambda: match(image,match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")),timeout_secs=timeout_secs):
           passMessageWith_Screenshots(elementName+" is visible on the Screen")
          
      else:
         failMessageWith_Screenshots(elementName+" is not visible on the Screen")
         assert False, elementName+" is not visible on the Screen"   
    except Exception,e:
        failMessage(e.args)
        raise e 
def wait_for_motion_detect(timeout_secs,mask):  
    try:
       wait_for_motion(mask=mask,timeout_secs=timeout_secs)
       passMessageWith_Screenshots("Motion detected.")
    except Exception,e:
        print  "Fail/ Motion not detected after %i seconds. Exception/  %s " % timeout_secs, e.args
        raise e

def find_file(path):
    root = os.path.dirname(os.path.abspath(__file__))
    return os.path.join(root, path)
        
def Read_Data_CSV(test,colname): 
    
    with open(find_file('TestData.csv'), 'rb') as f:
        reader = csv.DictReader(f) 
        for row in reader:
            key = row.pop('Test Script Name')
            if key in test:
                value= row[colname]
                break
    return value
           
def getText(xx,yy,right1,bottom1):
    try:
       value=ocr(mode=OcrMode.PAGE_SEGMENTATION_WITHOUT_OSD,lang='eng',region=Region(x=xx, y=yy, right=right1, bottom=bottom1))
       return value
    except Exception,e:
        print  "Error While extracting the Text. Exception/  %s "%e.args
        raise e        

def match_image(image,element):
    try:
        if lambda: match(image,match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none")):           
           return  match(image,match_parameters=stbt.MatchParameters(match_method="ccoeff-normed", confirm_method="none"))
        else:
          assert False, elementName+" is not visible on the Screen"  
    except Exception,e:
        failMessage(e.args)
        raise e 
     
def find_selection_horizontal_repeat(
        frame, background, region=stbt.Region.ALL, match_threshold=0.95):

    """Find the selected menu item by looking for the specified background.

    This is an example to demonstrate that you can implement your own custom
    image processing with OpenCV.

    :param frame: An OpenCV image, as returned by `stbt.get_frame` or
        `cv2.imread`. If `None`, will pull a new frame from the system under
        test.

    :param background: The path to a 1-pixel-wide image of your
        system-under-test's menu selection/highlight.

    :param region: If specified, restrict the search to this region of the
        frame.

    :returns: A `Selection` object representing the selected item.

    Example::

        >>> frame = load_image("selftest-screenshots/roku-home.png")
        >>> find_selection_horizontal_repeat(
        ...     frame, "images/roku-menu-selection-background.png")
        Selection(region=Region(x=119, y=162, right=479, bottom=204),
                  text=u'Home')

    """

    if frame is None:
        frame = stbt.get_frame()
    frame = crop(frame, region)

    bg = load_image(background)

    correlation = 1 - cv2.matchTemplate(frame, bg, cv2.TM_SQDIFF_NORMED)
    _, max_, _, _ = cv2.minMaxLoc(correlation)
    selection_region = None
    if max_ >= match_threshold:
        # Find y coordinate
        rowmax = numpy.amax(correlation, axis=1)
        goodness = rowmax
        _, _, _, maxloc = cv2.minMaxLoc(goodness)
        y = maxloc[1]

        # Got the y position, now work out the horizontal extents
        line_uint8 = numpy.uint8(correlation[y, :]*255)
        _, binary = cv2.threshold(line_uint8, 0, 255,
                                  cv2.THRESH_OTSU | cv2.THRESH_BINARY)
        binary = binary.flatten()

        nonzeros = list(_combine_neighbouring_extents(
            list(_zeros_to_extents(binary.nonzero()[0]))))
        if nonzeros:
            widest = max(nonzeros, key=lambda a: a[1] - a[0])
            x, right = widest
            selection_region = (
                stbt.Region(x, y, right=right, bottom=y + bg.shape[0])
                .translate(x=max(0, region.x), y=max(0, region.y)))
            if selection_region.width > 10:
                # Remove the rounded corners of the selection; after subtracting
                # the background they look like single-quotes to the OCR engine.
                selection_region = selection_region.extend(x=5, right=-5)

    if not selection_region:
        stbt.debug(
            "find_selection didn't find match (%.2f) above the threshold (%.2f)"
            % (max_, match_threshold))

    return Selection(selection_region, frame, bg)


class Selection(object):
    def __init__(self, region, frame, background):
        self.region = region
        self._frame = frame
        self._background = background
        self._text = None

    def __nonzero__(self):
        return self.region is not None

    def __eq__(self, other):
        return (isinstance(other, Selection) and
                self.region == other.region and
                self.text == other.text)

    def __ne__(self, other):
        return not self.__eq__(other)

    def __str__(self):
        return "Selection(region=%r, text=%r)" % (self.region, self.text)

    def __repr__(self):
        return self.__str__()

    @property
    def text(self):
        if self._text is None and self.__nonzero__():
            diff = cv2.cvtColor(
                cv2.absdiff(
                    crop(self._frame, self.region),
                    numpy.repeat(self._background, self.region.width, 1)),
                cv2.COLOR_BGR2GRAY)
            self._text = stbt.ocr(diff)
        stbt.debug("Selection text: %s" % self._text)
        return self._text


def crop(frame, region):
    frame_region = stbt.Region(
        x=0, y=0, width=frame.shape[1], height=frame.shape[0])
    region = stbt.Region.intersect(frame_region, region)
    return frame[region.y:region.bottom, region.x:region.right]


def load_image(relative_path):
    return cv2.imread(os.path.join(os.path.dirname(__file__), relative_path))


def _zeros_to_extents(zeros):
    """
    >>> list(_zeros_to_extents([3,4,5,7,8,9,10,15,21,22]))
    [(3, 6), (7, 11), (15, 16), (21, 23)]
    >>> list(_zeros_to_extents([]))
    []
    >>> list(_zeros_to_extents([3]))
    [(3, 4)]
    """
    if len(zeros) == 0:
        return
    bottom = zeros[0]
    last = zeros[0]
    for x in zeros[1:]:
        if x == last + 1:
            last = x
            continue
        else:
            yield (bottom, last + 1)
            bottom = x
            last = x
    yield (bottom, last + 1)


def _combine_neighbouring_extents(extents, distance_px=10):
    """
    >>> list(_combine_neighbouring_extents(
    ...     [(1, 6), (7, 11), (12, 16), (18, 23)],
    ...     distance_px=1))
    [(1, 16), (18, 23)]
    """
    left, right = extents[0]
    for x in extents[1:]:
        if x[0] <= (right + distance_px):
            right = x[1]
        else:
            yield (left, right)
            left, right = x
    yield (left, right)
       
"""def key_press(key):
    try:
       press(key)
       print "The Key "+key+" pressed successfully."
    except Exception,e:
        print  "Error While pressing The Key. Exception/  %s "%e.args
        raise e        
    
def enter_text(text,TextBox):
    try:
       for char in text:
         press("Lit_%s" % char)
         print "Pass/ The Text "+text+" entered successfully in the "+TextBox+ " field"
    except Exception,e:
        print  "Fail/ Error While Entering The Text "+text+". Exception/  %s "%e.args
        raise e
    
    

def Verify_Text_In_Frame(text):
    strAd = ocr(lang='eng')
    if text in strAd:
        print text+" is detected in Screen"
    else:
        print text+" is not detected in Screen"   

def wait_for_match(image,timeout,ImageTitle): 
    try:  
       wait_for_match("images/iplayer-home-logo.png", timeout_secs=20) 
       print "Pass/ The image "+ImageTitle+" is found on the screen."
    except Exception,e:
        print  "Fail/ The image "+ImageTitle+" not detected after %i seconds. Exception/  %s " % timeout, e.args
        raise e  

def press_until_match_detects(key,image,Keyname): 
    try:  
       press_until_match(key, image)
       print "Pass/ The Key "+Keyname+" pressed successfully."
    except Exception,e:
        print  "Fail/ Error While pressing The Key "+Keyname+". Exception/  %s "%e.args
        raise e  """  

    
