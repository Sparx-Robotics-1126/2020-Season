package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.acq.AcqCommand;
import frc.acq.IntakeRollers;
import frc.robot.IO;

public class Acquisitions extends Subsystem{

	private AcqCommand acqCommand = new IntakeRollers();
	
	TalonSRX motor = new TalonSRX(IO.ACQMOTOR);
	
	@Override
	void execute() {
		if(acqCommand != null) {
			double output = acqCommand.execute();
			motor.set(ControlMode.PercentOutput, -output);
			acqCommand = null;
		}
	}

	@Override
	public boolean isDone() {
		return acqCommand == null;
	}
	
}
