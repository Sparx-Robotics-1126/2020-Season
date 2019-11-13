package frc.subsystems.drives_commands;

public class DrivesOutput {

    /**
     * Left Motor output
     * Value between [-1, 1]
     */
    private double leftMotor;

    /**
     * Right Motor output
     * Value between [-1, 1]
     */
    private double rightMotor;

    /**
     * Is the command done?
     * Will be used during auto to tell next command to start.
     */
    private boolean isDone;

    public DrivesOutput(double leftMotor, double rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.isDone = false;
    }

    public DrivesOutput(double leftMotor, double rightMotor, boolean isDone){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.isDone = isDone;
    }

    public double getLeftMotor() {
        return leftMotor;
    }

    public double getRightMotor() {
        return rightMotor;
    }

    public boolean isDone() {
        return isDone;
    }
}
