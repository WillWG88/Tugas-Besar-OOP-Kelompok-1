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
        ArrayList<Monster> monsterList=new ArrayList<Monster>();
        //menyimpan effectivity dalam list
        private static Effectivity listEffectivity = new Effectivity();
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
                    String moveStatus = line[8];
                    String moveStats = line[9];
                    String[] arrofmovestats = moveStats.split(",",6);
                    Double hp = Double.parseDouble(arrofmovestats[0]);
                    Double atk = Double.parseDouble(arrofmovestats[1]);
                    Double def = Double.parseDouble(arrofmovestats[2]);
                    Double spcAtk = Double.parseDouble(arrofmovestats[3]);
                    Double spcDef = Double.parseDouble(arrofmovestats[4]);
                    Double speed = Double.parseDouble(arrofmovestats[5]);
                    Stats stat = new Stats(hp,atk,def,spcAtk,spcDef,speed);

                    StatusMove status = new StatusMove(id, name, elType, accuracy, priority, ammunition,target, moveStatus, stat);
                    moveList.add(status);
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
                String stat = line[3];
                String move = line[4];
                
                //masukkan ke objek
                ArrayList<Double> stats = new ArrayList<Double>();
                String[] arrofstats = stat.split(",",7);
                for(String a : arrofstats){
                    Double d = Double.parseDouble(a);
                    // System.out.println(d);
                    stats.add(d);
                }
                Stats basestats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));

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
                ArrayList<Move> monstermove = new ArrayList<Move>();
                String[] arrofmove=move.split(",",7);
                //iterasi
                for(int i = 0; i < arrofmove.length; i++){
                    Move origin = listMoves.get(Integer.valueOf(arrofmov[i])-1);
                    Move copy = (Move)origin.clone();
                    monstermove.add(copy);
                }
                
                DefaultMove default =new DefaultMove();
                monstermove.add(default);
                
                //membuat monster baru
                Monster newmonster = new Monster(id,name,elementType,basestats,monstermove);
                monsterList.add(newmonster);


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
            for (String[]line:lines2){
                //source 
                String source=line[0];
                //target
                String target=line[1];

                Double p=Double.parseDouble(line[2]);
                ElementType so =ElementType.NORMAL;
                ElementType ta= ElementType.NORMAL;
                switch (source){
                    case ("NORMAL"):
                        so = ElementType.NORMAL;
                        break;
                    case ("FIRE"):
                        so = ElementType.FIRE;
                        break;  
                    case ("WATER"):
                        so = ElementType.WATER;
                        break; 
                    case ("GRASS"):
                        so = ElementType.GRASS;
                        break; 
                }
                switch (target){
                    case ("NORMAL"):
                        ta = ElementType.NORMAL;
                        break;
                    case ("FIRE"):
                        ta = ElementType.FIRE;
                        break;  
                    case ("WATER"):
                        ta = ElementType.WATER;
                        break; 
                    case ("GRASS"):
                        ta = ElementType.GRASS;
                        break; 
                }
                KeyElementEffectivity sourcetarget = new KeyElementEffectivity(so,ta);
                listEffectivity.add(sourcetarget,p);
            }
        }
        catch (Exception e){
            System.err.println("Error");
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
                menu=input.nextInt();
                System.out.println();
            }

            if(menu==1){
                //Permainan dimulai 
                System.out.println("Permainan akan dimulai....");
                System.out.println("");
                //pemain1
                System.out.printf("Masukkan nama Pemain 1 : ");
                //menerima input nama pemain 1
                String name1=input.nextLine();
                //pemain2
                System.out.printf("Masukkan nama Pemain 2 : ");
                //menerima input nama pemain 2
                String name2=input.nextLine();
                System.out.println("Nama Pemain 1 : "+name1);
                System.out.println("Nama Pemain 2 : "+name2);

                //Memilih secara random monster yang akan digunakan
                Random random= new Random();
                Integer upperbound =monsterList.size();
                ArrayList<Monster> monsterPemain1=new ArrayList<Monster>();
                for(int i=0;i<6;i++){
                    Integer monsterRandom=random.nextInt(upperbound);
                    Monster Monster1=monsterList.get(monsterRandom);
                    while (monsterPemain1.contains(Monster1)){
                        monsterRandom=random.nextInt(upperbound);
                        Monster1=monsterList.get(monsterRandom);
                    }

                    monsterPemain1.add(Monster1);
                }
                ArrayList<Monster> monsterPemain2=new ArrayList<Monster>();
                for(int i=0;i<6;i++){
                    Integer monsterRandom=random.nextInt(upperbound);
                    Monster Monster2=monsterList.get(monsterRandom);
                    while (monsterPemain1.contains(Monster1)){
                        monsterRandom=random.nextInt(upperbound);
                        Monster2=monsterList.get(monsterRandom);
                    }

                    monsterPemain2.add(Monster2);
                }

                
                


                ArrayList<Monster> monsterpemain2=new ArrayList<Monster>();
                String file1="configs/MonsterPool.csv";
                String file2="configs/MovePool.csv";

                try{
                    //membaca file 1 dan file 2
                    CSVReader reader1= new CSVReader(new File(Main.class.getResource(file1).toURI()),";");
                    CSVReader reader2=new CSVReader(new File(Main.class.getResource(file2).toURI()),";");
                    reader1.setSkipHeader(true);
                    reader2.setSkipHeader(true);
                    Random random=new Random();
                    List<String[]> lines1=reader1.read();
                    List<String[]> lines2=reader2.read();

                }
            }

            else if (menu==2){
                Instruction instruction =new Instruction();
                instruction.getHelp();
            }

            else if(menu==3){
                System.out.println("Anda sudah keluar dari aplikasi. Terima kasih telah bermain");
                //menutup aplikasi
                input.close;
            }
        }
    }
}