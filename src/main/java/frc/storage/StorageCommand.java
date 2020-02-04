package frc.storage;

public abstract class StorageCommand {
	 /**
     * Class containing getters for all sensors needed for this subsystem
     */
    protected StorageSensorInterface sensors;

    //Constructor
    public StorageCommand(StorageSensorInterface sensors){
        this.sensors = sensors;
    }

    //Will be called by subsystem to execute this command
    public abstract StorageOutput execute();
}
