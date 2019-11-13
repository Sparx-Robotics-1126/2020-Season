package frc.drives;

/**
 * All sensor data passed into drives will appear here
 * Includes: Encoders, Limit Switches, Vision, Joysitck input, ext.
 */
public interface DrivesSensorInterface {

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
}
