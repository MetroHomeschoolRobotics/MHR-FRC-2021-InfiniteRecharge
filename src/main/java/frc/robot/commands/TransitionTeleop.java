/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transition;

public class TransitionTeleop extends CommandBase {
  private Transition _transition;
  private final Joystick _manipulatorControl;
  private int helixSpeed = 0.8;
  /**
   * Creates a new TransitionTelop.
   */
  public TransitionTeleop(Transition transition, Joystick manipulatorControl) {
    _transition = transition;
    _manipulatorControl = manipulatorControl;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(_transition);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //axis 2 and 3 are POV hat on manipulator controller
    if ((_manipulatorControl.getRawAxis(2) < helixSpeed && _manipulatorControl.getRawAxis(3) < helixSpeed) 
          || (_manipulatorControl.getRawAxis(2) > helixSpeed && _manipulatorControl.getRawAxis(3) > helixSpeed)) {
      //if both axes are pressed, they cancel: 
            _transition.setTransitionMotor(0);
    } else if (_manipulatorControl.getRawAxis(3) >= helixSpeed) { 
      _transition.setTransitionMotor(helixSpeed);
    } else if (_manipulatorControl.getRawAxis(2) >= helixSpeed) {
      _transition.setTransitionMotor(-helixSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
