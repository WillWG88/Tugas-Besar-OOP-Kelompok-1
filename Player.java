package Monster;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Thread;

public class Player {
    private int id;
    private String name;
    private Monster currentMonster;
    private MonsterPool allMonsters;

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

    public MonsterPool getMonsterPool(){
        return this.allMonsters;
    }

    // setter
    public void setPlayerID(int id){
        this.id = id;
    }

    public void setPlayerName(String name){
        this.name = name;
    }

    public void setAllMonster(MonsterPool allMonsters){
        this.allMonsters = allMonsters;
    }

    public void setCurrentMonster(Monster currentMonster){
        this.currentMonster=currentMonster;
    }

    public void setMonsterPool(int i, MonsterPool listMonster){
        try {
            List<Monster> copyMonsterPool = listMonster.getListMonster();
            List<Monster> copyList = new ArrayList<Monster>(copyMonsterPool);
            List<Monster> randomized = new ArrayList<Monster>();
            for (int j = 0; j < i; j++){
                int index = new Random().nextInt(copyList.size());
                Monster monster = copyList.get(index);
                randomized.add(monster);
                copyList.remove(index);
            }
            MonsterPool mp = new MonsterPool();
            mp.setListMonster(randomized);
            this.allMonsters = mp;
        } catch (Exception e) {
            System.out.println("Main interrupted");
        }
        System.out.println();
    }

    public void printCurrentMonster(){
        System.out.printf("%s is currently using %s%n", this.getPlayerName(), this.getCurrentMonster().getMonsterName());
    }
}
