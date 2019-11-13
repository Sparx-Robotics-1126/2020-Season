package frc.subsystems;

public abstract class Subsystem implements Runnable {

    //This is called continuously
    @Override
    public void run(){
        execute();
    }

    /**
     * Called by run(). This is where most of subsystem code goes
     */
    abstract void execute();

    /**
     * Used during autonomous to see what a task ( such as drives a certain distance forward ) is completed.
     */
    abstract boolean isDone();

}
