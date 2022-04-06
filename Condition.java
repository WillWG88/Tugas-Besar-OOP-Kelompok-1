public class Condition extends Stats{
        double HP;
        double damage;
        double kecepatan;

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

}