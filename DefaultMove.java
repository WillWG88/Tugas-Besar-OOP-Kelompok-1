// Default move tidak extends move karena tidak punya ammunition
package Moves;

public abstract class DefaultMove {
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;

    // Konstruktor
    public DefaultMove() {
        this.name = "Default Move";
        this.elementType =  ElementType.NORMAL;
        this.accuracy = 100;
        this.priority = 0;
    }

    // Getter
    public String getMoveName() {
        return this.name;
    }

    public ElementType getMoveElType() {
        return this.elementType;
    }

    public int getMoveAccuracy() {
        return this.accuracy;
    }

    public int getMovePriority() {
        return this.priority;
    }
}
