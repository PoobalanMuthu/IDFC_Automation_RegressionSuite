package workAround;

public class Check {

	public static void getRandomIntegerBetweenRange(){
		int d = (int)(Math.random()*((30-01)+1))+01;

		String videoNumber = Integer.toString(d);
		System.out.println("Video number is " + videoNumber);
	}
	
	public static void main(String[] args) {
		getRandomIntegerBetweenRange();

	}

}
