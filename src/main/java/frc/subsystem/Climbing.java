package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.robot.IO;

public class Climbing extends Subsystem{
	
	public TalonSRX winch;
	public TalonSRX scissorlift;
	private ClimbingCommand extendingCommand;
	private ClimbingCommand winchingCommand;
	
	
	public Climbing(TalonSRX winch, TalonSRX Scissorlift) {
		winch  = new TalonSRX(IO.CLIMBING_WINCH_MOTOR);
		scissorlift = new TalonSRX(IO.CLIMBING_SCISSORLIFT_MOTOR);
		extendingCommand = null;
		winchingCommand = null;
	}
	
	@Override
	void execute() {
		if(extendingCommand != null) {
			ClimbingOutput output = extendingCommand.execute();
			scissorlift.set(ControlMode.PercentOutput, output.getOutput());
			if(output.isFinished()) {
				extendingCommand = null;
				scissorlift.set(ControlMode.PercentOutput, 0);
			}
		}
		if(winchingCommand != null) {
			ClimbingOutput output = winchingCommand.execute();
			winch.set(ControlMode.PercentOutput, output.getOutput());
			if(output.isFinished()) {
				winchingCommand = null;
				winch.set(ControlMode.PercentOutput, 0);
			}
		}
	}

	@Override
	public boolean isDone() {
		return extendingCommand == null && winchingCommand == null;
	}
	
>>>>>>> refs/remotes/origin/master
}
