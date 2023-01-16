package model;

public class Request {

    private int currentFloor;
    private int destinationFloor;
    private Direction direction;

    public Request(int currentFloor, int destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        setDirection();
    }

    private void setDirection(){
        if (destinationFloor > currentFloor){
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}
