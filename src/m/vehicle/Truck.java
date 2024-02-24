package m.vehicle;

public class Truck extends Vehicle  {

	public Truck() {
		// TODO Auto-generated constructor stub
	}

	public Truck(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toCsvString() {
		// TODO Auto-generated method stub
		return super.toCsvString();
	}

}
