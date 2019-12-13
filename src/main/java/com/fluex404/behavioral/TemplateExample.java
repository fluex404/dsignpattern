package com.fluex404.behavioral;

public class TemplateExample {
    public static void main(String[] args) throws Exception{
        Class c = Class.forName(args[0]);
        Game game = (Game) c.newInstance();
        game.play();
    }
}
abstract class Game{
    abstract void initialize();
    abstract void start();
    abstract void end();

    public final void play(){
        // initialize the game
        initialize();
        // start game
        start();
        // end game
        end();
    }

}
class Chess extends Game{

    @Override
    void initialize() {
        System.out.println("Chess Game Initialize! Start playing.");
    }

    @Override
    void start() {
        System.out.println("Game Started. Welcome to in the chess game!");
    }

    @Override
    void end() {
        System.out.println("Game Finished!");
    }
}
class Soccer extends Game{

    @Override
    void initialize() {
        System.out.println("Soccer Game Initialized! Start playing.");
    }

    @Override
    void start() {
        System.out.println("Game Started Welcome to in the Soccer game!");
    }

    @Override
    void end() {
        System.out.println("Game Finished!");
    }
}

