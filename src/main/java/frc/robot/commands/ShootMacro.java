/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveLimelightTrench;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class ShootMacro extends CommandBase {
  private final Intake _intake;
  private final Magazine _magazine;
  private final Shooter _shooter;
  private final Transition _transition;
  private double _shooterSpeed = .6;
  private CommandBase _driveLimelightTrench;
  /**
   * Creates a new ShootMacro.
   */
  public ShootMacro(Intake intake, Magazine magazine, Shooter shooter, Transition transition, CommandBase driveLimelightTrench) {
    // Use addRequirements() here to declare subsystem dependencies.
    _intake = intake;
    _magazine = magazine;
    _shooter = shooter;
    _transition = transition;
    _driveLimelightTrench = driveLimelightTrench;
    
    addRequirements(_intake, _magazine, _shooter, _transition);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("ShootMacro Running", true);
    //CommandScheduler.getInstance().cancel(_driveLimelightTrench);
    //_shooterSpeed = SmartDashboard.getNumber("shooter speed (0-1)", .8);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _shooter.setShooter(_shooterSpeed);// was 0.75; this is the speed the shooter shoots
    Timer.delay(1.25);// time to spin shooter up; TODO: attempt to minimize time taken by this command
    _transition.setTransitionMotor(-.75);
    _magazine.setMagazine(.75);
    _intake.setIntake(-0.8);
    Timer.delay(5);//was 3.5; this is the time to empty the magazine
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _shooter.setShooter(0);
    _transition.setTransitionMotor(0);
    _magazine.setMagazine(0);
    _intake.setIntake(0);
    SmartDashboard.putBoolean("ShootMacro Running", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
