package frc.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Turret {
    double speedOfTurret;
    double speedOfRobot;
    double turretAngle;
    double targetAngle;
    double targetDistance;
    double robotSpeed;  // for shooting while moving
    
	double deadband = 2;
	double minSpeed = 0.1;
	double maxAngle = 12;
	double fullSpeed = 0.5;
    double tx;


    CANSparkMax shooter1;
    CANSparkMax shooter2;
    TalonSRX turret;

    public Turret(){
        shooter1 = new CANSparkMax(0, MotorType.kBrushed);
        shooter2 = new CANSparkMax(1, MotorType.kBrushed);
        turret = new TalonSRX(8);
    }


    public void setTurret(double speed){
        turret.set(ControlMode.PercentOutput,speed);
        System.out.println("Got here");
    }

    public void setShooter(double speed){
            shooter1.set(speed);
            shooter2.set(speed);
    }

    public double getTx(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }

    public void printTx(){
        System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
    }

    public void selfAim(){
        tx = getTx();
	    double absTX = Math.abs(tx);
    
        if (absTX > maxAngle) {
            if(tx<0) {
            setShooter(-fullSpeed);
            } else {
                setShooter(fullSpeed);
            } 
         } else if(absTX > deadband) {
             double speed = absTX/maxAngle; 
             if (tx< 0) {
                 setShooter(-speed);
             } else {
                 setShooter(speed);
             }
         } else {
             setShooter(0);
         } 
        }
    }
    