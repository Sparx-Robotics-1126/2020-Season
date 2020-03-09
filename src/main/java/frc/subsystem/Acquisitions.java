package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.acq.AcqCommand;
import frc.acq.commands.StopRollers;
import frc.health.HealthCheck;
import frc.health.HealthReport;
import frc.acq.commands.EjectRollers;
import frc.acq.commands.IntakeRollers;
import frc.robot.IO;

public class Acquisitions extends Subsystem {

	private AcqCommand acqCommand;

	private TalonSRX motor;

	public Acquisitions() {
		motor = new TalonSRX(IO.ACQ_MOTOR);
	}

	@Override
	void execute() {
		if (acqCommand != null) {
			double output = acqCommand.execute();
			motor.set(ControlMode.PercentOutput, -output);
			acqCommand = null;
		}
	}

	public void stopRollers() {
		acqCommand = new StopRollers();
	}

	public void ejectRollers() {
		acqCommand = new EjectRollers();
	}

	public void startIntake() {
		acqCommand = new IntakeRollers();
	}

	@Override
	public boolean isDone() {
		return acqCommand == null;
	}

	@Override
	public HealthReport getHealthCheck() {
		return new HealthReport();
	}
}
