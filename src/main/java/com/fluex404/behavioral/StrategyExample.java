package com.fluex404.behavioral;

public class StrategyExample {
    public static void main(String[] args) {
        Context cAdd = new Context(new Addition());
        Context cSub = new Context(new Subtraction());
        Context cMul = new Context(new Multiplication());

        System.out.println(cAdd.executeStrategy(2, 3));
        System.out.println(cSub.executeStrategy(2, 3));
        System.out.println(cMul.executeStrategy(2, 3));
    }
}
interface Strategy{
    float calculation(float a, float b);
}
class Addition implements Strategy{

    @Override
    public float calculation(float a, float b) {
        return a+b;
    }
}
class Subtraction implements Strategy{

    @Override
    public float calculation(float a, float b) {
        return a-b;
    }
}
class Multiplication implements Strategy{

    @Override
    public float calculation(float a, float b) {
        return a*b;
    }
}
class Context{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public float executeStrategy(float num1, float num2){
        return strategy.calculation(num1, num2);
    }
}