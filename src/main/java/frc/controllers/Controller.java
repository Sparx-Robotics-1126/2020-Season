package frc.controllers;

import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

//Interface for all Controllers. Makes sure all Controllers have same methods to call
public abstract class Controller {

	protected Acquisitions acq;
	protected Climbing climbing;
    protected Drives drives;
    protected Shooter shooter;
    protected Storage storage;

    public Controller(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage){
        this.acq = acq;
        this.climbing = climbing;
    	this.drives = drives;
    	this.shooter = shooter;
    	this.storage = storage;
    }
    public abstract void execute();
}
