package frc.subsystem;

import frc.health.HealthReport;

public abstract class Subsystem implements Runnable {

    //This is called continuously by Thread
    @Override
    public void run(){
    	while (true){
    		execute();
    	}
    }

    /**
     * Called by run(). This is where most of subsystem code goes
     */
    abstract void execute();

    /**
     * Used during autonomous to see what a task ( such as drives a certain distance forward ) is completed.
     */
    public abstract boolean isDone();

    public abstract HealthReport getHealthCheck();

}
