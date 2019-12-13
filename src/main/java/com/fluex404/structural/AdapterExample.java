package com.fluex404.structural;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdapterExample {
    public static void main(String[] args) {
        CreditCard targetInterface = new BankCustomer();
        targetInterface.giveBankDetails();
        System.out.println(targetInterface.getCreditCard());
    }
}
/* target interface */
interface CreditCard{
    void giveBankDetails();
    String getCreditCard();
}
/* adaptee class */
class BankDetails{
    private String bankName;
    private String accHolderName;
    private long accNumber;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(long accNumber) {
        this.accNumber = accNumber;
    }
}
/* adapter class */
class BankCustomer extends BankDetails implements CreditCard{

    @Override
    public void giveBankDetails() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the account holder name: ");
            String customeName = br.readLine();
            System.out.print("Enter the account number : ");
            long accNo = Long.parseLong(br.readLine());
            System.out.print("Enter the bank name: ");
            String bankName = br.readLine();

            setAccHolderName(customeName);
            setAccNumber(accNo);
            setBankName(bankName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCreditCard() {
        long accNo = getAccNumber();
        String accHolderName = getAccHolderName();
        String bName = getBankName();

        return ("The Account number "+accNo+" of "+accHolderName+" in "+bName+
                " bank is valid and authenticated for asssuing the credit card.");
    }
}
