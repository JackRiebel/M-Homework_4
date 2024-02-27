package m.vehicleManager;

import m.vehicle.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VehicleManager {
    private String vehicleFilePath;
    private ArrayList<Vehicle> vehicleList;

//Global Variables that made the initializeStock method run smoother.
    String testline;
    String splitBy = ","; 
public boolean initializeStock(String vehicleFilePath) 
{
	//List<List<String>> records = new ArrayList<>();
	try {
	BufferedReader fileIn = new BufferedReader(new FileReader(vehicleFilePath));
	//Eliminate Header Line
	fileIn.readLine();

		while((testline = fileIn.readLine())!=null)
		{
			String [] input = testline.split(splitBy);
		
			String type = input[0];
				//System.out.println("type" + type);
			String brand = input[1];
			 	//System.out.println("brand" + brand);
			 String make = input[2];
			 	//System.out.println("make" + make);
			long modelYear = Long.parseLong(input[3]);
			 	//System.out.println("year" + modelYear);
			 double price = Double.parseDouble(input[4]);
			 	//System.out.println("price" + price);
			VehicleColor color = VehicleColor.valueOf(input[5]);
			 	//System.out.println("color" + color);
			 FuelType fueltype = FuelType.valueOf(input[6]);
			 	//System.out.println("fueltype" + fueltype);
			 double mileage = Double.parseDouble(input[7]);
				//System.out.println("mileage" + mileage);
			 double mass = Double.parseDouble(input[8]);
			 	//System.out.println("mass" + mass);
			int cylinders = Integer.parseInt(input[9]);
			 	//System.out.println("cylinders" + cylinders);
			 double gasTankCapacity = Double.parseDouble(input[10]);
			 	//System.out.println("gasTankCapacity" + gasTankCapacity);
			 StartMechanism startType = StartMechanism.valueOf(input[11]);
			 	//System.out.println("startType" + startType);
			 
			 if(type == "Car") 
			 {
				 Vehicle vehicle = new Car(brand,make,modelYear,price,color,fueltype,mileage,mass,cylinders,gasTankCapacity,startType);
				 vehicleList.add(vehicle);
			 }
			 
			 if(type == "MotorBike") 
			 {
				 Vehicle vehicle = new MotorBike(brand,make,modelYear,price,color,fueltype,mileage,mass,cylinders,gasTankCapacity,startType);
			 	 vehicleList.add(vehicle);
			 }
			 
			 if(type == "SUV") 
			 {
				 Vehicle vehicle = new SUV(brand,make,modelYear,price,color,fueltype,mileage,mass,cylinders,gasTankCapacity,startType);
				 addVehicle(vehicle);
			 }
			 
			 if(type == "Truck")
			 {
				 Vehicle vehicle = new Truck(brand,make,modelYear,price,color,fueltype,mileage,mass,cylinders,gasTankCapacity,startType);
				 	addVehicle(vehicle);
			 }
			}
		fileIn.close();
			 
	}		
		catch(Exception e) 
		{
			System.out.println("Error: Data not found");
			return false;
		}
	return true;
}

public void displayAllCarInformation() {
	boolean found = false;
	for (Vehicle v : vehicleList) {
		displayVehicleInformation(v);
		found = true;
	}
	if (!found) {
		System.out.println("No vehicle in inventory");
	}
}


public void displayAllTruckInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof Truck) {
           displayVehicleInformation(v);
           found = true;
        }
    }
    if (!found) {
        System.out.println("No trucks found in the inventory.");
    }
}

public void displayAllSUVInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof SUV) {
            displayVehicleInformation(v);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No SUVs found in the inventory.");
    }
}

