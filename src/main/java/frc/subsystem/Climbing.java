package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.ClimingSensors;
import frc.robot.IO;

public class Climbing extends Subsystem{

	private ClimbingCommand extendingCommand;
	private ClimbingCommand winchingCommand;

	private ClimingSensors c = new ClimingSensors(); 


	
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

		SmartDashboard.putNumber("lead screw distance", c.getLeadScrewDistance());
		SmartDashboard.putNumber("winch distance", c.getWinchDistance());

	}

	@Override
	public boolean isDone() {
		return extendingCommand == null && winchingCommand == null;
	}
	
}
