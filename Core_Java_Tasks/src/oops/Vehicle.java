package oops;

public abstract class Vehicle implements IVehicle {

	private String brand;
	private String price;
	private int speed;
	
	
	public Vehicle(String brand, String price) {
		this.brand = brand;
		this.price = price;
	}
	
	public Vehicle(String brand) {
		this.brand = brand;
		this.speed = 0;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void start() {
		System.out.println(brand + " is starting ...");
	}
	
	@Override
	public void stop() {	
		System.out.println(brand + " is stopping ...");
	}
	
//	@Override
//	public abstract void accelerate();
}
