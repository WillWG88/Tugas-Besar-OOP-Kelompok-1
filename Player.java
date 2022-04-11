

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Thread;

public class Player {
    private int id;
    private String name;
    private Monster currentMonster;

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

    // getter
    public int getPlayerID(){
        return this.id;
    }

    public String getPlayerName(){
        return this.name;
    }

    public Monster getCurrentMonster(){
        return this.currentMonster;
    }

    // setter
    public void setPlayerID(int id){
        this.id = id;
    }

    public void setPlayerName(String name){
        this.name = name;
    }

    