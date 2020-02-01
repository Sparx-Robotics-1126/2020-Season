package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	private NetworkTable table;
	NetworkTableEntry tx;
	NetworkTableEntry tv;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
		tx = table.getEntry("tx");
		tv = table.getEntry("tv"); //tells whether or not a target is present; 1 for a target, 0 for none.
	}
	
	public double getDistanceFromTarget() {
		return 0;
	}
	
	public double getAngleFromTarget() {
		double x = tx.getDouble(0);
		return x;
	}
	
	public boolean getLock() {
		double lock = tv.getDouble(0); //checks if a target is present
		if (lock >= 1) { 
			return true;
		}
		return false;
	}
	
	public void enable(boolean enable) {
		
	}
	
}
