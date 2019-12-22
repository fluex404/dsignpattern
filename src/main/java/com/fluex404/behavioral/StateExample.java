package com.fluex404.behavioral;


public class StateExample {
    Controller controller;
    // the following trigger should be made by the user
    StateExample(String con ){
        controller = new Controller();

        switch(con){
            case "management":
                controller.setManagementConnection();
                break;
            case "sales":
                controller.setSalesConnection();
                break;
            case "accounting":
                controller.setAccountingConnection();
                break;
        }

        controller.open();
        controller.log();
        controller.close();
        controller.update();
    }
    public static void main(String[] args) {
        new StateExample("management");
    }
}

interface Connection{
    void open();
    void close();
    void log();
    void update();
}
class Accounting implements Connection{

    @Override
    public void open() {
        System.out.println("open database for accounting");
    }

    @Override
    public void close() {
        System.out.println("close the database");
    }

    @Override
    public void log() {
        System.out.println("log activities");
    }

    @Override
    public void update() {
        System.out.println("Accounting has been updated");
    }
}
class Sales implements Connection{

    @Override
    public void open() {
        System.out.println("open database for Sales");
    }

    @Override
    public void close() {
        System.out.println("close the database");
    }

    @Override
    public void log() {
        System.out.println("log activities");
    }

    @Override
    public void update() {
        System.out.println("Accounting has been updated");
    }
}
class Management implements Connection{

    @Override
    public void open() {
        System.out.println("open database for management");
    }

    @Override
    public void close() {
        System.out.println("close the database");
    }

    @Override
    public void log() {
        System.out.println("log activities");
    }

    @Override
    public void update() {
        System.out.println("Accounting has been updated");
    }
}
class Controller{
    public static Accounting acct;
    public static Sales sales;
    public static Management management;

    private static Connection con;

    Controller(){
        acct = new Accounting();
        sales = new Sales();
        management = new Management();
    }

    public void setAccountingConnection(){
        con = sales;
    }
    public void setManagementConnection(){
        con = management;
    }
    public void setSalesConnection(){
        con = sales;
    }

    public void open(){
        con.open();
    }
    public void close(){
        con.close();
    }
    public void log(){
        con.log();
    }
    public void update(){
        con.update();
    }
}
