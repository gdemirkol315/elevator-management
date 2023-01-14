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


    public Elevator() {
    }

    public Elevator(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    public void move(int destinationFloor) {

        ElevatorRunner elevatorRunner = new ElevatorRunner(this, destinationFloor);
        elevatorState = IN_MOTION;
        elevatorRunner.start();

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

}
