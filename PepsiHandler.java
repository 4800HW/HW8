public class PepsiHandler implements SnackDispenseHandler {
    private SnackDispenseHandler nextHandler;

    @Override
    public void setNextHandler(SnackDispenseHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void dispenseSnack(VendingMachine machine, Snack snack, double money) {
        if (snack.getName().equals("Pepsi")) {
            machine.dispense(snack, money);
        } else if (nextHandler != null) {
            nextHandler.dispenseSnack(machine, snack, money);
        }
    }
}