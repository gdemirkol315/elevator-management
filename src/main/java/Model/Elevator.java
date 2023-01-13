package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Elevator {

    private String id;

    private int currentFloor = 0;
    private State state = State.IDLE;
    private Direction currentDirection;
    private final Logger logger = LogManager.getLogger(Elevator.class);

    public Elevator() {
    }

    public Elevator(String id) {
        this.id = id;
    }

    public void move(int destinationFloor) {

        logger.info("Elevator no: {} is moving from level {} to {}", id, currentFloor, destinationFloor);

        if (currentFloor - destinationFloor < 0) {
            currentDirection = Direction.UP;
        } else {
            currentDirection = Direction.DOWN;
        }

        try {
            while (currentFloor != destinationFloor) {
                state = State.IN_MOTION;

                if (currentDirection.equals(Direction.UP)) {
                    currentFloor++;
                    Thread.sleep(500);
                } else if (currentDirection.equals(Direction.DOWN)) {
                    currentFloor--;
                    Thread.sleep(500);
                }

                logger.debug("Elevator no: {} in motion floor {}", id, currentFloor);

            }
            state = State.IDLE;
        } catch (InterruptedException e) {
            logger.error("Exception occurred elevator cannot move!");
        }

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
