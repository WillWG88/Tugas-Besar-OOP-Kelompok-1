import java.io.File;
import java.util.*;

public class Main{
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/MonsterPool.csv",
            "configs/MovePool.csv",
            "configs/ElementTypesEffectivity.csv"));

    public static void main(String[] args){
       //menyimpan move dalam ArrayList
        ArrayList<Move> moveList= new ArrayList<Move>();
        //menyimpan monster dalam ArrayList
        ArrayList<Monster>monsterList=new ArrayList<Monster>();
        //Effectivity pool
        EffectivityPool effectivityPool=new EffectivityPool();

        //Membaca CSV file dengan separator ;
        try{
            //membaca config file movepool
            CSVReader reader=new CSVReader(new File(Main.class.getResource("configs/MovePool.csv").toURI()),";");
            reader.setSkipHeader(true);
            List<String[]>lines=reader.read();
            //inisialisasi setiap baris dari file MovePool.csv
            for(String[]line:lines){
                int id=Integer.parseInt(line[0]); //line id
                String moveType=line[1]; //line moveType
                String name=line[2]; //line name
                ElementType elementType=ElementType.valueOf(line[3]); //line elementType
                int accuracy=Integer.parseInt(line[4]); //line accuracy
                int priority=Integer.parseInt(line[5]); // line priority
                int ammunition=Integer.parseInt(line[6]);//line ammunition
                // melakukan pengecekan tipe move
                if(moveType.equals("NORMAL")){
                    int effect=Integer.parseInt(line[8]);
                    NormalMove normal= new NormalMove(id, moveType, name,elementType,accuracy, priority,ammunition,target,basepower);
                    moveList.add(normal);
                }
                else if(moveType.equals("SPECIAL")){
                    int effect=Integer.parseInt(line[8]);
                    SpecialMove special= new SpecialMove (id, moveType, name, elementType, accuracy, priority, ammunition,target, basepower);
                    moveList.add(special);
                }

                else if(moveType.equals("STATUS")){
                    String target=line[7];
                    String status=line[8];
                    Condition statusCondition;
                    if(status.equals("-")){
                        statusCondition=Condition.NONE;
                    }
                    else{
                        statusCondition=Condition.valueOf(line[8]);
                    }
                    //........

                    Stats movestat= new Stats(healthPoint, attack,defense,  specialAttack, specialDefense,speed);
                    StatusMove statusMove =new StatusMove (id, moveType, name,  elementType, accuracy, priority, ammunition, target, statusEffect, hpEff,attEff, defEff, spAttEff, spDefEff, spdEff);
                    moveList.add(statusMove);
                }
            }
            
            //baca config file monsterpool
            CSVReader reader1 = new CSVReader(new File(Main.class.getResource("configs/MonsterPool.csv").toURI()), ";");
            reader1.setSkipHeader(true);
            List<String[]> lines1 =reader.read();
            //inisialisasi setiap baris dari file MonsterPool.csv
            for(String[] line:lines1){
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String element = line[2];
                String baseStats = line[3];
                String moves = line[4];

                //object element Type
                ArrayList<ElementType> elementType=new ArrayList<ElementType>();
                //array of elementType
                String[] ArrelementType=element.split(",",7);
                //iterasi for each elemen dari array
                for(String type: ArrelementType){
                    switch(type){
                        case("NORMAL"):
                            elementType.add(ElementType.NORMAL);
                            break;
                        case ("FIRE") :
                            elementType.add(ElementType.FIRE);
                            break;
                        case ("WATER") : 
                            elementType.add(ElementType.WATER);
                            break;
                        case ("GRASS") : 
                            elementType.add(ElementType.GRASS);
                            break;
                    }
                }

                ArrayList<Double> stats= new ArrayList<Double>();
                String[] ArrStats =baseStats.split(",",7);
                for(String a: ArrStats){
                    Double d=Double.parseDouble(a);
                    stats.add(d);
                }
                Stats basestats=new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
                
                Monster newmonster = new Monster(id,name,elementType,basestats);

                for(String moves:move){
                    newmonster.getMoveId().add(Integer.parseInt(moves)-1);
                }
                //menambahkan monster baru 
                monsterList.add(newmonster);

            }

            //membaca config file ElementTypesEffectivity
            CSVReader reader2=new CSVReader(new File(Main.class.getResource("configs/ElementTypesEffectivity.csv").toURI()), ";");
            reader2.setSkipHeader(true);
            List<String[]> lines2=reader2.read();
            for(String[] line : lines2){
                ElementType source;
                ElementType target;
                try{
                    source=ElementType.valueOf(line[0]);
                    target=ElementType.valueOf(line[1]);
                    double effectivity=Double.parseDouble(line[2]);
                    effectivityPool.add(source,target,effectivity);
                }
                catch(exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
        catch(Exception e){
        }

        //mulai permainan
        boolean endGame=false;
        while(!endGame){
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("===========Selamat datang di game Monster Saku===========");
            System.out.println();
        
            //pilih menu game
            System.out.println("Silahkan pilih menu game di bawah ini");
            System.out.println();
            System.out.println("1. START");
            System.out.println("2. HELP");
            System.out.println("3. EXIT");
            System.out.println();
            //menerima input dari user
            System.out.printf("Silahkan pilih menu: ");
            int menu= input.nextInt();
            System.out.println();

            while(menu<1 || menu>3){
                System.out.printf("Masukkan lagi menu yang valid : ");
                menu=input.nextInte();
                System.out.println();
            }

            if(menu==1){
                //pemain1
                System.out.printf("Masukkan nama Pemain 1 : ");
                //menerima input nama pemain 1
                String name1=input.nextLine();
                //pemain2
                System.out.printf("Masukkan nama Pemain 2 : ");
                //menerima input nama pemain 2
                String name2=input.nextLine();
                
                ArrayList<Monster> monsterpemain1=new ArrayList<Monster>();
                ArrayList<Monster> monsterpemain2=new ArrayList<Monster>();
                String file1="configs/MonsterPool.csv";
                String file2="configs/MovePool.csv";

                try{
                    //membaca file 1 dan file 2
                    CSVReader reader1= new CSVReader(new File(Main.class.getResource(file1).toURI()),";");
                    CSVReader reader2=new CSVReader(new File(Main.class.getResource(file2).toURI()),";");
                    reader1.setSkipHeader(true);
                    reader2.setSkipHeader(true);
                }


            }
        }
    }
}