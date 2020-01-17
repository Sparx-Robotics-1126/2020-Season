package frc.shooter;

public class ShooterOutput {

	private double shooterSpeed;
	private double shooterAngle;
	private boolean readyToShoot;
	
	/**
	 * Used to tell shooter motors what to do.
	 * This constructor can only be used when system is not ready to shoot
	 * @param shooterSpeed
	 * @param shooterAngle
	 */
	public ShooterOutput(double shooterSpeed, double shooterAngle) {
		this.shooterSpeed = shooterSpeed;
		this.shooterAngle = shooterAngle;
		this.readyToShoot = false;
	}
	
	/**
	 * Used to tell shooter motors what to do.
	 * This constructor may only be used when system is ready to shoot
	 * @param shooterSpeed
	 * @param shooterAngle
	 * @param readyToShoot
	 */
	public ShooterOutput(double shooterSpeed, double shooterAngle, boolean readyToShoot) {
		this.shooterSpeed = shooterSpeed;
		this.shooterAngle = shooterAngle;
		this.readyToShoot = readyToShoot;
	}
	
	public double getShooterSpeed() {
		return shooterSpeed;
	}
	public double getShooterAngle() {
		return shooterAngle;
	}
	public boolean isReadyToShoot() {
		return readyToShoot;
	}
	
}
