package frc.sensors;

import java.lang.Math;

public class Limelight {
	
	public double getDistanceFromTarget() {
		
		final double a1 = 75;
		final double a2 = getAngleFromTarget();
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
