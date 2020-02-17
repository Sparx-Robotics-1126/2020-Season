package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.ClimingSensors;
import frc.robot.IO;

public class Climbing extends Subsystem{
	
	public CANSparkMax winch;
	public TalonSRX scissorlift;
	private ClimbingCommand extendingCommand;
	private ClimbingCommand winchingCommand;

	private ClimingSensors c; 


	
	
	public Climbing() {

		winch  = new CANSparkMax(IO.CLIMBING_WINCH_MOTOR,MotorType.kBrushless);
		scissorlift = new TalonSRX(IO.CLIMBING_SCISSORLIFT_MOTOR);
		extendingCommand = null;
		winchingCommand = null;
		
		c = new ClimingSensors(winch);
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
			winch.set(output.getOutput());
			if(output.isFinished()) {
				winchingCommand = null;
				winch.set(0);
			}
		}
		winch.set(-.15);
		SmartDashboard.putBoolean("Limit", c.isTouchingBar());
		SmartDashboard.putNumber("lead screw distance", c.getLeadScrewDistance());
		SmartDashboard.putNumber("winch distance", c.getWinchDistance());

	}

	@Override
	public boolean isDone() {
		return extendingCommand == null && winchingCommand == null;
	}
	
}