public void displayAllMotorBikeInformation() {
    boolean found = false;
    for (Vehicle v : vehicleList) {
        if (v instanceof MotorBike) {
            displayVehicleInformation(v);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No motorbikes found in the inventory.");
    }
}

public void displayVehicleInformation(Vehicle v) {
    if (vehicleList.contains(v)) {
    // Assuming Vehicle class and its subclasses have a properly overridden toString method
        System.out.println(v.toString());
    } else {
        System.out.println("Vehicle not found in the inventory.");
    }
}

public void displayAllVehicleInformation() {
    if (vehicleList.isEmpty()) {
        System.out.println("The vehicle list is empty.");
    } else {
        for (Vehicle v : vehicleList) {
            displayVehicleInformation(v);
        }
    }
}

public boolean removeVehicle(Vehicle vehicle) {
    return vehicleList.remove(vehicle);
}

public boolean addVehicle(Vehicle vehicle) {
    return vehicleList.add(vehicle);
}

private boolean isVehicleType(Vehicle v, Class clazz) {
    return clazz.isInstance(v);
}

public int getNumberOfVehiclesByType(Class clazz) {
    int count = 0;
    for (Vehicle v : vehicleList) {
        if (isVehicleType(v, clazz)) {
            count++;
        }
    }
    return count;
}


//getVehicleWithHighestMaintencanceCost
public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
	
	//Make Sure List isn't empty
	if (vehicleList.isEmpty()) {
		return null;
	}
	//Variables to Track Maintenance Cost
	Vehicle highestMaintenanceCostVehicle = null;
	double highestMaintenanceCost = Double.MIN_VALUE;
	
	//Array to Store Vehicles if Their MaintenanceCost is the same
	ArrayList<Vehicle> maintenanceCostList = new ArrayList<>();
	
	//Find the Highest Maintenance Cost
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateMaintenanceCost(distance);
		if (maintenanceCost == highestMaintenanceCost) {
			//Store Vehicle in maintenanceCostList
			maintenanceCostList.add(vehicle);
		} else if (maintenanceCost > highestMaintenanceCost) {
			//Store Vehicle if maintenanceCost is > Previous Vehicle
			highestMaintenanceCostVehicle = vehicle;
		}
		
		//Select randomVehicle if maintenanceCostList != null
		if (maintenanceCostList != null && maintenanceCostList.size() > 1) {
			//Create the random variable if needed
			Random randomVehicle = new Random();
			//Assign highestMaintenanceCostVehicle a random vehicle
			highestMaintenanceCostVehicle = maintenanceCostList.get(randomVehicle.nextInt(maintenanceCostList.size()));
		}
		}
	//Return highestMaintenanceVehicle
	return highestMaintenanceCostVehicle;	
}

//getVehicleWithLowestMaintencanceCost
public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
	
	//Make Sure List isn't empty
	if (vehicleList.isEmpty()) {
		return null;
	}
	//Variables to Track Maintenance Cost
	Vehicle lowestMaintenanceCostVehicle = null;
	double lowestMaintenanceCost = Double.MIN_VALUE;
	
	//Array to Store Vehicles if Their MaintenanceCost is the same
	ArrayList<Vehicle> maintenanceCostList = new ArrayList<>();
	
	//Find the Lowest Maintenance Cost
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateMaintenanceCost(distance);
		if (maintenanceCost == lowestMaintenanceCost) {
			//Store Vehicle in maintenanceCostList
			maintenanceCostList.add(vehicle);
		} else if (maintenanceCost < lowestMaintenanceCost) {
			//Store Vehicle if maintenanceCost is > Previous Vehicle
			lowestMaintenanceCostVehicle = vehicle;
		}
		
		//Select randomVehicle if maintenanceCostList != null
		if (maintenanceCostList != null && maintenanceCostList.size() > 1) {
			//Create the random variable if needed
			Random randomVehicle = new Random();
			//Assign lowestMaintenanceCostVehicle a random vehicle
			lowestMaintenanceCostVehicle = maintenanceCostList.get(randomVehicle.nextInt(maintenanceCostList.size()));
		}
	}
	//Return lowestMaintenanceCost
	return lowestMaintenanceCostVehicle;
}

