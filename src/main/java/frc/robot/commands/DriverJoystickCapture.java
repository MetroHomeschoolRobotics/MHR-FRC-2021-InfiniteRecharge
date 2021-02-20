// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import java.util.Arrays;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Joystick;


/*This command is intended to capture the driver joystick input value 10 times per second and save it to an array. 
The array will then be output in a format that can be saved to a file in the end method and used to replay driver inputs during autonomous routines.*/
public class DriverJoystickCapture extends CommandBase {
  /** Creates a new DriverJoystickCapture. */
  //create private
  private Joystick _driverControl;
  private double[][] _dataArray;
private int _arrayIndex = 0;
private double _threshold = .2;
  public DriverJoystickCapture(Joystick driverControl) {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  private double[] getValues() {
    //This code uses the joystick to get the driver joystick's inputs. This code should be a copy of the code in DriveTank.java
    
    double x = _driverControl.getRawAxis(0);
    double y = _driverControl.getRawAxis(1);
    SmartDashboard.putNumber("Joystick X", x);
    SmartDashboard.putNumber("Joystick Y", y);
    if (Math.abs(x) < _threshold){
      x = 0;
    } /*else if (Math.abs(x) < _threshold * 2){
      x /= 2;
    }*/
    if (Math.abs(y) < _threshold){
      y = 0;
    }
    if (x<0) {
      x=-(x*x);
    } else if (x >=0) {
      x = (x*x);
    }
    if (y<0) {
      y = -(y*y);
    } else if (y >= 0) {
      y = (y*y);
    }
    if (_driverControl.getRawAxis(2) > 0.8) {
      x = x/2;
      y = y/2;
    }else if (_driverControl.getRawAxis(3) > 0.8) {
      x = 2*x;
      y = 2*y;
    }
    return new double[]{x,y};
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _dataArray[_arrayIndex] = getValues();
_arrayIndex+=1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //get data to Drive Station
    //Maybe use SmartDashboard?
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
