package frc.climbing;

public class TestClimbingSensors implements ClimbingSensorsInterface{

	public boolean isTouchingBar;
	public double leadScrewDistance;
	public double winchDistance;
	
	@Override
	public boolean isTouchingBar() {
		return isTouchingBar;
	}

	@Override
	public double getLeadScrewDistance() {
		return leadScrewDistance;
	}

	@Override
	public double getWinchDistance() {
		return winchDistance;
	}

}
