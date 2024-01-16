package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {
  Spark m_left;
  Spark m_right;
  DifferentialDrive m_drive;

  public TankDriveSubsystem() {
    m_left = new Spark(0);
    m_right = new Spark(1);

    m_left.setInverted(true); // if you want to invert motor outputs, you must do so here

    m_drive = new DifferentialDrive(m_left, m_right);
  }

  public void driveTank(double leftStick, double rightStick) {
    m_drive.tankDrive(-leftStick, -rightStick);
  }

  public void driveArcade(double speed, double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }

  public void driveCurvature(double speed, double curvature) {
    m_drive.curvatureDrive(speed, curvature, false);
  }
}
