package runner;

import model.Direction;
import model.Elevator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static model.ElevatorState.IDLE;

public class ElevatorRunner extends Thread {

    private Elevator elevator;
    private int destinationFloor;
    private final Logger logger = LogManager.getLogger(ElevatorRunner.class);

    public ElevatorRunner(Elevator elevator, int destinationFloor) {
        this.elevator = elevator;
        this.destinationFloor = destinationFloor;
    }

    @Override
    public void run() {
        logger.info("Elevator no: {} is moving from level {} to {}",
                elevator.getElevatorId(), elevator.getCurrentFloor(), destinationFloor);

        if (elevator.getCurrentFloor() - destinationFloor < 0) {
            elevator.setCurrentDirection(Direction.UP);
        } else {
            elevator.setCurrentDirection(Direction.DOWN);
        }

        try {
            while (elevator.getCurrentFloor() != destinationFloor) {

                if (elevator.getCurrentDirection().equals(Direction.UP)) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    //for simulating the movement time
                    Thread.sleep(500);
                } else if (elevator.getCurrentDirection().equals(Direction.DOWN)) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    Thread.sleep(500);
                }

                logger.debug("Elevator no: {} in motion floor {}", elevator.getElevatorId(), elevator.getCurrentFloor());

            }
        } catch (InterruptedException e) {
            logger.error("Exception occurred elevator cannot move!");
        } finally {
            elevator.setElevatorState(IDLE);
        }
    }
}
