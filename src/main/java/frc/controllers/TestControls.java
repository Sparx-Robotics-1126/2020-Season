/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.health.HealthReport;
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

/**
 * Add your docs here.
 */
public class TestControls extends Controller {

    private long startTimeStamp;
    private final long RUNTIME;
    private final long RUNTIME_SHOOT;

    public TestControls(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage) {
        super(acq, climbing, drives, shooter, storage);
        RUNTIME = 10000;//10 seconds
        RUNTIME_SHOOT = 12000; //12 seconds;
        reset();
    }

    public void reset(){
        startTimeStamp = 0;
    }

    @Override
    public void execute() {
        if(startTimeStamp == 0){
            startTimeStamp = System.currentTimeMillis();
            drives.moveBackward(120);
            acq.startIntake();
            storage.prepareForShooting();
            shooter.testShooter();
            climbing.extendScissorLift();
        }else if(startTimeStamp != Long.MAX_VALUE && System.currentTimeMillis() > startTimeStamp + RUNTIME){
            startTimeStamp = Long.MAX_VALUE;
            drives.setJoysticks(0, 0);
            drives.startDriverControlled();
            acq.stopRollers();
            shooter.centerTurret();
            climbing.retractScissorLift();
        }else{
            displaySmartDashboard("Acq", acq.getHealthCheck());
            displaySmartDashboard("Climbing", climbing.getHealthCheck());
            displaySmartDashboard("Drives", drives.getHealthCheck());
            displaySmartDashboard("Shooter", shooter.getHealthCheck());
            displaySmartDashboard("Storage", storage.getHealthCheck());
        }
    }

    private void displaySmartDashboard(String name, HealthReport report){
        SmartDashboard.putBoolean(name + " Status", !report.isError());
        SmartDashboard.putString(name + " Message", report.getMessage());
    }
}
