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
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.Timer;
import java.util.*;  

public class AutoDriveArray extends CommandBase {
  DriveSystemBase _tankDrive;

private int _index = 0;
//private ArrayList<Double> movementArrayX = new ArrayList<Double>(Arrays.asList(.1,.1,.1,.1,.1));//TODO: Change this value to be the actual driver joystick values
//private ArrayList<Double> movementArrayY = new ArrayList<Double>(Arrays.asList(.1,.1,.1,.1,.1));//TODO: Change this value to be the actual driver joystick values
private ArrayList<Double> _movementArrayX;
private ArrayList<Double> _movementArrayY;
  /**
   * Creates a new AutoDrive.
   */
  public AutoDriveArray(DriveSystemBase tankDrive, ArrayList<Double> movementArrayX, ArrayList<Double> movementArrayY) {
    // Use addRequirements() here to declare subsystem dependencies.
    _tankDrive = tankDrive;

    
    
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
      _tankDrive.move(_movementArrayX.get(_index), _movementArrayY.get(_index),0);
      _index+=1;
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
    if(_index >= _movementArrayX.size()) {
      return true;
    } else {
      return false;
    }
  }
}
