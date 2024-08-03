// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class SoftwareVersionConstants {
    /*
     * Contains constants representing the version of the software that is running.
     * 
     * SW_VERSION is represented as ["name of robot - name of branch"]. 
     * 
     * BUILD_NUMBER is an optional field for tracking various builds for a branch.
     * -1 = NO_STATEMENT (i.e., this optional field isn't being used). The master branch's
     * build number will always be -1.
     */
    public static final String SW_VERSION = "KitBot - basic-updates";
    public static final int BUILD_NUMBER = -1;
  }

  public static class OperatorConstants {
    public static final int K_DRIVER_CONTROLLER_PORT = 0;
  }

  public static class DriveTrainConstants {
    public static final int LEFT_1 = 0;
    public static final int LEFT_2 = 1;
    public static final int RIGHT_1 = 3;
    public static final int RIGHT_2 = 2;

    public static final double DEAD_BAND = 0.1;

    public static final double MAXIMUM_SPEED = 0.6;
  }

  public static class LauncherConstants {
    public static final int FRONT_MOTOR_CHANNEL = 8;
    public static final int REAR_MOTOR_CHANNEL = 9;

    public static final double FIRING_SPEED = 1.0;
  }
}
