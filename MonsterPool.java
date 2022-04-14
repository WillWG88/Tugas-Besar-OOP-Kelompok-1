package Monster;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MonsterPool {
    private List<Monster> listMonster;

    public MonsterPool(){
        List<Monster> listMonster = new ArrayList<Monster>();
        this.listMonster = listMonster;
    }

    //getter
    public List<Monster> getListMonster(){
        return this.listMonster;
    }

    public int getSize(){
        return listMonster.size();
    }

    //setter
    public void setListMonster(List<Monster> LM){
        this.listMonster = LM;
    }
    public void addMonster(Monster monster){
        (this.listMonster).add(monster);
    }

    public void printMonsterPool(){
        for (Monster monster : listMonster){
            System.out.printf("%d %s%n", monster.getID(), m.getNama());
        }
    }

    public void printWithMove(){
        for (Monster monster : listMonster){
            System.out.printf("%d %s%n", monster.getID(), monster.getID());
            monster.printMove();
        }
    }

    public void remove(Monster monster){
        this.listMonster.remove(monster);
    }

    public void printMonster(){
        int i = 1;
        for (Monster monster : listMonster){
            System.out.printf("%d %s%n", i, monster.getNama());
            i++;
        }
    }
    
    public List<Monster> pickRandom(int i, MonsterPool listMonster){
        Random randomGenerator = new Random();
        List<Monster> copyMonsterPool = listMonster.getListMonster();
        List<Monster> copyList = new ArrayList<Monster>(copyMonsterPool);
        List<Monster> randomized = new ArrayList<Monster>();
        for (int j = 0; j < i; j++){
            int index = randomGenerator.nextInt(copyList.size());
            Monster monster = copyList.get(index);
            randomized.add(monster);
            copyList.remove(index);
        }
        return randomized;
    }
}

