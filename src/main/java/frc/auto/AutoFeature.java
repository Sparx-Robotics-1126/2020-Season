package frc.auto;

public enum AutoFeature {
	STOP("Stopping Auto"),
	WAIT("Waiting..."),
	
	DRIVE_DONE("Drive: Waiting for Complete..."),
	DRIVE_FORWARD("Drive: Forward"),
	DRIVE_BACKWARDS("Drive: Backward"),
	DRIVE_TURN_RIGHT("Drive: Turn Right"),
	DRIVE_TURN_LEFT("Drive: Turn Left"),
	
	SHOOTER_DONE("Shooter: Waiting for Complete..."),
	SHOOTER_ACTIVATE_LIMELIGHT("Shooter: Limelight Enabled"),
	SHOOTER_DEACTIVATE_LIMELIGHT("Shooter: Limelight Disabled"),
	STORAGE_SHOOT("Storage: Shooting"),
	
	STORAGE_DONE("Storage: Waiting for Complete..."),
	STORAGE_PREPARE_FOR_SHOOTING("Storage: Preparing to Shoot"),
	
	ACQ_DONE("Acq: Waiting for Complete..."),
	ACQ_ACQUIRE("Acq: Acquiring"),
	ACQ_STOP_ACQUIRING("Acq: Stop Acquiring");
	
	
	private String name;
	private AutoFeature(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
