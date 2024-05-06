public class driver {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        
        // add snacks to vending machine
        machine.addSnack(new Snack("Coke", 1.50, 10));
        machine.addSnack(new Snack("Pepsi", 1.50, 10));
        machine.addSnack(new Snack("Cheetos", 1.00, 5));
        machine.addSnack(new Snack("Doritos", 1.00, 5));
        machine.addSnack(new Snack("KitKat", 0.75, 8));
        machine.addSnack(new Snack("Snickers", 0.75, 3));
        
        machine.selectSnack("Snickers");
        machine.insertMoney(1.00);

        // repeat buying Snickers until it runs out
        machine.selectSnack("Snickers");
        machine.insertMoney(0.75);
        machine.selectSnack("Snickers");
        machine.insertMoney(0.75);
        machine.selectSnack("Snickers");
        machine.insertMoney(0.75); // depletes stickers

        machine.selectSnack("Snickers");
        machine.insertMoney(0.75);
    }
}