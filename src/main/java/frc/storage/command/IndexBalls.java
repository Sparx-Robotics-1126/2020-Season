package frc.storage.command;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class IndexBalls extends StorageCommand {

    short amountOfBalls;
    int numberOfIndexedBalls;

    boolean intakeRollers;
    boolean indexRollers;

    boolean pastIntakeSensorValue;
    boolean pastShootingSensorValue;
    

    public IndexBalls(StorageSensorInterface sensors, short amountOfBalls) {
        super(sensors);
        this.amountOfBalls = amountOfBalls;
        pastIntakeSensorValue = sensors.getIntakeSensor();
        pastShootingSensorValue = sensors.getShootSensor();
        if(pastIntakeSensorValue)
            amountOfBalls++;
        if(pastShootingSensorValue)
            amountOfBalls--;
    }

    @Override
    public StorageOutput execute() {
        if(!pastIntakeSensorValue && sensors.getIntakeSensor()){
            pastIntakeSensorValue = true;
            amountOfBalls += 1;
        }else if(pastIntakeSensorValue && !sensors.getIntakeSensor()){
            pastIntakeSensorValue = false;
        }

        if(!pastShootingSensorValue && sensors.getShootSensor()) {
            pastShootingSensorValue = true;
            amountOfBalls -= 1;
        }else if(pastShootingSensorValue & !sensors.getShootSensor()){
            pastShootingSensorValue = false;
        }

        if(sensors.getIndexSensor() && !sensors.getShootSensor()){
            return new StorageOutput(.2, amountOfBalls);
        }

        if(amountOfBalls<0){
            amountOfBalls = 0;
        }

        return new StorageOutput(0, amountOfBalls);
    }

}