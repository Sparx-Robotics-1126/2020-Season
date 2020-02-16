package frc.climbing;

public abstract class ClimbingCommand {
	
	private ClimbingSensorsInterface sensors;
	
	public ClimbingCommand(ClimbingSensorsInterface sensors) {
		this.sensors = sensors;
	}
	
	public abstract ClimbingOutput execute();
	
}
