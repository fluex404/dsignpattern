package com.fluex404.creational;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.ArrayList;
import java.util.List;

public class BuilderExample {
    public static void main(String[] args) {
        CDBuilder cdBuilder = new CDBuilder();
        CDType cdType1 = cdBuilder.buildSonyCD();
        CDType cdType2 = cdBuilder.buildSamsungCD();

        cdType1.showItems();
        cdType2.showItems();
    }
}

interface Packing{
    String pack();
    int price();
}
abstract class CD implements Packing{
    public abstract String pack();
}
abstract class Company extends CD{
    public abstract int price();
}
class Sony extends Company{

    public String pack() {
        return "Sony CD";
    }

    public int price() {
        return 20;
    }
}
class Samsung extends Company{

    public String pack() {
        return "Samsung CD";
    }

    public int price() {
        return 15;
    }
}
class CDType{
    private List<Packing> items = new ArrayList<>();
    public void addItem(Packing packs) {
        items.add(packs);
    }
    public void getCost(){
        items.forEach(p -> p.price());
    }
    public void showItems(){
        items.forEach(p -> {
            System.out.print("CD name: "+p.pack());
            System.out.println(", Price: "+p.price());
        });
    }
}

class CDBuilder{
    public CDType buildSonyCD(){
        CDType cds = new CDType();
        cds.addItem(new Sony());
        return cds;
    }
    public CDType buildSamsungCD(){
        CDType cds = new CDType();
        cds.addItem(new Samsung());
        return cds;
    }
}