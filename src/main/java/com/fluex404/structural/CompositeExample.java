package com.fluex404.structural;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeExample {
    public static void main(String[] args) {
        Employee emp1 = new Cashier(101, "Iman" ,2000.0);
        Employee emp2 = new Cashier(102, "Kamal", 2500.0);
        Employee emp3 = new Accountant(103, "Tara", 3000.0);
        Employee manager1 = new BankManager(100, "Ashwani Rajput", 10000);

        manager1.add(emp1);
        manager1.add(emp2);
        manager1.add(emp3);

        manager1.print();
    }
}

interface Employee{
    int getId();
    String getName();
    double getSalary();
    void print();
    void add(Employee employee);
    void remove(Employee employee);
    Employee getChild(int i);
}
class BankManager implements Employee{

    private int id;
    private String name;
    private double salary;

    public BankManager(int id, String name ,double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    List<Employee> employees = new ArrayList<>();

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("=======================");
        System.out.println("Id = "+getId());
        System.out.println("Name = "+getName());
        System.out.println("Salary = "+getSalary());
        System.out.println("=======================");

        Iterator<Employee> it = employees.iterator();
        while(it.hasNext()) {
            Employee employee = it.next();
            employee.print();
        }
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public Employee getChild(int i) {
        return employees.get(i);
    }
}
class Cashier implements Employee{
    /**
     * In this class, there are many methods wich are not applicable to cachier because
     * it is a leaf node
     * @return
     */
    private int id;
    private String name;
    private double salary;

    public Cashier(int id, String name, double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("==========================");
        System.out.println("Id ="+getId());
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("==========================");
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public void remove(Employee employee) {

    }

    @Override
    public Employee getChild(int i) {
        return null;
    }
}
class Accountant implements Employee{
    /**
     * In this class, there are many methods wich are not applicable to cashier because
     * it is a leaf node.
     * @return
     */
    private int id;
    private String name;
    private double salary;

    public Accountant(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("==========================");
        System.out.println("Id ="+getId());
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("==========================");
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public void remove(Employee employee) {

    }

    @Override
    public Employee getChild(int i) {
        return null;
    }
}