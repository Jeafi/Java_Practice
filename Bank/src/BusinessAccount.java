public class BusinessAccount extends Account{
    public BusinessAccount(String name, double balance) {
        super(name, balance,10);

    }

    @Override
    public void print() {
        System.out.println("Name: " + name
                + "\tBalance: " + balance
                + "\tRate: " + rate);
    }
}
