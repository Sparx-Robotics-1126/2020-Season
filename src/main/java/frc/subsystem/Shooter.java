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
	private TalonSRX flywheelMotorBeta;
	private TalonSRX turretMotor;

	private double feed = 	0;
	private double kP = 	0;
	private double kI  = 	0;
	private double kD = 	0;


	public Shooter(DrivesSensorInterface driveSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = new ShooterSensors();
		shooterCommand = new ShooterSpeed(shooterSensors,driveSensors);
		turretCommand = new CenterTurretCommand(shooterSensors, driveSensors);
		flywheelMotorAlpha = new TalonSRX(IO.SHOOTER_FLYWHEEL_2);
		flywheelMotorBeta = new TalonSRX(IO.SHOOTER_FLYWHEEL_1);
		
		SmartDashboard.putNumber("Feed",0);
		SmartDashboard.putNumber("kP",0);
		SmartDashboard.putNumber("kI",0);
		SmartDashboard.putNumber("kD",0);
		


		flywheelMotorAlpha.configFactoryDefault();
		flywheelMotorAlpha.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,30);
		
		flywheelMotorAlpha.setSensorPhase(true);
		flywheelMotorAlpha.configNominalOutputForward(0);
		flywheelMotorAlpha.configNominalOutputReverse(0);
		flywheelMotorAlpha.configPeakOutputForward(1);
		flywheelMotorAlpha.configPeakOutputReverse(0);
		
		flywheelMotorAlpha.config_kF(0,feed,30);
		flywheelMotorAlpha.config_kP(0,kP,30);
		flywheelMotorAlpha.config_kI(0,kI,30);
		flywheelMotorAlpha.config_kD(0,kD,30);

		flywheelMotorBeta.set(ControlMode.Follower,flywheelMotorAlpha.getDeviceID());
		flywheelMotorBeta.setInverted(true);
		turretMotor = new TalonSRX(IO.SHOOTER_TURRET_MOTOR);
	}
	
	@Override
	void execute() {
		if(shooterCommand != null ) {
			feed = SmartDashboard.getNumber("Feed",0);
			kP = SmartDashboard.getNumber("kP",0);
			kI = SmartDashboard.getNumber("kI",0);
			kD = SmartDashboard.getNumber("kD",0);

			flywheelMotorAlpha.config_kF(0,feed,30);
			flywheelMotorAlpha.config_kP(0,kP,30);
			flywheelMotorAlpha.config_kI(0,kI,30);
			flywheelMotorAlpha.config_kD(0,kD,30);	
			

			ShooterOutput shooterOutput = shooterCommand.execute();
			// ShooterOutput turretOutput = turretCommand.execute();
			// readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
			SmartDashboard.putNumber("Actual Speed",flywheelMotorAlpha.getSelectedSensorVelocity()*10/(1024.0));
 			flywheelMotorAlpha.set(ControlMode.Velocity, (1024/100.0)*shooterOutput.getOutputValue());
			 // flywheelMotorBeta.set(ControlMode.PercentOutput, shooterOutput.getOutputValue());
			// turretMotor.set(ControlMode.PercentOutput, turretOutput.getOutputValue());
		} 
		SmartDashboard.putNumber("Encoder",flywheelMotorAlpha.getSelectedSensorPosition());
	} 


	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

	public void startLimelightAiming(){
		shooterCommand = new LimelightTurret(shooterSensors, driveSensors);
	} 

}
