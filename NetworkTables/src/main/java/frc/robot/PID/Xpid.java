package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Xpid implements PIDSource
{
    private double[] visionTable = new double[5];
    public Xpid(double[] visionTable)
    {
        this.visionTable=visionTable;
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
        return visionTable[1]; //returns xValue from the table
    }
}