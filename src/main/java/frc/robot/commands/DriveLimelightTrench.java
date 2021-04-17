/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;
// import frc.robot.subsystems.DriveSystemBase;
// import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class DriveLimelightTrench extends CommandBase {
  //pull data from network tables (communication protocol)

  DriveSystemBase _tankDrive;
  NetworkTable _limelightTable;
  double KpAim = -.04;//was .01
  double KpDistance = -.1;
  double min_aim_command = .055;
  double min_drive_command = .05;
  double aim_threshold = 1.5;
  double max_aim_threshold = .09;
  boolean done = false;
  double distance_threshold = 1.5;
  double tx_finishThreshold = 1.35;
  double ty_finishThreshold = 1;
  int finished_times = 0;
  /**
   * Creates a new DriveLimelight.
   */
  public DriveLimelightTrench(DriveSystemBase tankDrive) {
    _tankDrive = tankDrive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tankDrive);
    _limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished_times = 0;
    SmartDashboard.putBoolean("Beginning DriveLimelight", true);
        //change limelight to vision processing mode
        _limelightTable.getEntry("camMode").setNumber(0);
        _limelightTable.getEntry("ledMode").setNumber(3);
        _limelightTable.getEntry("pipeline").setNumber(0);
        SmartDashboard.putBoolean("Lined Up", false);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double tx = _limelightTable.getEntry("tx").getDouble(0.0);
    double ty = _limelightTable.getEntry("ty").getDouble(0.0);
    double tv = _limelightTable.getEntry("tv").getDouble(0.0);
    double heading_error = -tx;
    double distance_error = -ty;
    double steering_adjust = 0.0;
    /*if(tx>aim_threshold) {
      steering_adjust = KpDistance*heading_error - min_aim_command;
    } else if( tx<-aim_threshold) {
      steering_adjust = KpDistance*heading_error + min_aim_command;
    }*/
    //double distance_adjust = 0.0;
    /*if(ty>1) {
      distance_adjust = KpAim*distance_error - min_drive_command;
    } else if( ty<-1) {
      distance_adjust = KpAim*distance_error + min_drive_command;
    }*/
    double distance_adjust = 0;
    if(tv == 0) {
      steering_adjust = -.25;
       distance_adjust = 0;
    } else {
    steering_adjust = KpAim*heading_error + min_drive_command;
     distance_adjust = KpDistance*distance_error + min_drive_command;  
     if(steering_adjust>max_aim_threshold) {
      steering_adjust = max_aim_threshold;
    } else if(steering_adjust<-max_aim_threshold) {
      steering_adjust = -max_aim_threshold;
    }
  }
    
SmartDashboard.putNumber("Steering Adjust", steering_adjust);

    double left_command = -steering_adjust;
    double right_command =steering_adjust;
    
    /*double distance_adjust = 0.0;

    if(distance_error < -distance_threshold) {

    } else if(distance_error>distance_threshold) {
       distance_adjust = -.15;
    }*/
    SmartDashboard.putNumber("Distance adjust", distance_adjust);
    //_tankDrive.moveTank(-left_command, right_command);
_tankDrive.move(steering_adjust,distance_adjust,0);
//distance_adjust
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //_limelightTable.getEntry("camMode").setNumber(1);
    //_limelightTable.getEntry("ledMode").setNumber(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    double tx = _limelightTable.getEntry("tx").getDouble(0.0);
    double ty = _limelightTable.getEntry("ty").getDouble(0.0);
    if(finished_times> 25) {
      _tankDrive.move(0,0,0);
      return true;
      
    } else{
    if(ty!= 0 && tx!=0) {
      
  if(Math.abs(ty)<ty_finishThreshold && Math.abs(tx)<tx_finishThreshold){
    SmartDashboard.putBoolean("Lined Up", true);
    finished_times+=1;
return false;
  } else {
    SmartDashboard.putBoolean("Lined Up", false);
    return false;
  }
} else {
  return false;
}
  }
} 
}