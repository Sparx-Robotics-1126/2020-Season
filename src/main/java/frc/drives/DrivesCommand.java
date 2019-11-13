package frc.subsystems.drives_commands;

import frc.sensors.DrivesSensorInterface;

public abstract class DrivesCommand {

    /**
     * Class containing getters for all sensors needed for this subsystem
     */
    protected DrivesSensorInterface sensors;

    public DrivesCommand(DrivesSensorInterface sensors){
        this.sensors = sensors;
    }

    public abstract DrivesOutput execute();

}
