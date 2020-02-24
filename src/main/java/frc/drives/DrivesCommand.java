package frc.drives;

import frc.health.HealthCheck;

public abstract class DrivesCommand extends HealthCheck{

    /**
     * Class containing getters for all sensors needed for this subsystem
     */
    protected DrivesSensorInterface sensors;

    //Constructor
    public DrivesCommand(DrivesSensorInterface sensors){
        this.sensors = sensors;
    }

    //Will be called by subsystem to execute this command
    public abstract DrivesOutput execute();
    
}
