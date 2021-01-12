/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.pixy2.links;

// Checksum holder class
public class Checksum {

    int cs = 0;

    /**
     * Adds byte to checksum
     * 
     * @param b Byte to be added
     */
    public void updateChecksum(int b) {
        cs += b;
    }

    /**
     * Returns calculated checksum
     * 
     * @return Calculated checksum
     */
    public int getChecksum() {
        return cs;
    }

    /**
     * Resets checksum
     */
    public void reset() {
        cs = 0;
    }

}