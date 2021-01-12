package frc.robot.commands;

import java.util.*;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.pixy2.Pixy2CCC.Block;
import frc.robot.pixy2.Pixy2;
import frc.robot.pixy2.Pixy2CCC;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
//import frc.robot.commands.*;




public class ControlPanelTest {

    int halfRevCounter;
    int transitionCounter;
    int currentColor = 0;
    int lastColor = 0;
    int controlPanelDeployDirection=0;
    int taskToDo = 0;
    int deployCommand; //test bit to simulate signal from controller
    int taskCompleted = 0;
    int myCommand;
    Scanner console = new Scanner(System.in);
    int allDone = 0;
    int firstColor = 1;
    int myTargetColor = 1;
    int modifiedTargetColor=3;
    String gameData;
  
    public ControlPanel _controlPanel;



    public int deployControlPanelControl(int direction) {
        // drive the control panel control wheel into position
        if (direction == 1) {
            // move the control controller into position
            initiatePixy(); // should be a 1, 2, 3, or 5;
        }
        else if (direction == 2) {
            // move the motor to home
        }
        else {
            // do nothing for all other cases
        }
        return 1;
    }

    public void initiatePixy() {
        // simulate the pixy getting the current color
       // myBlock = (int) (4 * (Math.random())) + 1;
        firstColor = getBlock();
        currentColor = firstColor;
        lastColor = firstColor;
        halfRevCounter = 0;
        transitionCounter = 0;
            
        gameData = DriverStation.getInstance().getGameSpecificMessage();
if(gameData.length() > 0)
{
  switch (gameData.charAt(0))
  {
    case 'B' :
      myTargetColor=5;
//      System.out.println("Game Data BLUE");
      break;
    case 'G' :
      //Green case code
      myTargetColor=3;
      break;
    case 'R' :
      //Red case code
      myTargetColor=2;
      break;
    case 'Y' :
      //Yellow case code
      myTargetColor=1;
      break;
    default :
      //This is corrupt data
//      System.out.println("Game Data BLANK");
      break;
  }
} else {
  //Code for no data received yet
//  System.out.println("Game data NULL");
}
}
    
    

    public int getBlock() {
        // simulate the pixy getting the current color
        int myBlock = FindColorBlocks.currentColor; 

        return myBlock;
    }

 



    public int turnThreeTimes() {
        // simulate the pixy getting the current color
        System.out.println("In the method");
        int inPosition = 0;
        while (/*transitionCounter >= 1 && */halfRevCounter < 7){
            // turn the motor on
          // RunControlPanel.execute();
            currentColor = getBlock();
            System.out.println(currentColor);
            if (currentColor != lastColor) {
                transitionCounter++;
                lastColor = currentColor;
                if (currentColor == firstColor) {
                    halfRevCounter++;
                    System.out.println("Rev Count is " + halfRevCounter);
                }
            }

        }
        System.out.println("Finished");
        // stop the motor
        inPosition = 1;
        return inPosition;
    }


    public int positionPanel() {
        // simulate the pixy getting the current color
     //   System.out.println("In the position method");
        int inPosition = 0;
        while (/*transitionCounter >= 1 && */inPosition != 1) {
            switch (myTargetColor){
                case 1: 
                    modifiedTargetColor=3;
                    break;
                case 2: 
                    modifiedTargetColor=5;
                    break;
                case 3: 
                    modifiedTargetColor=1;
                    break;
                case 5: 
                    modifiedTargetColor=2;
                    break;
                default: 
                    modifiedTargetColor=myTargetColor + 2;
                    break;
            }

            // turn the motor on
            currentColor = getBlock();
            System.out.println(currentColor);
            if (currentColor == modifiedTargetColor) {
                inPosition=1;
                System.out.println("Target color is " + myTargetColor + " and I am on " + currentColor);
                
            }

        }
        System.out.println("Finished");
        // stop the motor
        inPosition = 1;
        return inPosition;
    }



    public void runIt (int depCom) {
        // deploy the control panel controller deployControlPanelControl();

        // choose the task to do... turns or position

        // get the initial color to establish current position

        // do appropriate task
        // turn the wheel 3.5 to 4.5 turns
        // position the wheel at the appropriate color
        // turn the wheel to get the color rate and establish the location of the
        // transitions.
        // calculate where to put the wheel
        // reposition the wheel

        // retract the controller
        deployCommand = depCom;

        //if (deployCommand!=4){

        switch (deployCommand) {
            case 1: taskCompleted = deployControlPanelControl(1);
                if (taskCompleted == 1) {
                    System.out.println("Deployed");
                    taskCompleted = 0;
                }
                break;
    
            case 2: System.out.println("Turn 3 times");
                taskCompleted = turnThreeTimes();
                taskCompleted = deployControlPanelControl(2);
                if (taskCompleted == 1) {
                    System.out.println("Retract");
                    taskCompleted = 0;
                    allDone = 1;
                    System.out.println("All Done");
                }
                break;
        
            case 3: System.out.println("Position the Wheel");
                taskCompleted = positionPanel();       
                taskCompleted = deployControlPanelControl(2);
                    if (taskCompleted == 1) {
                        System.out.println("Retract");
                        taskCompleted = 0;
                        allDone = 1;
                        System.out.println("All Done");
                    }
                break;
            
            case 4: taskCompleted = deployControlPanelControl(2);
                if (taskCompleted == 1) {
                    System.out.println("Retract");
                    taskCompleted = 0;
                    allDone = 1;
                }
                break;
            case 5: System.out.println("All Done");
                allDone = 1;
                break;        
            default: System.out.println("Something Else");
                break;
        }
    //}

    }

    }
