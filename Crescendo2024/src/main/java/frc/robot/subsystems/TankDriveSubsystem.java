package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {
  Spark m_left1;
  Spark m_left2;
  Spark m_right1;
  Spark m_right2;
  DifferentialDrive m_drive;

  public TankDriveSubsystem() {
    m_left1 = new Spark(0);
    m_left2 = new Spark(1);
    m_right1 = new Spark(3);
    m_right2 = new Spark(2);

    /*
     * Only invert the lead motor.
     * Do NOT invert the follower motor.
     */
    m_right1.setInverted(true);

    /*
     * Set followers.
     */
    m_left1.addFollower(m_left2);
    m_right1.addFollower(m_right2);

    m_drive = new DifferentialDrive(m_left1, m_right1);
  }

  /*
   * Control Dead Band here.
   */
  private double applyDeadBand(double inp) {
    if (Math.abs(inp) > (0.1)) {
      return inp;
    }
    return 0.0;
  }

  /*
   * Dampens speed by using the formula:
   * absoluteValue(input) * input * maxDesiredSpeed
   *
   * Control Speed Dampener here.
   */
  private double dampenSpeed(double inp) {
    return (Math.abs(inp) * inp * 0.8);
  }

  public void driveTank(double leftStick, double rightStick) {
    m_drive.tankDrive(
        dampenSpeed(applyDeadBand(leftStick)), dampenSpeed(applyDeadBand(rightStick)));
  }

  public void driveArcade(double speed, double rotation) {
    m_drive.arcadeDrive(dampenSpeed(applyDeadBand(speed)), dampenSpeed(applyDeadBand(-rotation)));
  }

  public void driveCurvature(double speed, double curvature) {
    m_drive.curvatureDrive(
        dampenSpeed(applyDeadBand(speed)), dampenSpeed(applyDeadBand(curvature)), true);
  }
}