public boolean saveVehicleList() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(vehicleFilePath))) {
        for (Vehicle v : vehicleList) {
        	bw.write(v.toCsvString()); // Assuming a method toCsvString() that converts vehicle data to CSV format
            bw.newLine();
        }
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
public Vehicle getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice) {
	//Make Sure List isn't empty
	if (vehicleList.isEmpty()) {
		return null;
	}
	//Variables to Track Maintenance Cost
	Vehicle lowestFuelEfficientVehicle = null;
	double lowestFuelEfficiency = Double.MAX_VALUE;
	
	//Array to Store Vehicles if Their MaintenanceCost is the same
	ArrayList<Vehicle> fuelEfficiencyList = new ArrayList<>();
	
	//Find the Highest Maintenance Cost
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateFuelEfficiency(distance, fuelPrice);
		if (maintenanceCost < lowestFuelEfficiency) {
			//Store Vehicle if maintenanceCost is > Previous Vehicle
			lowestFuelEfficiency = maintenanceCost;
			//highestFuelEfficientVehicle = vehicle;
		}
	}
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateFuelEfficiency(distance, fuelPrice);
		if (maintenanceCost == lowestFuelEfficiency) {
		//Store Vehicle in maintenanceCostList
		fuelEfficiencyList.add(vehicle);
	}
		
		
		//Select randomVehicle if maintenanceCostList != null
		if (fuelEfficiencyList != null && fuelEfficiencyList.size() > 1) {
			Random rand = new Random();
			int index = rand.nextInt(fuelEfficiencyList.size());
			//Assign highestMaintenanceCostVehicle a random vehicle
			lowestFuelEfficientVehicle = fuelEfficiencyList.get(index);
		}
		}
	//Return highestMaintenanceVehicle
	return lowestFuelEfficientVehicle;	
}
public Vehicle getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice) {
	
	//Make Sure List isn't empty
	if (vehicleList.isEmpty()) {
		return null;
	}
	//Variables to Track Maintenance Cost
	Vehicle highestFuelEfficientVehicle = null;
	double highestFuelEfficiency = Double.MIN_VALUE;
	
	//Array to Store Vehicles if Their MaintenanceCost is the same
	ArrayList<Vehicle> fuelEfficiencyList = new ArrayList<>();
	
	//Find the Highest Maintenance Cost
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateFuelEfficiency(distance, fuelPrice);
		if (maintenanceCost > highestFuelEfficiency) {
			//Store Vehicle if maintenanceCost is > Previous Vehicle
			highestFuelEfficiency = maintenanceCost;
			//highestFuelEfficientVehicle = vehicle;
		}
	}
	for (Vehicle vehicle : vehicleList) {
		double maintenanceCost = vehicle.calculateFuelEfficiency(distance, fuelPrice);
		if (maintenanceCost == highestFuelEfficiency) {
		//Store Vehicle in maintenanceCostList
		fuelEfficiencyList.add(vehicle);
	}
		
		
		//Select randomVehicle if maintenanceCostList != null
		if (fuelEfficiencyList != null && fuelEfficiencyList.size() > 1) {
			//Create the random variable if needed
			Random rand = new Random();
			int index = rand.nextInt(fuelEfficiencyList.size());
			//Assign highestMaintenanceCostVehicle a random vehicle
			highestFuelEfficientVehicle = fuelEfficiencyList.get(index);
		}
		}
	//Return highestMaintenanceVehicle
	return highestFuelEfficientVehicle;	
}


//getAverageFuelEfficiencyOfSUVs
public double getAverageFuelEfficiencyOfSUVs(double distance,double fuelPrice) {
	
	//Check to make sure Vehicle List is Empty
	if (vehicleList.isEmpty()) {
		return -1.0;
	}
	
	//Create Variables
	int numberOfSUVs = 0;
	double combinedEfficiency = 0.0;
	double averageEfficiency = 0.0;
	
	//Count number of SUVs and sum their fuel efficiency
	for (Vehicle v : vehicleList) {
        if (v instanceof SUV) {
            numberOfSUVs++;
            combinedEfficiency = v.calculateFuelEfficiency(distance, fuelPrice);
        }
	}
	
	//Calculate the average efficiency
	averageEfficiency = combinedEfficiency / numberOfSUVs;
	
	//Return either -1.0 or the average efficiency
	if (numberOfSUVs == 0) {
		return -1.0;
	} else {
		return averageEfficiency;
	}
}

}
