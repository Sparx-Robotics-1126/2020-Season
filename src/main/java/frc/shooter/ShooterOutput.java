package frc.shooter;

public class ShooterOutput {

	private double outputValue;
	private boolean readyToShoot;
	private boolean commandComplete;
	
	/**
	 * Used to tell shooter motors what to do.
	 * This constructor can only be used when system is not ready to shoot
	 * @param outputValue
	 */
	public ShooterOutput(double outputValue) {
		this.outputValue = outputValue;
		this.readyToShoot = false;
	}
	
	/**
	 * Used to tell shooter motors what to do.
	 * This constructor may only be used when system is ready to shoot
	 * @param outputValue
	 * @param readyToShoot
	 */
	public ShooterOutput(double outputValue, boolean readyToShoot) {
		this.outputValue = outputValue;
		this.readyToShoot = readyToShoot;
	}
	
	public ShooterOutput(double outputValue, boolean readyToShoot, boolean isComplete) {
		this.outputValue = outputValue;
		this.readyToShoot = readyToShoot;
		this.commandComplete = isComplete;
	}
	
	public double getOutputValue() {
		return outputValue;
	}
	public boolean isReadyToShoot() {
		return readyToShoot;
	}
	public boolean isCommandComplete() {
		return commandComplete;
	}
	
}
