package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//allows automatic motions
import edu.wpi.first.wpilibj.Timer;

public class LauncherSubsystem extends SubsystemBase{

    Spark m_motor1;
    Spark m_motor2;
    Timer timer;
    boolean debounce;

    public LauncherSubsystem() {
        m_motor1 = new Spark(8);
        m_motor2 = new Spark(9);
        timer = new Timer();
        debounce = false;
    }

    //wind up m_motor1 to pull in note
    public void intakePull() {
        if(debounce == false){
            m_motor1.set(-0.25);
            timer.reset();
            timer.start();
        }
    }

    //use both m_motor1 and m_motor2 to push out note
    public void launch() {
        if(debounce == false){
            debounce = true;
            m_motor1.set(0.30);
            m_motor2.set(0.30);
            timer.reset();
            timer.start();
        }
    }

    @Override
    public void periodic() {
        if(timer.hasElapsed(1)){
            timer.stop();
            timer.reset();
            m_motor1.set(0.0);
            m_motor2.set(0.0);
            debounce = false;
        }
    }
}
