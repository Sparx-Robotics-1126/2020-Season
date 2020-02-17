package frc.climbing;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.IO;

public class ClimingSensors implements ClimbingSensorsInterface {

    Encoder leadScrew = new Encoder(IO.LEADSCREW_ENCODER_A,IO.LEADSCREW_ENCODER_B);
    CANEncoder winch;
    DigitalInput bar = new DigitalInput(IO.CLIMBING_BAR_BUTTON); 

    public ClimingSensors(CANSparkMax c){
        leadScrew.setDistancePerPulse(999);
        winch = c.getEncoder();
    }

    @Override
    public boolean isTouchingBar() {
        return bar.get();
    }

    @Override
    public double getLeadScrewDistance() {
        return leadScrew.getDistance();
    }

    @Override
    public double getWinchDistance() {
        return winch.getDistance();
    }
    

}