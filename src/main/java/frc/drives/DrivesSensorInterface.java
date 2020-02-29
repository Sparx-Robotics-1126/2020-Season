package frc.drives;

import com.revrobotics.CANEncoder;

/**
 * All sensor data passed into drives will appear here
 * Includes: Encoders, Limit Switches, Vision, Joysitck input, ext.
 */
public interface DrivesSensorInterface {

	void addEncoders(CANEncoder leftSpark, CANEncoder rightSpark);
	
    //Sensors
    double getLeftEncoderDistance();
    double getLeftEncoderSpeed();
    double getRightEncoderDistance();
    double getRightEncoderSpeed();
    double getAverageEncoderDistance();
    double getAverageEncoderSpeed();
    double getGyroAngle();

    //OperatorInput
    double getRightJoyStick();
    double getLeftJoyStick();
    void setRightJoystick(double value);
    void setLeftJoystick(double value);
}
