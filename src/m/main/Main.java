package m.main;
import m.vehicleManager.VehicleManager;
public class Main {

	public static void main(String[] args) {
		// Instantiate vehicleManager, perform operations based on the requirements.
		VehicleManager vehicleManager = new VehicleManager();

		// Read vehicle data from the vehcileList.csv file and initialize objects.
		vehicleManager.initializeStock("File\\vehicleList.csv");

		
		
		// Display all vehicle information.
		vehicleManager.displayAllVehicleInformation();
		
		//Display all Car information
		vehicleManager.displayAllCarInformation();
		
		// Display all motorbike information.
		vehicleManager.displayAllMotorBikeInformation();

	}
}
