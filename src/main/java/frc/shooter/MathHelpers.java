package frc.shooter;

public class MathHelpers {

	public static double getShootOffset(double cameraToRobotAngle, double shootingSpeed, double robotSpeed) {
		double shootOffset = 0;
		double thirdSideLength = 0;
		thirdSideLength = Math.sqrt(Math.pow(shootingSpeed, 2) + Math.pow(robotSpeed, 2)
        - 2 * shootingSpeed * robotSpeed * Math.cos(Math.toRadians(cameraToRobotAngle)));
        //finds length of the third side 
        if (cameraToRobotAngle == 0) {
            shootOffset = 0; //sets offset to 0 if the robot is facing the target
        } else {
           shootOffset = Math.toDegrees(Math.acos((Math.pow(thirdSideLength,2) + Math.pow(shootingSpeed,2) -
                   Math.pow(robotSpeed,2))/(2*thirdSideLength*shootingSpeed)));
       }
		return shootOffset;
	}

	public static double getShootingSpeed(double distanceFromTarget) {
		return 0;
	}

}
