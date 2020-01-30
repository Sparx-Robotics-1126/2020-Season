package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	private NetworkTable table;
	NetworkTableEntry tx;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
		tx = table.getEntry("tx");
	}
	
	public double getDistanceFromTarget() {
		return 0;
	}
	
	public double getAngleFromTarget() {
		double x = tx.getDouble(0);
		return x;
	}
	
	public boolean getLock() {
		return false;
	}
	
	public void enable(boolean enable) {
		
	}
	
}
