package frc.shooter;

public class MathHelpers {

	public static double getShootOffset(double cameraToRobotAngle, double shootingSpeed, double robotSpeed) {
		double shootOffset = 0;
		double thirdSideLength = 0;
		thirdSideLength = Math.sqrt(Math.pow(shootingSpeed, 2) + Math.pow(robotSpeed, 2)
		- 2*shootingSpeed*robotSpeed*Math.cos(cameraToRobotAngle));//finds length of the third side to check if you need sin or tan
		if(cameraToRobotAngle == 0) {	
			shootOffset = 0; //sets offset to 0 if the robot is facing the target
		} else {
			if (thirdSideLength > shootingSpeed) {
				shootOffset = Math.atan(robotSpeed/shootingSpeed);
			} else { 
				shootOffset = Math.asin(robotSpeed/shootingSpeed);
			}
		}
		return shootOffset;
	}

	public static double getShootingSpeed(double distanceFromTarget) {
		return 0;
	}

}
