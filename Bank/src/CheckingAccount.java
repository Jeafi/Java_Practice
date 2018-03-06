public class CheckingAccount extends Account{
    public CheckingAccount(String name, double balance) {
        super(name, balance,1.5);

    }

    @Override
    public void print() {
        System.out.println("Name: " + name
                + "\tBalance: " + balance
                + "\tRate: " + rate);
    }
}
