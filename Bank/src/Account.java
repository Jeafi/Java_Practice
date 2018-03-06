
public abstract class Account {
    String name;
    double balance;
    double rate;

    Account(String name, double balance,double rate) {
        this.name = name;
        this.balance = balance;
        this.rate = rate;
    }

    public abstract  void print();
}
