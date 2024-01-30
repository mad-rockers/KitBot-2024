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
    m_right1 = new Spark(2);
    m_right2 = new Spark(3);

    // Invert your motor inputs here
    m_left1.setInverted(true);
    m_left2.setInverted(true);

    // Set followers
    m_left1.addFollower(m_left2);
    m_right1.addFollower(m_right2);

    m_drive = new DifferentialDrive(m_left1, m_right1);
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
