package Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {

    private String id;

    private int currentFloor;
    private Direction currentDirection;
    private final Logger logger = LogManager.getLogger(Elevator.class);

    public Elevator() {
    }

    public Elevator(String id) {
        this.id = id;
        currentFloor = 0;
    }

    public void move(int destinationFloor) {

        logger.info("Elevator no: {} is moving from level {} to {}",id, currentFloor, destinationFloor);
        currentFloor = destinationFloor;

    }

    public String getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
