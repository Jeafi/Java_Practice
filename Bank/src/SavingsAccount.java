public class SavingsAccount extends Account{
    public SavingsAccount(String name, double balance) {
        super(name, balance,5);

    }

    @Override
    public void print() {
        System.out.println("Name: " + name
                + "\tBalance: " + balance
                + "\tRate: " + rate);
    }
}
