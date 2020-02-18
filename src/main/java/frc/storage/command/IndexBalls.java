package frc.storage.command;

import frc.shooter.ShooterOutput;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class IndexBalls extends StorageCommand {

    short amountOfBalls;
    int numberOfIndexedBalls;

    boolean intakeRollers;
    boolean indexRollers;

    boolean pastIntakeSensorValue;

    public IndexBalls(StorageSensorInterface sensors, short amountOfBalls) {
        super(sensors);
        this.amountOfBalls = amountOfBalls;
        pastIntakeSensorValue = sensors.getIntakeSensor();
    }

    @Override
    public StorageOutput execute() {
        if(!pastIntakeSensorValue && sensors.getIntakeSensor()){
            pastIntakeSensorValue = true;
            amountOfBalls += 1;
        }else if(pastIntakeSensorValue && !sensors.getIntakeSensor()){
            pastIntakeSensorValue = false;
        }

        if(sensors.getIndexSensor() && !sensors.getShootSensor()){
            return new StorageOutput(.2, amountOfBalls);
        }

        return new StorageOutput(0, amountOfBalls);
    }
    
}