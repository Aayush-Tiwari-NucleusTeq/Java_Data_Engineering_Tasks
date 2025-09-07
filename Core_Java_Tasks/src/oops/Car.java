package oops;

public class Car extends Vehicle {

	public Car(String brand) {
		super(brand);
	}

	@Override
	public void accelerate() {
		setSpeed(getSpeed() + 20);
		System.out.println(getBrand() + " is accelerating with the speed " + getSpeed());
	}

}
