package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {
	
	
	final double CAMERA_ANGLE = 13.35;
	final double ROBOT_HEIGHT = 37.5;
	final double TARGET_HEIGHT = 90; // 83.25  89.5

	private NetworkTable table;
	NetworkTableEntry tx;
	NetworkTableEntry tv;
	NetworkTableEntry ty;
	
	public Limelight() {
		// SmartDashboard.putNumber("Camera Angle", CAMERA_ANGLE);
		table = NetworkTableInstance.getDefault().getTable("limelight");
		tx = table.getEntry("tx");
		tv = table.getEntry("tv"); //tells whether or not a target is present; 1 for a target, 0 for none.
		ty = table.getEntry("ty");
	}
	
	public double getDistanceFromTarget() {
		double a2 = ty.getDouble(0);
		double distance = (TARGET_HEIGHT-ROBOT_HEIGHT) / Math.tan(Math.toRadians(CAMERA_ANGLE+a2));
		return distance;
	}
	
	public double getAngleFromTarget() {
		double x = tx.getDouble(0);
		return x;
	}
	
	public boolean getLock() {
		if (tv.getDouble(0) > 0) {
			return true;
		}
		return false;
	}
	public void enable(boolean enable) {
		
	}
	
}
