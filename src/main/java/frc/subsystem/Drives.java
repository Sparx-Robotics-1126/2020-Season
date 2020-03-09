package frc.subsystem;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;

import frc.drives.commands.SpinLeft;
import frc.drives.commands.DriveBackwards;
import frc.drives.commands.DriveForward;
import frc.drives.commands.DriverControlled;

import frc.robot.IO;
import frc.drives.commands.TurnRight;
import frc.health.HealthReport;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Used to control ALL drives behavior
 */
public class Drives extends Subsystem {

    /**
     * This is the used to scale the motor output with battery performance
     */
    private static final double DRIVES_MAX_VOLTAGE = 12.0;

    /**
     * Current drives action being run Example: DrivesForward, Turn Right,
     * HumanControl, etc.
     */
    private DrivesCommand drivesCommand;

    /*
     * Class containing all sensor data for drives Includes: Encoders, Limit
     * Switches, HumanInput, etc.
     */
    private DrivesSensorInterface drivesSensors;

    // PUT MOTOR INIT HERE
    private CANSparkMax rightMotorMaster;
    private CANSparkMax leftMotorMaster;

    // Main Constructor called in SubsystemManager.java
    public Drives(DrivesSensorInterface driveSensors) {
        rightMotorMaster = new CANSparkMax(IO.DRIVES_RIGHT_MOTOR_1, MotorType.kBrushless);
        CANSparkMax rightMotorSlave = new CANSparkMax(IO.DRIVES_RIGHT_MOTOR_2, MotorType.kBrushless);
        configureMotor(rightMotorMaster, rightMotorSlave);

        leftMotorMaster = new CANSparkMax(IO.DRIVES_LEFT_MOTOR_1, MotorType.kBrushless);
        CANSparkMax leftMotorSlave = new CANSparkMax(IO.DRIVES_LEFT_MOTOR_2, MotorType.kBrushless);
        configureMotor(leftMotorMaster, leftMotorSlave);

        driveSensors.addEncoders(leftMotorMaster.getEncoder(), rightMotorMaster.getEncoder());
        drivesSensors = driveSensors;
    }

    /**
     * Configures motors to follow one controller
     */
    private static void configureMotor(CANSparkMax master, CANSparkMax... slaves) {
        master.restoreFactoryDefaults();
        master.set(0);
        master.setIdleMode(IdleMode.kCoast);
        master.enableVoltageCompensation(12);

        for (CANSparkMax slave : slaves) {
            slave.restoreFactoryDefaults();
            slave.follow(master);
            slave.setIdleMode(IdleMode.kCoast);
        }
    }

    /**
     * Main loop of drives, is called constantly by the Thread DON'T CALL THIS!!!!
     */
    @Override
    void execute() {
        if (drivesCommand != null) {
            DrivesOutput output = drivesCommand.execute();
            leftMotorMaster.set(-output.getLeftMotor());
            rightMotorMaster.set(output.getRightMotor());
            if (output.isDone()) {
                leftMotorMaster.set(0);
                rightMotorMaster.set(0);
                drivesCommand = null;
            }
        }
    }

    /**
     * Signals when the current command is done executing (If applicable)
     */
    @Override
    public boolean isDone() {
        // How can we tell if the subsystem is ready to accept a new command?
        return drivesCommand == null;
    }

    public void setJoysticks(double left, double right) {
        drivesSensors.setLeftJoystick(left);
        drivesSensors.setRightJoystick(right);

    }

    public void moveForward(double distance, double maxSpeed) {
        drivesCommand = new DriveForward(drivesSensors, maxSpeed, distance);
    }

    public void moveBackward(double distance) {
        drivesCommand = new DriveBackwards(drivesSensors, distance);
    }

    public void turnRight(double angle) {
        drivesCommand = new TurnRight(drivesSensors, 1, angle);
    }

    public void turnLeft(double angle) {
        drivesCommand = new SpinLeft(drivesSensors, 1, angle);
    }

    public void startDriverControlled() {
        drivesCommand = new DriverControlled(drivesSensors);
    }

    @Override
    public HealthReport getHealthCheck() {
        return drivesCommand.checkHealth();
    }
    
}