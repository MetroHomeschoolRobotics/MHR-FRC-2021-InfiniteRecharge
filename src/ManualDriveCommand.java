/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package DriveCommands;

import edu.wpi.first.wpilibj.command.Command;

public class ManualDriveCommand extends Command {
  private DriveSystemBase _driveSystem;
  private Joystick _driverControl;
  private Joystick _manipulatorControl;

  double _threshold = 0.1;
  
  public ManualDriveCommand(DriveSystemBase driveSystem, 
  Joystick driverControl, 
  Joystick manipulatorControl) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _driveSystem = driveSystem;
    _driverControl = driverControl;
    _manipulatorControl = manipulatorControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double total = abs(_driverControl.getRawAxis(0)) +
		  abs(_driverControl.getRawAxis(1)) +
		  abs(_driverControl.getRawAxis(2)) +
		  abs(_driverControl.getRawAxis(3));
	if (total > _threshold*2) {
		double x = _driverControl->GetRawAxis(0);
		double y = _driverControl->GetRawAxis(1);
		if (abs(x) < _threshold){
			x = 0;
		} else if (abs(x) < _threshold * 7){
			x /= 2;
		}
		if (abs(y) < _threshold){
			y = 0;
		} else if (abs(y) < _threshold * 7){
			y /= 2;
		}
		_driveSystem.move(
				x/2,
				y/2,
				_driverControl->GetRawAxis(2) - _driverControl->GetRawAxis(3));
	} else {
		_driveSystem.move(
				_driverControl->GetRawAxis(0)/2,
				_driverControl->GetRawAxis(1)/2,
				_driverControl->GetRawAxis(2) - _driverControl->GetRawAxis(3));
	}
	if (_driverControl->GetRawButton(6)) {
		_driveSystem.move(
				_driverControl->GetRawAxis(0),
				_driverControl->GetRawAxis(1),
				_driverControl->GetRawAxis(2) - _driverControl->GetRawAxis(3));
	}

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
