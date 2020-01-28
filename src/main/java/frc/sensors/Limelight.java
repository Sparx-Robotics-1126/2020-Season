package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	private NetworkTable table;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	
	public double getDistanceFromTarget() {
		return 0;
	}
	
	public double getAngleFromTarget() {
		return 0;
	}
	
	public void enable(boolean enable) {
		
	}
	
}
