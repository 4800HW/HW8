import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private List<Snack> snacks;
    //private SnackDispenseHandler handlerChain;
    private Snack selectedSnack;
    private double insertedMoney;

    public VendingMachine() {
        this.state = new IdleState();
        this.snacks = new ArrayList<>();
        buildHandlerChain();
    }

    private void buildHandlerChain() {
        SnackDispenseHandler coke = new CokeHandler();
        SnackDispenseHandler pepsi = new PepsiHandler();
        coke.setNextHandler(pepsi);
        //this.handlerChain = coke;
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public void selectSnack(String name) {
        for (Snack snack : snacks) {
            if (snack.getName().equals(name) && snack.getQuantity() > 0) {
                this.selectedSnack = snack;
                setState(new WaitingForMoneyState());
                System.out.println("Snack selected: " + name + ". Please insert money.");
                return;
            }
        }
        System.out.println("Snack not available or out of stock.");
        setState(new IdleState());
    }

    public void insertMoney(double amount) {
        if (this.state instanceof WaitingForMoneyState) {
            this.insertedMoney += amount;
            System.out.println("Inserted $" + amount + ". Total inserted: $" + this.insertedMoney);
            if (this.insertedMoney >= selectedSnack.getPrice()) {
                setState(new DispensingState());
                dispense(selectedSnack, this.insertedMoney);
            } else {
                System.out.println("Please insert $" + (selectedSnack.getPrice() - this.insertedMoney) + " more.");
            }
        } else {
            System.out.println("No snack selected. Please select a snack first.");
        }
    }

    public void dispense(Snack snack, double money) {
        if (money >= snack.getPrice() && snack.getQuantity() > 0) {
            snack.setQuantity(snack.getQuantity() - 1);
            this.insertedMoney -= snack.getPrice();
            System.out.println("Dispensing " + snack.getName() + ". Enjoy your snack!");
            if (this.insertedMoney > 0) {
                System.out.println("Returning change: $" + this.insertedMoney);
            }
            this.insertedMoney = 0; // Reset inserted money after transaction
            setState(new IdleState());
        } else {
            System.out.println("Insufficient funds or snack out of stock.");
            if (money > 0) {
                System.out.println("Returning inserted money: $" + money);
                this.insertedMoney = 0; // Reset money as it's being returned
            }
            setState(new IdleState());
        }
    }
}