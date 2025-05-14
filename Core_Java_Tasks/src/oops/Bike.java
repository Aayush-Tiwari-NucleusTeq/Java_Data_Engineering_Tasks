package oops;

public class Bike extends Vehicle {

	public Bike(String brand) {
		super(brand);
	}

	@Override
	public void accelerate() {
		setSpeed(getSpeed() + 10);
		System.out.println(getBrand() + " is accelerating with speed - " + getSpeed());
	}

}
