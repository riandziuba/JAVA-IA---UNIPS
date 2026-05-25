package core;

public class SpecialAccount extends BankAccount {
    private double limit;
    public SpecialAccount(int number, String owner, double limit) {
        super(number, owner);
        this.limit = limit;
    }

    @Override
    public boolean debt(double value) {
        if (super.balance + this.limit >= value) {
            super.balance -= value;
            return true;
        }

        return false;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "EspecialAccount{" +
                "limit=" + limit +
                ", number=" + number +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}
