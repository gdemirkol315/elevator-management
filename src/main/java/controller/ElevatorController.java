package controller;

import model.Direction;
import model.Elevator;
import model.ElevatorState;
import model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class ElevatorController {

    private final Logger logger = LogManager.getLogger(ElevatorController.class);
    private static final int NUMBER_OF_ELEVATORS = 7;
    private static final int NUMBER_OF_FLOORS = 55;

    private List<Elevator> elevators = new LinkedList<>();


    public ElevatorController() {

        for (int i = 0; i < NUMBER_OF_ELEVATORS; i++) {
            Elevator elevator = new Elevator(i + "");
            elevators.add(elevator);
        }

        logger.info("Elevator manager initialized.");

    }

    public void addRequest(Request request) {

        Elevator elevator = findAvailableElevator(request.getCurrentFloor());
        if (elevator.getCurrentFloor() != request.getCurrentFloor()) {
            elevator.getQueue().add(new Request(elevator.getCurrentFloor(), request.getCurrentFloor()));
        }
        elevator.getQueue().add(request);
        elevator.setElevatorState(ElevatorState.IN_MOTION);
        elevator.move(request);


    }

    private Elevator findAvailableElevator(int destinationFloor) {

        List<Elevator> availableElevators = new ArrayList<>();

        for (Elevator elevator : elevators) {

            if (elevator.getElevatorState().equals(ElevatorState.IDLE)) {
                if (elevator.getCurrentFloor() == destinationFloor) {
                    return elevator;
                } else {
                    availableElevators.add(elevator);
                }
            } else if (elevator.getElevatorState() == ElevatorState.IN_MOTION) {
                if (hasSameDirection(elevator, destinationFloor)) {
                    availableElevators.add(elevator);
                }
            }
        }

        return findNearestElevator(availableElevators, destinationFloor);
    }

    private Elevator findNearestElevator(List<Elevator> availableElevators, int destinationFloor) {

        Elevator nearestElevator = new Elevator();
        int shortestPath = 0;

        for (Elevator elevator : availableElevators) {

            int levelDiff = Math.abs(elevator.getCurrentFloor() - destinationFloor);
            if (shortestPath == 0) {
                shortestPath = levelDiff;
            }

            if (levelDiff < shortestPath) {
                shortestPath = levelDiff;
                nearestElevator = elevator;
            }
        }

        if (nearestElevator.getElevatorId() == null) {
            //take random elevator
            int randomNum = ThreadLocalRandom.current().nextInt(0, 7);
            return elevators.get(randomNum);
        }
        return nearestElevator;
    }

    private boolean hasSameDirection(Elevator elevator, int destinationFloor) {

        Direction direction = elevator.getCurrentFloor() > destinationFloor ? Direction.DOWN : Direction.UP;
        Direction elevatorDirection = elevator.getCurrentDirection();
        boolean result = false;

        if (direction == elevatorDirection) {
            result = true;
        }

        return result;
    }

}
