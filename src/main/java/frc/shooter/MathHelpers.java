package frc.shooter;

public class MathHelpers {
	
	final static double heightOfPort = 2.4955; //In meters
	

	public static double getShootOffset(double cameraToRobotAngle, double shootingSpeed, double robotSpeed) {
		return 0;
	}
	
	public static double getShootingSpeed(double distanceFromTarget) {
		if(distanceFromTarget<200){
			return 80.19593-0.1052412*distanceFromTarget+2.74896*Math.pow(10,-4)*Math.pow(distanceFromTarget,2);
		}
		return 239.24833-2.0422343*distanceFromTarget+0.0060037235*Math.pow(distanceFromTarget,2);
		// return 79.5-.173*distanceFromTarget+5.4*Math.pow(10,-4)*Math.pow(distanceFromTarget,2); //Using trendline
		// return Math.sqrt((distanceFromTarget*distanceFromTarget*9.8)/(2*Math.cos(35)*heightOfPort-Math.tan(35))); //Using math
	}

}
