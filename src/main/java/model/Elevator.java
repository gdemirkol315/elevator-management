package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import runner.ElevatorRunner;

import java.util.LinkedList;
import java.util.Queue;


import static model.ElevatorState.*;


public class Elevator {

    private String elevatorId;

    private int currentFloor = 0;
    private ElevatorState elevatorState = IDLE;
    private Direction currentDirection;
    private ElevatorRunner elevatorRunner = new ElevatorRunner(this);
    private final Logger logger = LogManager.getLogger(Elevator.class);

    private Queue<Request> queue = new LinkedList<>();

    public Elevator() {
    }

    public Elevator(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    public void move() {

        try {
            if (elevatorRunner.getState() == Thread.State.NEW) {
                elevatorRunner.start();
            } else if (elevatorRunner.getState() == Thread.State.RUNNABLE) {
                synchronized (elevatorRunner) {
                    elevatorRunner.wait();
                }

            } else if (elevatorRunner.getState() == Thread.State.TERMINATED){
                elevatorRunner = new ElevatorRunner(this);
                elevatorRunner.start();
            }
        } catch (Exception e) {
            logger.error("Unexpected thread state {}", elevatorRunner.getState(), e);
        }
    }


    public String getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Queue<Request> getQueue() {
        return queue;
    }
}
