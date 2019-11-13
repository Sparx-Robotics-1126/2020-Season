package frc.subsystems;

import frc.sensors.DrivesSensorInterface;
import frc.subsystems.drives_commands.DrivesCommand;
import frc.subsystems.drives_commands.DrivesOutput;

public class Drives extends Subsystem{

    private DrivesCommand drivesCommand;
    private DrivesSensorInterface drivesSensors;

    public Drives(){
//        drivesSensors = new DrivesSensors();
    }

    //Used during TESTING
    public Drives(DrivesSensorInterface sensors){
        drivesSensors = sensors;
    }

    @Override
    void execute() {
        if(drivesCommand != null) {
            DrivesOutput output = drivesCommand.execute();
            //Set left motor
            //set right motor
            //set isDone flag
            //If isDone set state to null
        }
    }

    @Override
    boolean isDone() {
        return drivesCommand == null;
    }
}