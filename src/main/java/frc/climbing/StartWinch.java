package frc.climbing;

public class StartWinch extends ClimbingCommand {
	private double wantedDistance;
	private double currentDistance;


	public StartWinch(ClimbingSensorsInterface sensors, double distance) {
		super(sensors);
		this.currentDistance = sensors.getWinchDistance();
		this.wantedDistance = distance;
	}

		public ClimbingOutput execute() {
			if(currentDistance >= wantedDistance) {
				return new ClimbingOutput(0.0,true);
			}
			return new ClimbingOutput(0.3,false);
		}

}
