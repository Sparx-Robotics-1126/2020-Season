package frc.climbing;

public interface ClimbingSensorsInterface {

	/**
	 * @return true when hook mechanism is touching bar
	 */
	public abstract boolean isTouchingBar();
	
	/**
	 * @return the distance the leadscrew has traveled in inches.
	 */
	public abstract double leadScrewDistance();
}
