import Model.Request;

public class ElevatorManagementSystem {

    public static void main(String[] args) {
        ElevatorManager elevatorManager = new ElevatorManager();
        elevatorManager.addRequest(new Request(0,35));
        elevatorManager.addRequest(new Request(0,20));
        elevatorManager.addRequest(new Request(25,0));
        elevatorManager.addRequest(new Request(10,0));
        elevatorManager.addRequest(new Request(0,20));
        elevatorManager.addRequest(new Request(13,0));
        elevatorManager.addRequest(new Request(15,0));
        elevatorManager.addRequest(new Request(0,45));
        elevatorManager.addRequest(new Request(55,0));
    }

}
