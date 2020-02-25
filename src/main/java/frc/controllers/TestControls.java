/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controllers;

import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

/**
 * Add your docs here.
 */
public class TestControls extends Controller {

    public TestControls(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage) {
        super(acq, climbing, drives, shooter, storage);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
    }
}
