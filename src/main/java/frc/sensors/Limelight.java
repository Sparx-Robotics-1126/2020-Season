package frc.sensors;

import java.lang.Math;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	private NetworkTable table;
	NetworkTableEntry ty;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
		ty = table.getEntry("ty");
	}

	public double getDistanceFromTarget() {
		
		final double a1 = 15;
		final double a2 = ty.getDouble(0);
		final double h1 = 36;
		final double h2 = 98.25;
		
		return (h2-h1) / Math.tan(a1+a2);
	}
	
	public double getAngleFromTarget() {
		return 0;
	}
	
	public void turnLimelightOn() {
		
	}
	
	public void turnLimelightOff() {
		
	}
	
}
