package frc.auto.routine;

import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;

public class SixBallsFromTrench extends AutoRoutine {
	public SixBallsFromTrench() {
		super("Shoot Six", auto);
	}
	private static final AutoTask[] auto = {
			// shoots initial 3 balls from line
			AutoTask.createTask(AutoFeature.SHOOTER_ACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DEACTIVATE_LIMELIGHT),
			// acquires 3 balls from trench and returns to start line
			AutoTask.createTask(AutoFeature.DRIVE_TURN_LEFT, 1 , 90),
			AutoTask.createTask(AutoFeature.DRIVE_FORWARD, 1 , 60),
			AutoTask.createTask(AutoFeature.DRIVE_TURN_RIGHT, 1 , 90),
			AutoTask.createTask(AutoFeature.ACQ_ACQUIRE),
			AutoTask.createTask(AutoFeature.DRIVE_FORWARD, 1 , 198),
			AutoTask.createTask(AutoFeature.ACQ_DONE),
			AutoTask.createTask(AutoFeature.DRIVE_TURN_RIGHT, 1, 70),
			AutoTask.createTask(AutoFeature.DRIVE_FORWARD, 1, 204),
			AutoTask.createTask(AutoFeature.DRIVE_TURN_RIGHT, 1, 200),
			// shoots the 3 acquired balls
			AutoTask.createTask(AutoFeature.SHOOTER_ACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DEACTIVATE_LIMELIGHT),
	};
}
