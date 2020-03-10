package frc.storage.command;

import frc.health.HealthReport;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class PrepareForShooting extends StorageCommand {

    private short amountOfBalls;

    public PrepareForShooting(StorageSensorInterface sensorsInterface, short amountOfBalls){
        super(sensorsInterface);
        this.amountOfBalls = amountOfBalls;
        checkedIndex = false;
        checkedIntake = false;
        checkedShooter = false;
    }

    @Override
    public StorageOutput execute () {
        if(!sensors.getShootSensor()){
            return new StorageOutput(0.30,amountOfBalls);
        }
        return new StorageOutput(0,amountOfBalls,true);
    }
    
    //--------------------------HEALTH CHECK-----------------------------
    
    private boolean checkedIntake;
    private boolean checkedIndex;
    private boolean checkedShooter;
    
    @Override
    public HealthReport checkHealth() {
    	if(sensors.getIntakeSensor()) {
    		checkedIntake = true;
    	}
    	if(sensors.getIndexSensor()) {
    		checkedIndex = true;
    	}
    	if(sensors.getShootSensor()) {
    		checkedShooter = true;
    	}
    	if(!checkedIntake) {
    		return new HealthReport(true, "Waiting for Intake...");
    	}else if(!checkedIndex) {
    		return new HealthReport(true, "Waiting for Index...");
    	}else if(!checkedShooter) {
    		return new HealthReport(true, "Waiting for Shooter...");
    	}
    	return new HealthReport();
    }
}