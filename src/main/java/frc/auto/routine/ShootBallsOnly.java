package frc.auto.routine;

import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;

public class ShootBallsOnly extends AutoRoutine{

	public ShootBallsOnly() {
		super("shoot Three", auto);
		// TODO Auto-generated constructor stub
	}
	private static final AutoTask[] auto = {
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
			AutoTask.createTask(AutoFeature.DRIVE_BACKWARDS, 1 , 24),
			AutoTask.createTask(AutoFeature.DRIVE_DONE),
			AutoTask.createTask(AutoFeature.STOP),
	};
}
