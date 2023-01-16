package runner;

import model.Direction;
import model.Elevator;
import model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

import static model.ElevatorState.*;

public class ElevatorRunner extends Thread {

    private Elevator elevator;
    private final Logger logger = LogManager.getLogger(ElevatorRunner.class);

    public ElevatorRunner(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        Queue<Request> queue = new LinkedList<>(elevator.getQueue());

        for (Request request: queue) {

            logger.info("Elevator no: {} is moving from level {} to {}",
                    elevator.getElevatorId(), elevator.getCurrentFloor(), request.getDestinationFloor());

            if (elevator.getCurrentFloor() - request.getDestinationFloor() < 0) {
                elevator.setCurrentDirection(Direction.UP);
            } else {
                elevator.setCurrentDirection(Direction.DOWN);
            }

            try {
                while (elevator.getCurrentFloor() != request.getDestinationFloor()) {

                    if (elevator.getCurrentDirection().equals(Direction.UP)) {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        //for simulating the movement time
                        Thread.sleep(300);
                    } else if (elevator.getCurrentDirection().equals(Direction.DOWN)) {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        Thread.sleep(300);
                    }
                    logger.debug("Elevator no: {} in motion floor {}", elevator.getElevatorId(), elevator.getCurrentFloor());

                }
            } catch (InterruptedException e) {
                logger.error("Exception occurred elevator cannot move!");
            }

            elevator.getQueue().remove(request);
            elevator.setElevatorState(IDLE);

        }
    }
}
