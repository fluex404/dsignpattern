package com.fluex404.structural;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class FacadeExample {
    private static int choice;
    public static void main(String[] args) throws Exception{
        do{
            System.out.print("========= Mobile Shop ============ \n");
            System.out.print("            1. IPHONE.              \n");
            System.out.print("            2. SAMSUNG.              \n");
            System.out.print("            3. BLACKBERRY.            \n");
            System.out.print("            4. Exit.                     \n");
            System.out.print("Enter your choice: ");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            choice=Integer.parseInt(br.readLine());

            ShopKeeper sk = new ShopKeeper();
            switch(choice) {
                case 1: sk.iphoneSale();break;
                case 2: sk.samsungSale();break;
                case 3: sk.blackberrySale();break;
                default:
                    System.out.println("Nothing you purchased");
                    return;
            }
        }while(choice != 4);
    }
}
interface MobileShop{
    void modelNo();
    void price();
}
class Iphone implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("Iphone 6");
    }

    @Override
    public void price() {
        System.out.println("1000");
    }
}
class Samsung implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("Samsung galaxy tab 3");
    }

    @Override
    public void price() {
        System.out.println("500");
    }
}
class Blackberry implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("Blackberry Z10");
    }

    @Override
    public void price() {
        System.out.println("200");
    }
}
class ShopKeeper{
    private MobileShop iphone;
    private MobileShop samsung;
    private MobileShop blackberry;

    public ShopKeeper(){
        iphone = new Iphone();
        samsung = new Samsung();
        blackberry = new Blackberry();
    }
    public void iphoneSale(){
        iphone.modelNo();
        iphone.price();
    }
    public void samsungSale(){
        samsung.modelNo();
        samsung.price();
    }
    public void blackberrySale(){
        blackberry.modelNo();
        blackberry.price();
    }
}