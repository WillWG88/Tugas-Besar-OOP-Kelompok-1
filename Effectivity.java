//file : Effectivity.java
//berisi getter tipe element source,tipe element target, dan efektivitas dari sebuah move dengan tipe elemen source yang digunakan terhadap monster dengan tipe elemen target


public class Effectivity {
    private ElementType source;
    private ElementType target;
    private double effectivity;
    
    //konstruktor
    public Effectivity(ElementType source, ElementType target, double effectivity){
        this.source=source;
        this.target=target;
        this.effectivity=effectivity;
    }

    //return source(enum)
    public ElementType getSource(){
        return this.source;
    }

    //return target(enum)
    public ElementType getTarget(){
        return this.target;
    }

    //return effectivity(double)
    public double getEffectivity(){
        return this.effectivity;
    }
}
