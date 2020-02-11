package frc.shooter;

public class MathHelpers {
	
	final static double heightOfPort = 2.4955; //In meters
	

	public static double getShootOffset(double cameraToRobotAngle, double shootingSpeed, double robotSpeed) {
		return 0;
	}
	
	public static double getShootingSpeed(double distanceFromTarget) {
		return 50.7-.45*distanceFromTarget+.058*Math.pow(distanceFromTarget,2); //Using trendline
		// return Math.sqrt((distanceFromTarget*distanceFromTarget*9.8)/(2*Math.cos(35)*heightOfPort-Math.tan(35))); //Using math
	}

}
