package frc.climbing;

import frc.health.HealthCheck;

public abstract class ClimbingCommand extends HealthCheck{
	
	protected ClimbingSensorsInterface sensors;
	
	public ClimbingCommand(ClimbingSensorsInterface sensors) {
		this.sensors = sensors;
	}
	
	public abstract ClimbingOutput execute();
	
}
