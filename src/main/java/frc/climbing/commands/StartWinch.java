package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;

public class StartWinch extends ClimbingCommand {
	private double wantedDistance;
	private double currentDistance;


	public StartWinch(ClimbingSensorsInterface sensors, double distance) {
		super(sensors);
		this.currentDistance = sensors.getWinchDistance();
		this.wantedDistance = distance;
	}

		public ClimbingOutput execute() {
			currentDistance = sensors.getWinchDistance();
			if(currentDistance >= wantedDistance) {
				return new ClimbingOutput(0.0,true);
			}
			return new ClimbingOutput(1.0,false);
		}

}
