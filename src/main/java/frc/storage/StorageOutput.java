package frc.storage;

public class StorageOutput {
	
	private double primaryOutput;
	private double secondaryOutput;
	private short numOfBallsAquired;
	private boolean commandFinished;

	public StorageOutput(double output, short numOfBalls){
		this.primaryOutput = output;
		this.secondaryOutput = output;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = false;
	}
	
	public StorageOutput(double primary, double seconday, short numOfBalls){
		this.primaryOutput = primary;
		this.secondaryOutput = seconday;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = false;
	}
	
	public StorageOutput(double output, short numOfBalls, boolean commandFinished){
		this.primaryOutput = output;
		this.secondaryOutput = output;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = commandFinished;
	}
	
	public StorageOutput(double primary, double seconday, short numOfBalls, boolean commandFinished){
		this.primaryOutput = primary;
		this.secondaryOutput = seconday;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = commandFinished;
	}
	
	@Deprecated
	public double getOutput() {
		return primaryOutput;
	}
	
	public double getPrimaryOutput() {
		return primaryOutput;
	}

	public double getSecondaryOutput() {
		return secondaryOutput;
	}

	public short getNumOfBallsAquired() {
		return numOfBallsAquired;
	}

	public boolean isCommandFinished() {
		return commandFinished;
	}

}
