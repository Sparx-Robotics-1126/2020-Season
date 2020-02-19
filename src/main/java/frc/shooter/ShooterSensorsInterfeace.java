package frc.shooter;

public interface ShooterSensorsInterfeace {

	public abstract double getDistanceToTarget();
	public abstract double getAngleToTarget();
	public abstract double getShooterAngleToRobot();
	public abstract double getShooterSpeed();
	public abstract boolean getTargetLock();
	public abstract void enableLimelight(boolean enable);
	
}
