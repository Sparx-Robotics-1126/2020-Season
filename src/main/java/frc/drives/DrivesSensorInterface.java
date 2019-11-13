package frc.sensors;

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
