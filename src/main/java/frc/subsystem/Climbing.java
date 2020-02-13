package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.IO;

public class Climbing extends Subsystem{
	
	public TalonSRX winch;
	public TalonSRX Scissorlift;
	
	
	public Climbing(TalonSRX winch, TalonSRX Scissorlift) {
		winch  = new TalonSRX(IO.CLIMBING_WINCH_MOTOR);
		Scissorlift = new TalonSRX(IO.CLIMBING_SCISSORLIFT_MOTOR);
		
	}


	@Override
	void execute() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

}
