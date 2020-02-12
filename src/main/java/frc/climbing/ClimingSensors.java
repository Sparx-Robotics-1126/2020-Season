package frc.climbing;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.IO;

public class ClimingSensors implements ClimbingSensorsInterface {

    Encoder leadScrew = new Encoder(IO.LEADSCREW_ENCODER_A,IO.LEADSCREW_ENCODER_B);
    Encoder winch = new Encoder(IO.WINCH_ENCODER_A,IO.WINCH_ENCODER_B);
    DigitalInput bar = new DigitalInput(IO.CLIMBING_BAR_BUTTON); 

    public ClimingSensors(){
        leadScrew.setDistancePerPulse(999);
        winch.setDistancePerPulse(999);
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