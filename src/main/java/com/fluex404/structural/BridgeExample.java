package com.fluex404.structural;

import java.util.ArrayList;
import java.util.List;

public class BridgeExample {
    public static void main(String[] args) {
        QuestionFormat questions = new QuestionFormat("Java");
        questions.q = new JavaQuestion();
        questions.delete("what is class ? ");
        questions.display();
        questions.newOne("What is inheritance? ");

        questions.newOne("How many tpes of inheritance are there in java ? ");
        questions.displayAll();
    }
}

interface Quetion{
    void nextQuestion();
    void previousQuestion();
    void newQuestion(String q);
    void deleteQuestion(String q);
    void displayQuestion();
    void displayAllQuestion();
}
class JavaQuestion implements Quetion{

    private List<String> questions = new ArrayList<>();
    private int current = 0;

    public JavaQuestion(){
        questions.add("What is class? ");
        questions.add("What is interface? ");
        questions.add("What is abstraction? ");
        questions.add("How multiple polymorphism is achieved in java? ");
        questions.add("How many types of exception  handling are there in java? ");
        questions.add("Define the keyword final for  variable, method, and class in java? ");
        questions.add("What is abstract class? ");
        questions.add("What is multi-threading? ");
    }

    @Override
    public void nextQuestion() {
        if(current <= questions.size()-1) {
            current++;
            System.out.println(current);
        }
    }

    @Override
    public void previousQuestion() {
        if(current > 0)
            current--;
    }

    @Override
    public void newQuestion(String q) {
        questions.add(q);
    }

    @Override
    public void deleteQuestion(String q) {
        questions.remove(q);
    }

    @Override
    public void displayQuestion() {
        System.out.println(questions.get(current));
    }

    @Override
    public void displayAllQuestion() {
        questions.forEach(System.out::println);
    }
}
class QuestionManager {
    protected Quetion q;
    public String catalog;
    public QuestionManager(String catalog) {
        this.catalog = catalog;
    }
    public void next(){
        q.nextQuestion();
    }
    public void previous(){
        q.previousQuestion();
    }
    public void newOne(String ques){
        q.newQuestion(ques);
    }
    public void delete(String quest){
        q.deleteQuestion(quest);
    }
    public void display() {
        q.displayQuestion();
    }
    public void displayAll(){
        q.displayAllQuestion();
    }
}
class QuestionFormat extends QuestionManager{

    public QuestionFormat(String catalog) {
        super(catalog);
    }
    public void displayAll(){
        System.out.println("------------------------------------");
        super.displayAll();
        System.out.println("------------------------------------");
    }
}