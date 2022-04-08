import java.io.file;
import java.util.*;

public class Main{
    public static void main(String[] args){
       //menyimpan move dalam ArrayList
        ArrayList<Move> moveList= new ArrayList<Move>();
        //menyimpan monster dalam ArrayList
        ArrayList<Monster>monsterList=new ArrayList();
        //Effectivity pool
        EffectivityPool effectivityPool=new EffectivityPool();

        //Membaca CSV file dengan separator ;
        try{
            //membaca config file movepool
            CSVReader reader=new CSVReader(new File(Main.class.getResource("configs/MovePool.csv").toURI()),";");
            reader.setSkipHeader(true);
            List<String[]>lines=reader1.read();
            //inisialisasi setiap baris dari file MovePool.csv
            for(String[]line:lines){
                int id=Integer.parseInt(line[0]); //line id
                String moveType=line[1]; //line moveType
                String name=line[2]; //line name
                ElementType elementType=ElementType.valueOf(line[3]); //line elementType
                int accuracy=Integer.parseInt(line[4]); //line accuracy
                int priority=Integer.parseInt(line[5]); // line priority
                int ammunition=Integer.parseInt(line[6]);//line ammunition
                
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


                    StatusMove (id, moveType, name,  elementType, accuracy, priority, ammunition, target, statusEffect, hpEff,attEff, defEff, spAttEff, spDefEff, spdEff);
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

                //object

                
            }

        }




        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Selamat datang di game Monster Saku");
        
        //pilih menu
        System.out.println("Silahkan pilih menu game");
        String menu= input.nextLine();

        if (menu.equals("StartGame")){
            System.out.println(1);
        }

        else if(menu.equals("Help")){
            System.out.println(2);
        }

        else if(menu.equals("Exit")){
            System.out.println(3);
        }

        else if(menu.equals("ViewMonstersInfo")){
            System.out.println(4);
        }

        else if(menu.equals("ViewGameInfo")){
            System.out.println(5);
        }

    }
}
