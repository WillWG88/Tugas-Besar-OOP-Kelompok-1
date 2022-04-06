
class Player{
    String name;
    double health;
    int level;

    //object member
    Weapon weapon;
    Armor armor;

    //constructor
    Player(String name, double health){
        this.name = name;
        this.health = health;
    }
    
    void equipWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    void equipArmor(Armor armor){
        this.armor = armor;
    }

    void display(){
        System.out.println("Name : " + this.name);
        System.out.println("Health : " + this.health);
        this.weapon.display();
        this.armor.display();
    }
}

class Weapon{
    String name;
    double attackPower;

    Weapon(String name, double attackPower){
        this.name = name;
        this.attackPower = attackPower;
    }

    void display(){
        System.out.println("Weapon : " + this.name + ", power : " + this.attackPower);
    }
}

class Armor{
    String name;
    double deffensePower;

    Armor(String name, double deffensePower){
        this.name = name;
        this.deffensePower = deffensePower;
    }

    void display(){
        System.out.println("Armor : " + this.name + ", deffense : " + this.deffensePower);
    }
}
