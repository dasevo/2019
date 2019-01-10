package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Ypid implements PIDSource
{
    private double[] visionTable = new double[5];
    public Ypid(double[] visionTable)
    {
        this.visionTable=visionTable; //imports the table with vision values from Robot class
    }

    public PIDSourceType getPIDSourceType()
    {
        return PIDSourceType.kDisplacement;
    }

    public void setPIDSourceType(PIDSourceType type)
    {
        
    }

    public double pidGet()
    {
        return visionTable[2]; //change this to getting values from robot.java
    }
}