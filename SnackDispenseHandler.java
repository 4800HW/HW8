public interface SnackDispenseHandler {
    void setNextHandler(SnackDispenseHandler handler);
    void dispenseSnack(VendingMachine machine, Snack snack, double money);
}