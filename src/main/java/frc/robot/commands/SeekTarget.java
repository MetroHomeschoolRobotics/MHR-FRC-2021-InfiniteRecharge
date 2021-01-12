/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;

public class SeekTarget extends CommandBase {
  DriveSystemBase _tankDrive;
  double _x = 0.25;
  double _y = 0;
  double _z = 0;
  Timer _timer = new Timer();
  double runTime = 0.8;
  /**
   * Creates a new WaitForTarget.
   */
  public SeekTarget(DriveSystemBase tankDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    _tankDrive = tankDrive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _tankDrive.move(_x, _y, _z);
    SmartDashboard.putNumber("SeekTargetTimer", _timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _tankDrive.move(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    limelightTable.getEntry("pipeline").setNumber(0);
    double tv = limelightTable.getEntry("tv").getDouble(0);
    //double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    if(tv == 1 && _timer.get() >= runTime) {
    return true;
    } else {
      return false;
    }
  }
}
