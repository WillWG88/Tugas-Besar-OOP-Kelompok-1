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
    private boolean isBurn = false;
    private boolean isPoison = false;
    private boolean isSleep = false;
    private boolean isParalyze = false;
    private double HP;
    private double damage;
    private double kecepatan;


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

    public void burn(double healthPoint, double attack) {
        HP = this.healthPoint-this.healthPoint * 0.125;
        damage = 0.5 * this.attack;
        System.out.println("Health Point monster ini menjadi " + HP);
        System.out.println("Attack yang dihasikan monster ini menjadi " + damage);
      }
    
    public void poison(double healthPoint) {
        HP = this.healthPoint- this.healthPoint * 0.0625;
        System.out.println("Health Point monster ini menjadi " + HP);
      }
    
    public void paralyze(double speed) {
        kecepatan = this.speed-this.speed * 0.5;
        System.out.println("Speed monster ini menjadi " + kecepatan);
      }



    