package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class TurnRight extends DrivesCommand{
	
	private final double SPEED;
	private final double ANGLE;
	
	public TurnRight(DrivesSensorInterface sensor, double speed, double angle) {
		
		super (sensor);
		this.SPEED = speed;
		this.ANGLE = angle;
		
	}

	@Override
	public DrivesOutput execute() {
		if(sensors.getGyroAngle() >= ANGLE) {
			return new DrivesOutput(0, 0, true);
		}else {
		// TODO Auto-generated method stub
			return new DrivesOutput(SPEED, -SPEED);
	}

}
}