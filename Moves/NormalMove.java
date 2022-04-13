// Hanya dipengaruhi oleh base Power dari Move untuk damage calcu
package Moves;

public class NormalMove extends Move {
    private int basepower;

    // Konstruktor
    public NormalMove (int id, String moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, int basepower) {
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.basepower = basepower;
    }

    // Getter
    public int getBasePower() {
        return this.basepower;
    }

    // Other methods
    public void print(){
        super.print();
        System.out.println("Base power: " + this.basepower);
        System.out.println();
    }
}