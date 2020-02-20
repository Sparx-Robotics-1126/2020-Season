package frc.storage.command;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class IndexBalls extends StorageCommand {

    short amountOfBalls;

    boolean pastIntakeSensorValue;
    boolean pastShootingSensorValue;

    public IndexBalls(StorageSensorInterface sensors, short amountOfBalls) {
        super(sensors);
        this.amountOfBalls = amountOfBalls;
        pastIntakeSensorValue = sensors.getIntakeSensor();
        pastShootingSensorValue = sensors.getShootSensor();
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
            return new StorageOutput(1, 0.1, amountOfBalls);
        }

        return new StorageOutput(0, amountOfBalls);
    }

}