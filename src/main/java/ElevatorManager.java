import Model.Elevator;
import Model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class ElevatorManager {

    private final Logger logger = LogManager.getLogger(ElevatorManager.class);
    private final int numberOfElevators = 7;
    private final int numberOfFloors = 55;

    private Set<Elevator> elevators = new HashSet<>();
    //private Queue<Request> requests = new LinkedList<>();


    public ElevatorManager() {

        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i + ""));
        }

        logger.info("Elevator manager initialized.");

    }

    public void addRequest(Request request) {

        Elevator elevator = findNearestElevator(request.getCurrentFloor());
        if (elevator.getCurrentFloor() != request.getCurrentFloor()) {
            elevator.move(request.getCurrentFloor());
        }
        elevator.move(request.getDestinationFloor());

    }

    private Elevator findNearestElevator(int floorNumber) {

        int destination = floorNumber;
        int shortestPath = 0;

        Elevator nearestElevator = new Elevator();

        for (Elevator elevator : elevators) {

            if (elevator.getCurrentFloor() == floorNumber) {
                return elevator;
            }

            int levelDiff = Math.abs(elevator.getCurrentFloor() - destination); //30

            if (shortestPath == 0) {
                shortestPath = levelDiff;
            }

            if (levelDiff < shortestPath) {
                shortestPath = levelDiff; //15
                nearestElevator = elevator; //2
            }
        }

        return nearestElevator;
    }

}
