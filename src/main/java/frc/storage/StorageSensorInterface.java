package frc.storage;

public interface StorageSensorInterface {

	/**
	 * @return true when ball enters system
	 */
	public abstract boolean getIntakeSensor();
	
	/**
	 * @return true when ball at bottom of conveyer
	 */
	public abstract boolean getIndexSensor();
	
	/**
	 * @return true when ball is about to enter shooter
	 */
	public abstract boolean getShootSensor();
	
}
