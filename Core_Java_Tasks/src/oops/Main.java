package oops;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main class under OOPs package");
		Vehicle i20 = new Car("Hyundai i20");
		i20.setSpeed(85);
		i20.start();
		i20.stop();
		i20.accelerate();
		
		Vehicle shine = new Bike("Hero Shine");
		shine.setSpeed(50);
		shine.stop();
		shine.start();
		shine.accelerate();
		shine.stop();
	}

}
