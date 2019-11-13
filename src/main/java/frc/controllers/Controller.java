package frc.controllers;

import frc.subsystems.Drives;

//Interface for all Controllers. Makes sure all Controllers have same methods to call
public abstract class Controller {

    protected Drives drives;

    public Controller(Drives drives){
        this.drives = drives;
    }

    public abstract void execute();
}
