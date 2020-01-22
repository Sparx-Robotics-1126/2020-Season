package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	private NetworkTable table;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	
	
	public double getDistanceFromTarget() {
		return 0;
	}
	
	public double getAngleFromTarget() {
		double x = tx.getDouble(0.0);
		double y = ty.getDouble(0.0);
		return x + y;
		//System.out.println("Offset from target (x, y): " + Double.valueOf(x) + ", " + Double.valueOf(y)); //for testing
	}
	
	public void enable(boolean enable) {
		
	}
	
}
