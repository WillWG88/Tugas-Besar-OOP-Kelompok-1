// Hanya dipengaruhi oleh base Power dari Move untuk damage calcu
// mirip NormalMove
package Moves;

public class SpecialMove extends Move {
    private int basepower;
    // Asumsi: semua special move target ke lawan (bukan self)

    // Konstruktor
    public SpecialMove (int id, String moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, String target, int basepower) {
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.basepower = basepower;
    }

    // Getter
    public int getBasePower() {
        return this.basepower;
    }

    public void print(){
        super.print();
        System.out.println("Base power: " + this.basepower);
        System.out.println();
    }
}
