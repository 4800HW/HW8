public class IdleState implements StateOfVendingMachine {
    public void handleRequest(VendingMachine machine) {
        System.out.println("Idle state: Waiting for snack selection.");
    }
}