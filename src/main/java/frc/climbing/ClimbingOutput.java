package frc.climbing;

public class ClimbingOutput {

	private double output;
	private boolean isFinished;
	
	public ClimbingOutput(double output) {
		this.output = output;
		this.isFinished = false;
	}
	
	public ClimbingOutput(double output, boolean isFinished) {
		this.output = output;
		this.isFinished = isFinished;
	}
	
	public double getOutput() {
		return output;
	}
	public boolean isFinished() {
		return isFinished;
	}
	
	
}
