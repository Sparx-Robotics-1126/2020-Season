package frc.climbing;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.IO;

public class ClimingSensors implements ClimbingSensorsInterface {

    private Encoder leadScrew;
    private CANEncoder winch;
    private DigitalInput bar;

    public ClimingSensors(CANSparkMax c){
        leadScrew = new Encoder(IO.LEADSCREW_ENCODER_A,IO.LEADSCREW_ENCODER_B);
        bar = new DigitalInput(IO.CLIMBING_BAR_BUTTON);  
        winch = c.getEncoder();
        leadScrew.setDistancePerPulse(0.00411523);   
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
        return winch.getPosition()/(6.87292);
    }
    

}