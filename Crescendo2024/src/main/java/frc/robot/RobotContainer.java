// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  /// SUBSYSTEMS ///
  // Remmber these are members of the class meaning they should start with the m_ prefix and end
  // with the Subsystem suffix
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final LauncherSubsystem m_launcherSubsystem = new LauncherSubsystem();
  private final TankDriveSubsystem m_tankDriveSubsystem = new TankDriveSubsystem();

  /// CONTROLLERS ///
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    /*
     * Set Default Commands.
     *
     * There are three Differential Drive modes available in TankDriveSubsystem.
     * Uncomment the drive mode you wish to use. Don't try to use two drive modes at the same time.
     */

    // m_tankDriveSubsystem.setDefaultCommand(
    //     Commands.run(
    //         () ->
    //             m_tankDriveSubsystem.driveTank(
    //                 m_driverController.getLeftY(), m_driverController.getRightY()),
    //         m_tankDriveSubsystem));

    m_tankDriveSubsystem.setDefaultCommand(
        Commands.run(
            () ->
                m_tankDriveSubsystem.driveArcade(
                    m_driverController.getLeftY(), m_driverController.getRightX()),
            m_tankDriveSubsystem));

    // m_tankDriveSubsystem.setDefaultCommand(
    //     Commands.run(
    //         () ->
    //             m_tankDriveSubsystem.driveCurvature(
    //                 m_driverController.getLeftY(), m_driverController.getRightX()),
    //         m_tankDriveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    /// BOUND COMMANDS ///
    /*
     *     Continuous Command
     *     m_driverController.y().onTrue(new ExampleCommand(args*));
     *
     *     Single Input Command / Do not use a Command class
     *     m_driverController.y().onTrue(Commands.runOnce(() -> m_subsystemExample.exampleMethod(), m_subsystemExample));
     */

    /*
     * Pressing (A) on the controller will start the launcher's motors. The motors will continue to spin until stopped by pressing the (B) button, even if the (A) button is released.
     */
    m_driverController
        .a()
        .onTrue(Commands.runOnce(() -> m_launcherSubsystem.RunMotors(), m_launcherSubsystem));

    /*
     * Pressing (B) on the controller will stop the launcher's motors.
     */
    m_driverController
        .b()
        .onTrue(Commands.runOnce(() -> m_launcherSubsystem.RunMotors(), m_launcherSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
