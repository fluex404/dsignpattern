package com.fluex404.structural;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class DecoratorExample {
    private static int choice;
    public static void main(String[] args) throws Exception{

        do{
            System.out.println("=========Food Menu=========");
            System.out.println("1. Vegetarian Foo.");
            System.out.println("2. Non-Vegetarian Food.");
            System.out.println("3. Chineese Food.");
            System.out.println("4. Exit");
            System.out.print("Enter your choose: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            choice = Integer.parseInt(br.readLine());
            switch(choice) {
                case 1:{
                    VegFood vf = new VegFood();
                    System.out.println(vf.prepareFood());
                    System.out.println(vf.foodPrice());
                }break;
                case 2:{
                    Food f1 = new NonVegFood(new VegFood());
                    System.out.println(f1.prepareFood());
                    System.out.println(f1.foodPrice());
                }break;
                case 3:{
                    Food f2 = new ChineeseFood(new VegFood());
                    System.out.println(f2.prepareFood());
                    System.out.println(f2.foodPrice());
                }break;
                default:{
                    System.out.println("Other than these not found available");
                }
                return;
            }
        } while(choice != 4);
    }
}

interface Food{
    String prepareFood();
    double foodPrice();
}
class VegFood implements Food{

    @Override
    public String prepareFood() {
        return "Veg Food";
    }

    @Override
    public double foodPrice() {
        return 50.0;
    }
}
class FoodDecorator implements Food{
    private Food newFood;

    public FoodDecorator(Food newFood){
        this.newFood = newFood;
    }

    @Override
    public String prepareFood() {
        return newFood.prepareFood();
    }

    @Override
    public double foodPrice() {
        return newFood.foodPrice();
    }
}
class NonVegFood extends FoodDecorator{

    public NonVegFood(Food newFood) {
        super(newFood);
    }

    @Override
    public String prepareFood() {
        return super.prepareFood()+" With Roasted Chiken and Chiken Curry";
    }

    @Override
    public double foodPrice() {
        return super.foodPrice()+150.0;
    }
}
class ChineeseFood extends FoodDecorator{

    public ChineeseFood(Food newFood) {
        super(newFood);
    }

    @Override
    public String prepareFood() {
        return super.prepareFood() +" With Fried RIce and Manchurian";
    }

    @Override
    public double foodPrice() {
        return super.foodPrice()+65.0;
    }
}