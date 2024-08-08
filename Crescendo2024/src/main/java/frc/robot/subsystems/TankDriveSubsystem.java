package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class TankDriveSubsystem extends SubsystemBase {
  Spark m_left1;
  Spark m_left2;
  Spark m_right1;
  Spark m_right2;
  DifferentialDrive m_drive;

  // Member variables to store the current values of left and right sticks
  private double currentLeftStick = 0.0;
  private double currentRightStick = 0.0;

  public TankDriveSubsystem() {
    m_left1 = new Spark(DriveTrainConstants.LEFT_1);
    m_left2 = new Spark(DriveTrainConstants.LEFT_2);
    m_right1 = new Spark(DriveTrainConstants.RIGHT_1);
    m_right2 = new Spark(DriveTrainConstants.RIGHT_2);

    m_right1.setInverted(true);
    m_left1.addFollower(m_left2);
    m_right1.addFollower(m_right2);

    m_drive = new DifferentialDrive(m_left1, m_right1);
  }

  private double applyDeadBand(double inp) {
    if (Math.abs(inp) > (DriveTrainConstants.DEAD_BAND)) {
      return inp;
    }
    return 0.0;
  }

  private double dampenSpeed(double inp) {
    return (Math.abs(inp) * inp * DriveTrainConstants.MAXIMUM_SPEED);
  }

  // Stepper function to gradually adjust the current stick value towards the target value
  private double adjustValue(double currentValue, double targetValue) {
    if (currentValue < targetValue) {
      return Math.min(currentValue + DriveTrainConstants.STEP_SIZE, targetValue);
    } else if (currentValue > targetValue) {
      return Math.max(currentValue - DriveTrainConstants.STEP_SIZE, targetValue);
    }
    return currentValue;
  }

  public void driveTank(double leftStick, double rightStick) {
    currentLeftStick = adjustValue(currentLeftStick, leftStick);
    currentRightStick = adjustValue(currentRightStick, rightStick);

    m_drive.tankDrive(
        dampenSpeed(applyDeadBand(currentLeftStick)),
        dampenSpeed(applyDeadBand(currentRightStick)));
  }

  public void driveArcade(double speed, double rotation) {
    currentLeftStick = adjustValue(currentLeftStick, speed);
    currentRightStick = adjustValue(currentRightStick, -rotation);

    m_drive.arcadeDrive(
        dampenSpeed(applyDeadBand(currentLeftStick)),
        dampenSpeed(applyDeadBand(currentRightStick)));
  }
}
