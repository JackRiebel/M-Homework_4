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
	public double calculateMaintenanceCost(double distance) {
		double maintenanceCost = (distance * mass * (currentYear() - modelYear) * cylinders * 0.002);
		return maintenanceCost;
	}
	
	@Override
	public double calculateFuelEfficiency (double distance, double fuelPrice) {
		double fuelEfficiency = (cylinders * gasTankCapacity * fuelPrice / distance * 0.003);
		return fuelEfficiency;
	}
	
	@Override
	public String toCsvString() {
		// TODO Auto-generated method stub
		return super.toCsvString();
	}

}
