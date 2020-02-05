package frc.storage;

public class StorageOutput {
	
	private double output;
	private short numOfBallsAquired;
	private boolean commandFinished;

	public StorageOutput(double output, short numOfBalls){
		this.output = output;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = false;
	}
	
	public StorageOutput(double output, short numOfBalls, boolean commandFinished){
		this.output = output;
		this.numOfBallsAquired = numOfBalls;
		this.commandFinished = commandFinished;
	}
	
	public double getOutput() {
		return output;
	}
	public short getNumOfBallsAquired() {
		return numOfBallsAquired;
	}

	public boolean isCommandFinished() {
		return commandFinished;
	}

}
