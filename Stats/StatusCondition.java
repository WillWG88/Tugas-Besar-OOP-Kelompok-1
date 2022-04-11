//file StatusCondition.java

//enum class
public enum StatusCondition{
    NONE("NONE");
    BURN("BURN");
    POISON("POISON");
    SLEEP("SLEEP");
    PARALYZE("PARALYZE");

    private String name;

    public StatusCondition(String name){
        this.name=name;
    }
    public getName(){
        return this.name;
    }
}