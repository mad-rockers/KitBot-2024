package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {

  private PWMSparkMax motor1;
  private PWMSparkMax motor2;
  private static final int motor1Channel = 8;
  private static final int motor2Channel = 9;

  public LauncherSubsystem() {
    motor1 = new PWMSparkMax(motor1Channel);
    motor2 = new PWMSparkMax(motor2Channel);

    motor1.stopMotor();
    motor2.stopMotor();
  }

  /*
   * Motors will run at a single speed until stopped.
   *
   * Adjust the motor speed here. KitBot doesn't currently support adjusting speed via the controller.
   */
  public void RunMotors() {
    motor1.set(1.0);
    motor2.set(1.0);
  }

  public void runMotor1() {
    motor1.set(1.0);
  }

  public void runMotor2() {
    motor2.set(1.0);
  }

  /*
   * Explicitly stop the motors.
   */
  public void StopMotors() {
    motor1.stopMotor();
    motor2.stopMotor();
  }
}
