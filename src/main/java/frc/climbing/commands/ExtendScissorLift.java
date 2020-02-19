package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;

public class ExtendScissorLift extends ClimbingCommand {
	
	private double wantedDistance;
	
	public ExtendScissorLift(ClimbingSensorsInterface sensors,double dist) {
		super(sensors);
		wantedDistance=dist;
	}
	
	public ClimbingOutput execute() {
		if(sensors.getLeadScrewDistance() >= wantedDistance) {
			return new ClimbingOutput(0,true);
		}
		if(sensors.isTouchingBar()) {
			return new ClimbingOutput(0,true );
		}else {
			return new ClimbingOutput(1);
		}
	}
}
