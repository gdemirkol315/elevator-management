package controller;

import model.Elevator;
import model.ElevatorState;
import model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;


public class ElevatorController {

    private final Logger logger = LogManager.getLogger(ElevatorController.class);
    private static final int NUMBER_OF_ELEVATORS = 7;
    private static final int NUMBER_OF_FLOORS = 55;

    private Set<Elevator> elevators = new HashSet<>();
    //private Queue<Request> requests = new LinkedList<>();


    public ElevatorController() {

        for (int i = 0; i < NUMBER_OF_ELEVATORS; i++) {
            Elevator elevator = new Elevator(i + "");
            elevators.add(elevator);
        }

        logger.info("Elevator manager initialized.");

    }

    public void addRequest(Request request) {

        Elevator elevator = findNearestElevator(request.getCurrentFloor());
        elevator.move(request.getDestinationFloor());


    }

    private Elevator findNearestElevator(int floorNumber) {

        int destination = floorNumber;
        int shortestPath = 0;

        Elevator nearestElevator = new Elevator();

        for (Elevator elevator : elevators) {

            if (elevator.getCurrentFloor() == floorNumber
                    && elevator.getElevatorState().equals(ElevatorState.IDLE)) {
                return elevator;
            }

            int levelDiff = Math.abs(elevator.getCurrentFloor() - destination);

            if (shortestPath == 0) {
                shortestPath = levelDiff;
            }

            if (levelDiff < shortestPath) {
                shortestPath = levelDiff;
                nearestElevator = elevator;
            }
        }

        return nearestElevator;
    }

}
