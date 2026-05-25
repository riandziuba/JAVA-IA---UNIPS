package main;

import core.BankAccount;
import core.SpecialAccount;
import core.Product;

public class MainClass {
    public void main(String[] args) {
        Product product = new Product(123, "Computador", 2000F, 2);

        BankAccount bankAccount = new BankAccount(123, "Isidro");
        BankAccount bankAccount2 = new SpecialAccount(124, "Rose", 200);

        bankAccount.credit(100);
        bankAccount2.credit(100);

        System.out.println(bankAccount.toString());
        System.out.println(bankAccount2.toString());

        this.makeDebitWithMessage(bankAccount, 150);
        this.makeDebitWithMessage(bankAccount2, 150);
    }

    public void makeDebitWithMessage(BankAccount account, double value) {
        if (account.debt(value)) {
            System.out.println("Débito Efetuado");
            System.out.println(account);
        } else {
            System.out.println("Saldo insuficiente para conta " +  account.getNumber());
        }
    }
}
