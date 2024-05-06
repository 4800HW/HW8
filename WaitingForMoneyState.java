public class WaitingForMoneyState implements StateOfVendingMachine {
    public void handleRequest(VendingMachine machine) {
        System.out.println("Insert money to continue.");
    }
}