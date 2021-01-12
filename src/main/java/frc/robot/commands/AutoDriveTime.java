/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;

public class AutoDriveTime extends CommandBase {
  DriveSystemBase _tankDrive;
  double _x; //= 0;
  double _y;//= .25;
  double _z;//= 0;
  double _seconds;// = 1.5;
  /**
   * Creates a new AutoDrive.
   */
  public AutoDriveTime(DriveSystemBase tankDrive, double x, double y, double z, double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    _tankDrive = tankDrive;
    _x = x;
    _y = y;
    _z = z;
    _seconds = seconds;
    addRequirements(_tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("AutoDriving", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _tankDrive.move(_x, _y, _z);
    Timer.delay(_seconds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("AutoDriving", false);
    _tankDrive.move(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
