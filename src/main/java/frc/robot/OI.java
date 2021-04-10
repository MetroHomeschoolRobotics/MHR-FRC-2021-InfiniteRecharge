/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.pixy2.Pixy2;
import java.util.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Pixy2 _i2cPixy2 = null;
  Pixy2 _spiPixy2 = null;

  private DriveSystemBase _tankDrive;
  private Intake _intake;
  private Shooter _shooter;
  private Magazine _magazine;
  private ControlPanel _controlPanel;
  private Transition _transition;
  private IntakeLifter _intakeLifter;
  private Climber _climber;
  private ControlPanelLift _controlPanelLift;
  CommandBase _autonomousCommand;
  CommandBase _driveTank;
  CommandBase _runIntake;
  CommandBase _runShooter;
  CommandBase _runMagazine;
  CommandBase _runControlPanel;
  CommandBase _reverseMagazine;
  CommandBase _reverseIntake;
  CommandBase _driveLimelight;
  CommandBase _runTransition;
  CommandBase _transitionTeleop;
  CommandBase _shootMacro;
  CommandBase _autoDrive;
  CommandBase _moveIntake;
  CommandBase _climbPistons;
  CommandBase _climbWinch;
  CommandBase _driveLimelightTrench;
  CommandBase _seekTarget;
  SendableChooser<CommandBase> _autoChooser = new SendableChooser<>();

  SendableChooser<CommandBase> _i2cPixyChooser = new SendableChooser<>();
  SendableChooser<CommandBase> _spiPixyChooser = new SendableChooser<>();

  public OI(Pixy2 i2cPixy2, Pixy2 spiPixy2, DriveSystemBase tankDrive, Intake intake, Shooter shooter, Magazine magazine, ControlPanel controlPanel, Transition transition, IntakeLifter intakeLifter, Climber climber, ControlPanelLift controlPanelLift){
    _i2cPixy2 = i2cPixy2;
    _spiPixy2 = spiPixy2;
    _tankDrive = tankDrive;
    _intake = intake;
    _shooter = shooter;
    _magazine = magazine;
    _controlPanel = controlPanel;
    _transition = transition;
    _intakeLifter = intakeLifter;
    _climber = climber;
    _controlPanelLift = controlPanelLift;
  }
  
  public void init() {
    Joystick driverControl = new Joystick(0);
    Joystick manipulatorControl = new Joystick(1);

    _driveTank = new DriveTank(_tankDrive, driverControl, manipulatorControl);
    _transitionTeleop = new TransitionTeleop(_transition, manipulatorControl);
    _climbWinch = new ClimbWinch(_climber, driverControl);
    //_runIntake = new RunIntake(_intake, driverControl);
   // _shooterAxis = new Joystick(driverControl, 3);

    JoystickButton intakeButton = new JoystickButton(driverControl, 5);
    intakeButton.whileHeld(new RunIntake(_intake, driverControl));

    JoystickButton shootOnButton = new JoystickButton(manipulatorControl, 6);
    shootOnButton.whenPressed(new RunShooter(_shooter, manipulatorControl));

    JoystickButton reverseIntakeButton = new JoystickButton(driverControl, 6);
    reverseIntakeButton.whileHeld(new ReverseIntake(_intake, driverControl));

    JoystickButton HoodAdjustmentButton = new JoystickButton(driverControl, 8); // Button 8 = Start Button
    HoodAdjustmentButton.whileHeld(new RunControlPanel(_controlPanel));

    JoystickButton targetButton = new JoystickButton(driverControl, 1);
    targetButton.whileHeld(new DriveLimelight(_tankDrive, _shooter, false));

    JoystickButton targetWithHoodButton = new JoystickButton(driverControl, 2);
    targetWithHoodButton.whileHeld(new DriveLimelight(_tankDrive, _shooter, true));

    //JoystickButton targetTrenchButton = new JoystickButton(driverControl, 2);
    //targetTrenchButton.whileHeld(new DriveLimelightTrench(_tankDrive));

    JoystickButton shootMacroButton = new JoystickButton(manipulatorControl, 1);
    shootMacroButton.whenPressed(new ShootMacro(_intake, _magazine, _shooter, _transition));

    JoystickButton cancelAllButton = new JoystickButton(manipulatorControl, 7);
    cancelAllButton.whileHeld(new CancelAll(_controlPanel, _intake, _magazine, _shooter, _tankDrive));
    //syntax from wpilib to make one command run at the end of another: 
    //JoystickButton targetShootButton = new JoystickButton(driverControl, 2);
    //targetShootButton.whenPressed(new DriveLimelight(_tankDrive).whenFinished(() -> new ShootMacro(_intake, _magazine, _shooter, _transition)));
    
    JoystickButton moveIntakeButton = new JoystickButton(driverControl, 4);
    moveIntakeButton.whenPressed(new MoveIntake(_intakeLifter));

    JoystickButton activateClimbPistonsButton = new JoystickButton(driverControl, 10);
    activateClimbPistonsButton.whenPressed(new ClimbPistons(_climber));

    POVButton magazineForwardButton = new POVButton(manipulatorControl, 90, 0);
    magazineForwardButton.toggleWhenPressed(new RunMagazine(_magazine));

    POVButton magazineReverseButton = new POVButton(manipulatorControl, 270, 0);
    magazineReverseButton.toggleWhenPressed(new ReverseMagazine(_magazine));
    
    POVButton liftControlPanelButton = new POVButton(manipulatorControl, 0, 0);
    liftControlPanelButton.whileHeld(new LiftControlPanel(_controlPanelLift));
  
    JoystickButton moveHopperButton = new JoystickButton(driverControl, 10);
moveHopperButton.whenPressed(new MoveHopperDown(_magazine));

    _tankDrive.setDefaultCommand(_driveTank);
    _transition.setDefaultCommand(_transitionTeleop);
    
   if (_i2cPixy2 != null){
      //System.out.println("Adding Dashboard Options...");
      _i2cPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_i2cPixy2));
      _i2cPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_i2cPixy2));
      _i2cPixyChooser.addOption("Find Color Blocks", new FindColorBlocks(_i2cPixy2));
      _i2cPixyChooser.addOption("Lamp Off", new SendLED(_i2cPixy2, false, 0, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Red)", new SendLED(_i2cPixy2, true, 255, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Green)", new SendLED(_i2cPixy2, true, 0, 255, 0));
      _i2cPixyChooser.addOption("Lamp On (Blue)", new SendLED(_i2cPixy2, true, 0, 0, 255));
      _i2cPixyChooser.addOption("Lamp On (Purple)", new SendLED(_i2cPixy2, true, 200, 30, 255));
      SmartDashboard.putData("I2C Pixy Command", _i2cPixyChooser);
      SmartDashboard.putData("Send I2C Command", new ExecuteChooser(_i2cPixyChooser));
    }

    if (_spiPixy2 != null){
      _spiPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_spiPixy2));
      _spiPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_spiPixy2));
      _spiPixyChooser.addOption("Find Color Blocks", new FindColorBlocks(_spiPixy2));
      _spiPixyChooser.addOption("Lamp Off", new SendLED(_spiPixy2, false, 0, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Red)", new SendLED(_spiPixy2, true, 255, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Green)", new SendLED(_spiPixy2, true, 0, 255, 0));
      _spiPixyChooser.addOption("Lamp On (Blue)", new SendLED(_spiPixy2, true, 0, 0, 255));
      _spiPixyChooser.addOption("Lamp On (Purple)", new SendLED(_spiPixy2, true, 200, 30, 255));
      SmartDashboard.putData("SPI Pixy Command", _spiPixyChooser);
      SmartDashboard.putData("Send SPI Command", new ExecuteChooser(_spiPixyChooser));
    }

    _autoChooser.setDefaultOption("3-ball", new SequentialCommandGroup(
      new WaitCommand(0),
      new DriveLimelight(_tankDrive, _shooter, false),
      new ShootMacro(_intake, _magazine, _shooter, _transition),
      new AutoDriveTime(_tankDrive, 0, 0.25, 0, 1.5),
      new MoveIntake(_intakeLifter)));
    _autoChooser.addOption("5-ball", new SequentialCommandGroup(
        new MoveIntake(_intakeLifter),
        new ParallelRaceGroup(
          new WaitCommand(2.2),
          new RunIntake(_intake, driverControl),
          new RunMagazine(_magazine),
          new AutoDriveTime(_tankDrive, 0, -0.4, 0, 2.2)),//time to be lengthened to match actual ball locations
        //new AutoDriveTime(_tankDrive, 0, 0, 0, 0),
        new ParallelRaceGroup(
          new WaitCommand(1),//make wait shorter in final version
          new RunIntake(_intake, driverControl),
          new RunMagazine(_magazine)),  
        new SeekTarget(_tankDrive),
        //may add AutoDriveTime to reach initiation line at full speed, decreasing time required
        new DriveLimelight(_tankDrive, _shooter, false),
        new ShootMacro(_intake, _magazine, _shooter, _transition)));
    _autoChooser.addOption("No auto", new WaitCommand(15));
    _autoChooser.addOption("Drive Array", new AutoDriveArray(_tankDrive, new ArrayList<Double>(Arrays.asList(
      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.00733184814453125, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.09039618328682097, 0.0937751884650102, 0.0937751884650102, 0.0937751884650102, 0.10071919901600568, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.18379937300524496, 0.16070433710710752, 0.033759066298621576, 0.0, 0.035836069410500215, 0.047151095094872364, 0.08707917817350408, 0.13915927138580741, 0.08707917817350408, 0.06277512904249427, 0.06277512904249427, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.00523901082031622, 0.008439766574655394, 0.007936015812903818, 0.01119102285599205, 0.010609771954484093, 0.010609771954484093, 0.010609771954484093, 0.010609771954484093, 0.010609771954484093, 0.013027775657824425, 0.014329777606803096, 0.01785603557903359, 0.018607786717606434, 0.01937503787239736, 0.02176979454337602, 0.02883780880274145, 0.029791060269481306, 0.027900057352219676, 0.033759066298621576, 0.03274381476700938, 0.011787773773718091, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.02607105109720298, 0.04243908781793637, 0.06559613340216863, 0.10974796256965691, 0.11724998813633647, 0.11724998813633647, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.07595789917189855, 0.08221990853997063, 0.08382417312505952, 0.08872993072205348, 0.09039618328682097, 0.05731911042721238, 0.05731911042721238, 0.08872993072205348, 0.012400024707662216, 0.0, -0.00733184814453125, -0.01410675048828125, -0.016143798828125, -0.025665283203125, -0.0274658203125, -0.01685333251953125, -0.0059814453125, 0.009493768146812798, 0.01119102285599205, 0.011787773773718091, 0.011787773773718091, 0.006517762774300745, 0.006517762774300745, 0.006517762774300745, 0.006517762774300745, 0.006076012106754486, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.006866455078125, -0.013458251953125, -0.01685333251953125, -0.022247314453125, -0.022247314453125, -0.0059814453125, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    )),
     new ArrayList<Double>(Arrays.asList(
      0.0, 0.0, 0.0, 0.0, 0.0, -0.006866455078125, -0.0098876953125, -0.017578125, -0.02655792236328125, -0.03632354736328125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.5, -0.5, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.461700439453125, -0.079376220703125, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.005157470703125, -0.02392578125, -0.03955078125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.125, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.12305450439453125, -0.12305450439453125, -0.12305450439453125, -0.125, -0.12305450439453125, -0.11920928955078125, -0.11542510986328125, -0.113555908203125, -0.09741973876953125, -0.08094024658203125, -0.073272705078125, -0.06598663330078125, -0.06317901611328125, -0.061798095703125, -0.06043243408203125, -0.05774688720703125, -0.05512237548828125, -0.04523468017578125, -0.04291534423828125, -0.04065704345703125, -0.037384033203125, -0.03632354736328125, -0.04065704345703125, -0.03955078125, -0.03845977783203125, -0.03845977783203125, -0.03845977783203125, -0.03845977783203125, -0.03632354736328125, -0.03632354736328125, -0.03632354736328125, -0.03632354736328125, -0.033233642578125, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.01785603557903359, 0.09207793586780655, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.1116002294088787, 0.10428420438881192, 0.10428420438881192, 0.10428420438881192, 0.10071919901600568, 0.09895994635392968, 0.0, 0.0, 0.03689782099076666, 0.054684106496818874, 0.06417788121422241, 0.061387876886984216, 0.053389854555949245, 0.03797507258725119, 0.03689782099076666, 0.027900057352219676, 0.02176979454337602, 0.007936015812903818, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
     ))));//TODO: Fix these arrays to be an actual drive routine
    

  SmartDashboard.putData("AutoMode", _autoChooser);


  SmartDashboard.putNumber("Hood Angle", 20);
  SmartDashboard.putNumber("shooter speed (0-1)", .62);
  SmartDashboard.putData("Move Hood", new MoveShooterHoodToPreset(SmartDashboard.getNumber("Hood Angle", 20), _shooter));
}
  
  public CommandBase getAutonomousCommand(){
    return _autoChooser.getSelected();
    // Joystick driverControl = new Joystick(0);
    // return new SequentialCommandGroup(
    //   new MoveIntake(_intakeLifter),
    //   new ParallelRaceGroup(
    //     new AutoDriveTime(_tankDrive, 0, -0.25, 0, 2),//time to be lengthened to match actual ball locations
    //     new RunIntake(_intake, driverControl),
    //     new RunMagazine(_magazine)),
    //   new SeekTarget(_tankDrive),
    //   //may add AutoDriveTime to reach initiation line at full speed, decreasing time required
    //   new DriveLimelight(_tankDrive),
    //   new ShootMacro(_intake, _magazine, _shooter, _transition));
    //   //new AutoDriveTime(_tankDrive, 0, 0.25, 0, 1.5));
  }
  
  public CommandBase getDriveCommand(){
    return _driveTank;
  }


  public double getHoodSmartDashboardAngle() {
    return SmartDashboard.getNumber("Hood Angle", 20);
  }
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by its isFinished method.
  // button.whenReleased(new ExampleCommand());
}
