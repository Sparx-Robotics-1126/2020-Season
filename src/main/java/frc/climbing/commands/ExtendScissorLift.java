package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.health.HealthReport;

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

	@Override
	public HealthReport checkHealth(){		
		if(sensors.getLeadScrewDistance()<0){
			return new HealthReport(true,"Sensor going backward");
		}
		else if(sensors.getLeadScrewDistance()>.2){
			return new HealthReport(false,"Lead screw encoder went "+sensors.getLeadScrewDistance());
		}
		return new HealthReport(true,"Lead screw went nowhere");	
	}
}