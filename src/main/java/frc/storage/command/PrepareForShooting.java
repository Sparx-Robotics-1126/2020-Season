package frc.storage.command;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;



public class PrepareForShooting extends StorageCommand {

    short amountOfBalls;

    public PrepareForShooting(StorageSensorInterface sensorsInterface, short amountOfBalls){
        super(sensorsInterface);
        this.amountOfBalls = amountOfBalls;
    }

    @Override
    public StorageOutput execute () {
        if(!sensors.getShootSensor()){
            return new StorageOutput(.6,amountOfBalls);
        }
        return new StorageOutput(0,amountOfBalls,true);
    }
}