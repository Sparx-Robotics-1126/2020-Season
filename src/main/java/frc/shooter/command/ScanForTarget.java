import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.sensors.Limelight;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class ScanForTarget extends ShooterCommand {

    Limelight l;
    final int maxAngleOnEitherSide = 160;
    final int howCloseToEdge = 3;

    public ScanForTarget(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors , driveSensors);
        l = new Limelight();
    }

    @Override 
    public ShooterOutput execute() {
        double angle = sensors.getShooterAngleToRobot();

        //On right side
        if(angle > 0){
            if(angle<maxAngleOnEitherSide-howCloseToEdge){
                return new ShooterOutput(.2);
            }
            return new ShooterOutput(-.2);
        }else{
            if(angle>-maxAngleOnEitherSide+howCloseToEdge){
                return new ShooterOutput(-.2);
            }
            return new ShooterOutput(.2);
        }
        System.out.println("ScanForTarget.java, logic error");
        return new ShooterOutput(0);
    }
}