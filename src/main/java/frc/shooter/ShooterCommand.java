package frc.shooter;

import frc.drives.DrivesSensorInterface;

public abstract class ShooterCommand {
	/**
     * Class containing getters for all sensors needed for this subsystem
     */
    protected ShooterSensorsInterfeace sensors;

    //Constructor
    public ShooterCommand(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        this.sensors = sensors;
    }


    //Will be called by subsystem to execute this command
    public abstract ShooterOutput execute();
}
