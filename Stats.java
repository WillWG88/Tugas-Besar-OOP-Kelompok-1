// spesifikasi stats 
//stats adalah kumpulan statistik yang menggambarkan sebuah monster pada permainan saku

public class Stats{
    // atribut Stats
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    //konstruktor 
    public Stats(double healthPoint, double attack, double defense, double specialAttack, double specialDefense,double speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
}
    //getter
    public gethealthPoint(double healthPoint){
        this.healthPoint=healthPoint;
    }

    public double getattack(double attack) {
        return this.attack;
      }
    
    public double getdefense(double defense) {
        return this.defense;
      }
    
    public double getspecialAttack(double specialAttack) {
        return this.specialAttack;
      }
    
    public double getspecialDefense(double specialDefense) {
        return this.specialDefense;
      }
    
    public double getspeed(double speed) {
        return this.speed;
      }
    
    //setter
    public void sethealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
      }
    
    public void setattack(double attack) {
        this.attack = attack;
      }
    
    public void setdefense(double defense) {
        this.defense = defense;
      }
    
    public void setspecialAttack(double specialAttack) {
        this.specialAttack = specialAttack;
      }
    
    public void setspecialDefense(double specialDefense) {
        this.specialDefense = specialDefense;
      }
    
    public void setSpeed(double speed) {
        this.speed = speed;
      }



    