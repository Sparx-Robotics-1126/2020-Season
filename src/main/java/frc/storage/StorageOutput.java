package frc.storage;

public class StorageOutput {
	
	private double output;
	private short numOfBallsAquired;

	public StorageOutput(double output, short numOfBalls){
		this.output = output;
		this.numOfBallsAquired = numOfBalls;
	}
	
	public double getOutput() {
		return output;
	}
	public short getNumOfBallsAquired() {
		return numOfBallsAquired;
	}
	
	

}
