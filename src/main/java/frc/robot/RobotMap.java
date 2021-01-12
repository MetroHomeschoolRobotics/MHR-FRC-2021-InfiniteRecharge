/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //These are all ports
  public static int LeftFrontMotor = 1;//SparkMAX
  public static int LeftRearMotor = 2;//SparkMAX
  public static int RightFrontMotor = 3;//SparkMAX
  public static int RightRearMotor = 4;//SparkMAX
  public static int IntakeMotor = 0;//Spark
  public static int ControlPanelSpinMotor = 1;//Spark
  public static int ShooterMotor1 = 5;//SparkMAX
  public static int ShooterMotor2 = 6; //SparkMAX
  public static int MagazineMotor = 0;//CAN TalonSRX
  public static int TransitionMotor = 2;//Spark
  public static int ClimberMotor = 1; //CAN TalonSRX
  public static int ControlPanelLiftMotor = 3;//Spark
  public static int ControlPanelLiftEncoderA = 0;
  public static int ControlPanelLiftEncoderB = 1;
  //public static int Compressor;//Pneumatic Compressor
  //public static int IntakeSolenoid1 = 0;//Pneumatic Solenoid
  //public static int IntakeSolenoid2 = 1;//Pneumatic solenoid

  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
