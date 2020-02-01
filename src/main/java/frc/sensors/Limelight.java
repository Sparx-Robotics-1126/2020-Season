package frc.sensors;

import java.lang.Math;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	
	
	final double a1 = 14.5;
	final double h1 = 59.5;
	final double h2 = 98.25; // 83.25  89.5

	private NetworkTable table;
	NetworkTableEntry ty;
	
	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
		ty = table.getEntry("ty");
	}

	public double getDistanceFromTarget() {
		double a2 = ty.getDouble(0);
		System.out.println("A2: "+a2);
		return (h2-h1+15) / Math.tan(Math.toRadians(a1+a2));
	}
	
	public double getAngleFromTarget() {
		return 0;
	}
	
	public void turnLimelightOn() {
		
	}
	
	public void turnLimelightOff() {
		
	}
	
}
