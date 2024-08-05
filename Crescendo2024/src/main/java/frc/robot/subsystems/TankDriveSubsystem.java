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

  public TankDriveSubsystem() {
    m_left1 = new Spark(DriveTrainConstants.LEFT_1);
    m_left2 = new Spark(DriveTrainConstants.LEFT_2);
    m_right1 = new Spark(DriveTrainConstants.RIGHT_1);
    m_right2 = new Spark(DriveTrainConstants.RIGHT_2);

    /*
     * It is necessary to invert one side of the drive train.
     * Only invert the lead motor.
     * Do NOT invert the follower motor. The follower motor will
     * inherit the inversion of the lead motor.
     */
    m_right1.setInverted(true);

    /*
     * Set followers.
     *
     * Whatever commands are sent to the lead motors will also
     * be sent to the follower motors.
     */
    m_left1.addFollower(m_left2);
    m_right1.addFollower(m_right2);

    /*
     * The DifferentialDrive constructor only takes 2 motors - one
     * representing the left side and one representing the right side
     * of the drive train.
     *
     * This is why we set followers. As the DifferentialDrive class
     * controls the lead motors, the follower motors will get identical
     * control signals.
     */
    m_drive = new DifferentialDrive(m_left1, m_right1);
  }

  /*
   * The dead band is applied here.
   *
   * Dead band accounts for slight stick drift in the controller.
   * Sometimes, the sticks don't return to precisely zero. Other times,
   * an anxious or excited driver has thumbs on the sticks and will slightly
   * move them off of zero.
   *
   * In either case, the dead band enforces that the stick must
   * be moved past a pre-determined value before applying that input
   * to the Differential Drive.
   */
  private double applyDeadBand(double inp) {
    if (Math.abs(inp) > (DriveTrainConstants.DEAD_BAND)) {
      return inp;
    }
    return 0.0;
  }

  /*
   * This function controls the maximum output of the drive train.
   *
   * Often times, you don't want the maxmimum power that could be applied.
   * This makes sure that the operator, even when applying full
   * power, can maintain control of the robot.
   *
   * Speed-dampening formula:
   * absoluteValue(input) * input * maxDesiredSpeed
   */
  private double dampenSpeed(double inp) {
    return (Math.abs(inp) * inp * DriveTrainConstants.MAXIMUM_SPEED);
  }

  /*
   * Tank Drive - one input controls the left tread, and the
   * other input contorls the right tread. Usually, input1 is the
   * left stick and input2 is the right stick.
   *
   * It can be difficult to drive in a stright line using Tank Drive.
   */
  public void driveTank(double leftStick, double rightStick) {
    m_drive.tankDrive(
        dampenSpeed(applyDeadBand(leftStick)), dampenSpeed(applyDeadBand(rightStick)));
  }

  /*
   * Arcade Drive - one input controls forward / backward speed, while
   * the other input controls rotation. Usually, the speed is controlled
   * by the left stick while rotation is controlled by the right stick.
   *
   * Generally, it's very easy to drive in a straight line using
   * Arcade Drive.
   *
   * The rotation parameter is negated because of the coordinate grid used by
   * the operator controller.
   */
  public void driveArcade(double speed, double rotation) {
    m_drive.arcadeDrive(dampenSpeed(applyDeadBand(speed)), dampenSpeed(applyDeadBand(-rotation)));
  }
}
