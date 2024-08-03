package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;

public class LauncherSubsystem extends SubsystemBase {

  private PWMSparkMax frontMotor;
  private PWMSparkMax rearMotor;

  public LauncherSubsystem() {
    frontMotor = new PWMSparkMax(LauncherConstants.FRONT_MOTOR_CHANNEL);
    rearMotor = new PWMSparkMax(LauncherConstants.REAR_MOTOR_CHANNEL);

    /*
     * Ensure that both motors are stopped when the LauncherSubsystem
     * is instantiated.
     */
    frontMotor.stopMotor();
    rearMotor.stopMotor();
  }

  /*
   * Motors will run at a single speed until stopped.
   *
   * Adjust the motor speed here. KitBot doesn't currently support adjusting speed via the controller.
   */
  public void RunBothMotors() {
    frontMotor.set(LauncherConstants.FIRING_SPEED);
    rearMotor.set(LauncherConstants.FIRING_SPEED);
  }

  public void runFrontMotor() {
    frontMotor.set(LauncherConstants.FIRING_SPEED);
  }

  public void runRearMotor() {
    rearMotor.set(LauncherConstants.FIRING_SPEED);
  }

  /*
   * Stops the motors.
   */
  public void StopMotors() {
    frontMotor.stopMotor();
    rearMotor.stopMotor();
  }
}
