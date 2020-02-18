package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;

public class UnwindWinch extends ClimbingCommand {
	private double wantedDistance;
	private double currentDistance;


    //Distance should be negative
	public UnwindWinch(ClimbingSensorsInterface sensors, double distance) {
		super(sensors);
		this.wantedDistance = distance;
	}

		public ClimbingOutput execute() {
			currentDistance = sensors.getWinchDistance();
			if(currentDistance <= wantedDistance) {
				return new ClimbingOutput(0.0,true);
			}
			return new ClimbingOutput(-1.0,false);
		}

}
