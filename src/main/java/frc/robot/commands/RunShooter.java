/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.RobotController;

public class RunShooter extends CommandBase {
  private final Shooter _shooter;
  private final Joystick _manipulatorControl;
  private double _shooterSpeed = .62;
  private double _regularVoltage = 12.5;

  /**
   * Creates a new RunShooter.
   */
  public RunShooter(Shooter shooter, Joystick manipulatorControl) {
    // Use addRequirements() here to declare subsystem dependencies.
    _shooter = shooter;
    _manipulatorControl = manipulatorControl;
    addRequirements(_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    //_shooterSpeed = SmartDashboard.getNumber("shooter speed (0-1)", .62);
    SmartDashboard.putBoolean("Shooting", true);
    _manipulatorControl.setRumble(RumbleType.kLeftRumble, 0); //TODO turn on for compeition. 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double _batteryVoltage = RobotController.getBatteryVoltage();
    _shooter.setShooter(_shooterSpeed/*(_batteryVoltage/_regularVoltage)*/);//was 0.75
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Shooting", false);
    _shooter.setShooter(0);
    _manipulatorControl.setRumble(RumbleType.kLeftRumble, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if(_manipulatorControl.getRawButton(5)){
      return true;
    } else {*/
    return false;
  //}
}
}
