package com.fluex404.other;


import java.sql.SQLOutput;

public class DependencyInjectionExample {
    public static void main(String[] args) {
        String msg = "Hi Isa";
        String email = "isaabqari@gmail.com";
        String phone = "0123123123";

        MessageServiceInjector injector = null;
        Consumer app = null;

        // Send Email
        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessage(msg, email);

        // Send SMS
        injector = new SMSServiceInjector();
        app = injector.getConsumer();
        app.processMessage(msg, phone);

    }
}

// service component
interface MessageService{
    void sendMessage(String msg, String rec);
}
class EmailServiceImpl implements MessageService{

    @Override
    public void sendMessage(String msg, String rec) {
        // this is the logic
        System.out.println("send email "+msg+" to "+rec);
    }
}
class SMSServiceImpl implements MessageService{

    @Override
    public void sendMessage(String msg, String rec) {
        // this is the logic
        System.out.println("send SMS "+msg+" to "+rec);
    }
}

// consumer
interface Consumer{
    void processMessage(String msg, String rec);
}
class ConsumerImpl implements Consumer{

    MessageService service;

    public ConsumerImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public void processMessage(String msg, String rec) {
        this.service.sendMessage(msg, rec);
    }
}

// injector
interface MessageServiceInjector{
    Consumer getConsumer();
}
class EmailServiceInjector implements MessageServiceInjector{

    @Override
    public Consumer getConsumer() {
        return new ConsumerImpl(new EmailServiceImpl());
    }
}
class SMSServiceInjector implements MessageServiceInjector{

    @Override
    public Consumer getConsumer() {
        return new ConsumerImpl(new SMSServiceImpl());
    }
}
