public class Atm {
    public void change( Account a,double balance) {
        System.out.println("Name: " + a.name
                + "\tBalance: " + a.balance
                + "\tRate: " + a.rate);
        a.balance=balance;


    }
}
