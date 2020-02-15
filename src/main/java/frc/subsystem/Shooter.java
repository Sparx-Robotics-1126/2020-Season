package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.command.CenterTurretCommand;
import frc.shooter.command.ShooterSpeed;
import frc.shooter.commands.LimelightTurret;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	private TalonSRX flywheelMotorAlpha;
	private TalonSRX turretMotor;

	private final double FEED = .108;
	private final double KP = 	.2;
	private final double KI  = 	0;
	private final double KD = 	0;

	public Shooter(DrivesSensorInterface driveSensors) {		
		this.driveSensors = driveSensors;
		flywheelMotorAlpha = new TalonSRX(IO.SHOOTER_FLYWHEEL_2);
		
		TalonSRX flywheelMotorBeta = new TalonSRX(IO.SHOOTER_FLYWHEEL_1);
		flywheelMotorBeta.set(ControlMode.Follower,flywheelMotorAlpha.getDeviceID());
		flywheelMotorBeta.setInverted(true);

		flywheelMotorAlpha.configFactoryDefault();
		flywheelMotorAlpha.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,30);
		
		flywheelMotorAlpha.setSensorPhase(true);
		flywheelMotorAlpha.configNominalOutputForward(0);
		flywheelMotorAlpha.configNominalOutputReverse(0);
		flywheelMotorAlpha.configPeakOutputForward(1);
		flywheelMotorAlpha.configPeakOutputReverse(0);
		
		flywheelMotorAlpha.config_kF(0,FEED,30);
		flywheelMotorAlpha.config_kP(0,KP,30);
		flywheelMotorAlpha.config_kI(0,KI,30);
		flywheelMotorAlpha.config_kD(0,KD,30);

		this.shooterSensors = new ShooterSensors(flywheelMotorAlpha);

		turretMotor = new TalonSRX(IO.SHOOTER_TURRET_MOTOR);
		
		shooterCommand = new ShooterSpeed(shooterSensors,driveSensors);
		turretCommand = new CenterTurretCommand(shooterSensors, driveSensors);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null ) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot(); //&& turretOutput.isReadyToShoot();
			SmartDashboard.putBoolean("Ready to shoot",readyToShoot);
			flywheelMotorAlpha.set(ControlMode.Velocity, (1024/10.0)*shooterOutput.getOutputValue());
			turretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
		} 
	} 


	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

	public void startLimelightAiming(){
		shooterCommand = new LimelightTurret(shooterSensors, driveSensors);
	} 

}
