package frc.climbing.command;

	import frc.climbing.ClimbingCommand;
	import frc.climbing.ClimbingOutput;
	import frc.climbing.ClimbingSensorsInterface;

	public class ClimbingRetract extends ClimbingCommand {

		public ClimbingRetract(ClimbingSensorsInterface sensors) {
			super(sensors);
		}
		
		@Override
		public ClimbingOutput execute() {
			
			if (sensors.getLeadScrewDistance() >= 0.25) {
				return new ClimbingOutput(-1);
			}
			return null;
		}

	}