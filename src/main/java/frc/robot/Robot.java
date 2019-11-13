package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

	private SubsystemManager subsystemManager;

	/**
	 * Called ONCE when robot turns on
	 */
	public void robotInit() {
		subsystemManager = new SubsystemManager();
	}

	/**
	 * Called ONCE when auto starts
	 */
	public void autonomousInit() {
		subsystemManager.autoStarted();
	}

	/**
	 * Called ONCE when teleop starts
	 */
	public void teleopInit() {
		subsystemManager.teleopStarted();
	}

	/**
	 * Called ONCE when test starts
	 */
	public void testInit() {
//		subsystemManager.testStarted();
	}
}
