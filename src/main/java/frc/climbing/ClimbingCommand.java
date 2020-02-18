package frc.climbing;

public abstract class ClimbingCommand {
	
	protected ClimbingSensorsInterface sensors;
	
	public ClimbingCommand(ClimbingSensorsInterface sensors) {
		this.sensors = sensors;
	}
	
	public abstract ClimbingOutput execute();
	
}
