package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;

public class StopWinch extends ClimbingCommand {

	public StopWinch(ClimbingSensorsInterface sensors) {
		super(sensors);
	}

		public ClimbingOutput execute() {
            return new ClimbingOutput(0,true);
        }

}