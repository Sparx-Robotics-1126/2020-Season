package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.robot.IO;

public class Climbing extends Subsystem{

	private ClimbingCommand extendingCommand;
	private ClimbingCommand winchingCommand;
	
	@Override
	void execute() {
		if(extendingCommand != null) {
			ClimbingOutput output = extendingCommand.execute();
			//Set extending Motor
			if(output.isFinished()) {
				extendingCommand = null;
			}
		}
		if(winchingCommand != null) {
			ClimbingOutput output = winchingCommand.execute();
			//Set winching motor
			if(output.isFinished()) {
				winchingCommand = null;
			}
		}
	}

	@Override
	public boolean isDone() {
		return extendingCommand == null && winchingCommand == null;
	}
	
}
