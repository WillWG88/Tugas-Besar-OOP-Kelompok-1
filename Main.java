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

        //memulai permainan
        boolean endGame=false;
    
        Scanner input = new Scanner(System.in);
        Scanner action = new Scanner(System.in);
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
        
        while(menu!=3){
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
                MonsterPool pool1= new MonsterPool();
                pool1.setListMonster(monsterPemain1);

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
                MonsterPool pool2=new MonsterPool();
                pool2.setListMonster(monsterPemain2);

                Player pemain1=new Player(1,name1);
                Player pemain2=new Player(2,name2);
                pemain1.setAllMonster(pool1);
                pemain2.setAllMonster(pool2);
                pemain1.setCurrentMonster(pemain1.getMonsterPool().get(0));
                pemain2.setCurrentMonster(pemain2.getMonsterPool().get(0));
                //
                Monster p1ActiveMonster= pemain1.getCurrentMonster();
                Monster p2ActiveMonster= pemain2.getCurrentMonster();

                boolean pemain1ValidMove=true;
                boolean pemain2ValidMove=true;
                int p1PilMove = -1;
                int p2PilMove = -1;

                //melakukan loop giliran
                while(!endGame){
                    //inisialisasi 
                    boolean pemain1CanMove=true;
                    boolean pemain2CanMove=true;
                    pemain1ValidMove=false;
                    pemain2ValidMove=false;

                    turn=turn+1;
                    //mengecek condition sleep semua monster pemain 1 dan pemain 2
                    for(int i=0;i<pool1.getSize();i++){
                        if()
                    }

                    for(int i=0;i<pool2.getSize();i++){

                    }

                    //mengecek condition burn semua monster pemain 1 dan pemain 2
                    for (int i=0;i<pool1.getSize();i++){
                        if (pemain1.getMonsterPool().get(i).getIsBurn()){   
                            Condition.Burn(pemain1.getMonsterPool().get(i));
                        }    
                    }
                    for (int i=0;i<pool2.getSize();i++){
                        if (pemain2.getMonsterPool().get(i).getIsBurn()){   
                            Condition.Burn(pemain2.getMonsterPool().get(i));
                        }  
                    }
                    
                    //mengecek condition poison semua monster pemain 1 dan pemain 2
                    for (int i=0;i<pool1.getSize();i++){
                        if (pemain1.getMonsterPool().get(i).getIsPoison()){   
                             Condition.Poison(pemain1.getMonsterPool().get(i));
                        }    
                    }
                    for (int i=0;i<pool2.getSize();i++){
                        if (pemain2.getMonsterPool().get(i).getIsPoison()){   
                             Condition.Poison(pemain2.getMonsterPool().get(i));
                        }  
                    }
                    
                    
                    //Pemain 1 menentukan Turn 
                    boolean turnFinished=false;
                    //loop
                    while(!turnFinished){
                        System.out.printf("Player 1 Active Monster : %s%n", p1ActiveMonster.getNama());
                        System.out.println("1. Move");
                        System.out.println("2. Switch");
                        System.out.println("3. View Current Monsters Info");
                        System.out.println("4. View Current Game Info");
                        System.out.printf("Select action : ");
                        //user memilih aksi yang akan dilakukan 
                        int select1 = action.nextInt();
                        //jika user memilih move
                        if(select1==1){
                            //Mengecek monster aktif sedang sleep atau paralyzed
                            if(p1ActiveMonster.IsSleep()){
                                System.out.println("Monster yang aktif sedang sleep sehingga tidak bisa melakukan move, sekarang giliran Player lain turn");
                            }
                            else{
              
                                //mengecek status paralyzed
                                if(p1ActiveMonster.IsParalyze()){
                                    //monster yang terkena status paralyze akan menyebabkan speed turun 50% kemudian terdapat kemungkinan monster tidak bisa bergerak satu giliran sebesar 25%
                                    StatusCondition.paralyze(p1ActiveMonster);
                                    int rand_int=random.nextInt(4);
                                    if(rand_int==0){
                                        pemain1CanMove=false;
                                    }
                                }
                                    if(pemain1CanMove){
                                        System.out.printf("%s move : %n", p1ActiveMonster.getNama());
                                        for (int i = 0; i < p1ActiveMons.getMoves().size(); i++){
                                            System.out.printf("%d. %s%n", i+1,p1ActiveMonster.getMoves().get(i).getMoveName())
                                        }

                                        //memilih move
                                        while(!pemain1ValidMove){
                                            //input pilihan move
                                            System.out.printf("Pilih Move : ");
                                            int pilihmove = action.nextInt();
                                            //mengecek tipe move yang diinput
                                            if(pilihmove < 1 || pilihmove > p1ActiveMonster.getMoves().size()){
                                                System.out.println("Move tidak ada dalam pilihan");
                                            }
                                            else if ((p1ActiveMonster.getMoves().get(pilihmove -1 ).getMoveAmmunition() ==0)){
                                                System.out.println("Amunisi habis.");
                                            }
                                            else{
                                                p1PilMove=pilihmove-1;
                                                pemain1ValidMove= true;
                                                System.out.println(p1ActiveMonster.getMoves().get(p1PilMove).getMoveName() + " telah dipilih");
                                            }
                                            
                                        }


                                    }
                                    else{
                                        System.out.println("tidak bisa melakukan move karena efek 25% paralyze aktif");
                                    }
                                }
                                turnFinished=true;
                            }

                            //switch pokemon
                            else if (select1 == 2){
                                p1.printMonsters();
                                System.out.println("Choose Id Monster : ");
                                int sel = scan.nextInt();
                                
                                // bikin reset status buff untuk monster yang diswitch
    
                                // ngerubah aktif monster
                                p1ActiveMonster = pemain1.getMonsterPool().get(sel-1);
                                System.out.printf("Player 1 Active Monster : %s%n", p1ActiveMonster.getNama);
                                turnFinished = true;
                            }
                            //view current monster status
                            else if (select1 == 3){
                                p1ActiveMons.printStats();
                            }
                            else if (select1 == 4){
                                System.out.printf("Turn sekarang adalah : %d%n",turn );
                                System.out.println("Monster Player 1 yg aktif : "+ p1ActiveMonster.getNama());
                                System.out.println("Monster Player 1 yg tidak aktif :");
                                System.out.println("");
                                for(int a = 0; a < pemain1.getMonsterPool().size() ; a++){
                                    if(pemain1.getMonsterPool().get(a).getId() != p1ActiveMonster.getId()){
                                        System.out.println(p1.getMonsterPool().get(a).getNama());
                                    }
                                }
                                System.out.println("");
                            }
                        }

                    //Pemain 2 menentukan Turn 
                    turnFinished=false;
                    //loop
                    while(!turnFinished){
                        System.out.printf("Player 2 Active Monster : %s%n", p2ActiveMonster.getNama());
                        System.out.println("1. Move");
                        System.out.println("2. Switch");
                        System.out.println("3. View Current Monsters Info");
                        System.out.println("4. View Current Game Info");
                        System.out.printf("Select action : ");
                        //user memilih aksi yang akan dilakukan 
                        int select2 = action.nextInt();
                        //jika user memilih move
                        if(select2==1){
                            //Mengecek monster aktif sedang sleep atau paralyzed
                            if(p2ActiveMonster.IsSleep()){
                                System.out.println("Monster yang aktif sedang sleep sehingga tidak bisa melakukan move, sekarang giliran Player lain turn");
                            }
                            else{
              
                                //mengecek status paralyzed
                                if(p2ActiveMonster.IsParalyze()){
                                    //monster yang terkena status paralyze akan menyebabkan speed turun 50% kemudian terdapat kemungkinan monster tidak bisa bergerak satu giliran sebesar 25%
                                    StatusCondition.paralyze(p2ActiveMonster);
                                    int rand_int=random.nextInt(4);
                                    if(rand_int==0){
                                        pemain2CanMove=false;
                                    }
                                }
                                    if(pemain2CanMove){
                                        System.out.printf("%s move : %n", p2ActiveMonster.getNama());//getName nama dari monster
                                        for (int i = 0; i < p1ActiveMons.getMoves().size(); i++){
                                            System.out.printf("%d. %s%n", i+1,p2ActiveMonster.getMoves().get(i).getMoveName())
                                        }

                                        //memilih move
                                        while(!pemain2ValidMove){
                                            //input pilihan move
                                            System.out.printf("Pilih Move : ");
                                            int pilihmove = action.nextInt();
                                            //mengecek tipe move yang diinput
                                            if(pilihmove < 1 || pilihmove > p2ActiveMonster.getMoves().size()){
                                                System.out.println("Move tidak ada dalam pilihan");
                                            }
                                            else if ((p2ActiveMonster.getMoves().get(pilihmove -1 ).getMoveAmmunition() ==0)){
                                                System.out.println("Amunisi habis.");
                                            }
                                            else{
                                                p2PilMove=pilihmove-1;
                                                pemain1ValidMove= true;
                                                System.out.println(p2ActiveMonster.getMoves().get(p2PilMove).getMoveName() + " telah dipilih");
                                            }
                                            
                                        }


                                    }
                                    else{
                                        System.out.println("tidak bisa melakukan move karena efek 25% paralyze aktif");
                                    }
                                }
                                turnFinished=true;
                            }

                            //switch pokemon
                            else if (select2 == 2){
                                pemain2.printMonsters(); //masih kurang......................
                                System.out.println("Choose Id Monster : ");
                                int sel = scan.nextInt();
                                
                                // bikin reset status buff untuk monster yang diswitch
    
                                // ngerubah aktif monster
                                p2ActiveMonster = pemain2.getMonsterPool().get(sel-1);
                                System.out.printf("Player 2 Active Monster : %s%n", p2ActiveMonster.getNama());
                                turnFinished = true;
                            }
                            //view current monster status
                            else if (select2 == 3){
                                p2ActiveMonster.printStats();
                            }
                            else if (select2 == 4){
                                System.out.printf("Turn skrng : %d%n",turn );
                                System.out.println("Monster Player 2 yg aktif : "+ p2ActiveMonster.getNama());
                                System.out.println("Monster Player 2 yg tidak aktif :");
                                System.out.println("");
                                for(int a = 0; a < pemain2.getMonsterPool().size() ; a++){
                                    if(pemain2.getMonsterPool().get(a).getId() != p2ActiveMonster.getId()){
                                        System.out.println(p2.getMonsterPool().get(a).getNama());
                                    }
                                }
                                System.out.println("");
                            }
                        }

                        //kedua pemain melakukan move
                        if ((pemain1ValidMove == true) && (pemain2ValidMove == true)){
                            //mengecek priority move yang lebih besar
                            if (p1ActiveMonster.getMoves().get(p1PilMove).getMovePriority() > p2ActiveMonster.getMoves().get(p2PilMove).getMovePriority() || (p1ActiveMonster.getMoves().get(p1PilMove).getMovePriority() == p2ActiveMons.getMoves().get(p2PilMove).getMovePriority() && p1ActiveMons.getStats().getspeed() >= p2ActiveMons.getStats().getspeed())){
                                System.out.println(p1ActiveMonster.getNama() + " milik Pemain 1 akan menyerang duluan");
                                p1ActiveMonster.getMoves().get(p1PilMove).applyEffect(p1ActiveMonster, p2ActiveMonster, listEffectivity);
                                //mengecek apakah monster mati
                                if(p2ActiveMonster.getIsDead()){
                                    System.out.printf("%s is Dead :(%n", p2ActiveMonster.getNama());
                                    //mengecek pemain2 kalah atau tidak
                                    if(!pemain2.isLose()){
                                        pemain2.printMonsters();
                                        System.out.println("Choose New Monster Id: ");
                                        int MonsterId=action.nextInt();
                                        while(pemain2.getMonsterPool().get(MonsterId-1).getStats().getHealthPoint() <= 0 || pemain2.getListMonster().get(MonsterId-1).getSleepCounter() > 0){
                                            if(pemain2.getMonsterPool().get(MonsterId-1).getStats().getHealthPoint() <= 0){
                                                System.out.println("Monster sudah mati, silahkan pilih monster lain.");
                                                System.out.println("Choose Monster Id : ");
                                                MonsterId=action.nextInt();
                                            }
                                            else if(pemain2.getMonsterPool().get(MonsterId-1).getSleepCounter()>0){
                                                System.out.println("Monster sedang tertidur, silahkan pilih monster lain.");
                                                System.out.println("Choose Monster Id : ");
                                                MonsterId=action.nextInt();
                                            }
                                        }
                                        p2ActiveMonster = pemain2.getMonsterPool().get(MonsterId-1);
                                        System.out.printf("%s's Active Monster : %s%n", pemain2.getPlayerName(), p2ActiveMonster.getName());
                                    }
                                    else{
                                        endGame=true;
                                    }
                                }
                                //jika monster tidak mati maka akan dilakukan pengecekan kondisi sleep dan paralyze
                                else{
                                    if(p2ActiveMonster.getIsSleep()){
                                        System.out.println(p2ActiveMonster.getNama()+" dalam kondisi sleep");
                                    }
                                    else if(p2ActiveMonster.getIsParalyze()){
                                        System.out.println("Monster ini baru saja terkena efek paralyze dari move pemain 1 sebelumnya");
                                        System.out.println("Efek 25% akan diacak");
                                        int rand_int=random.nextInt(4);
                                        if (rand_int == 0){
                                            System.out.println("Monster terkena stunt");
                                        }
                                        else{
                                            System.out.println("Monster tidak kena stunt");
                                            p2ActiveMonster.getMoves().get(p2PilMove).applyEffect(p2ActiveMonster, p1ActiveMonster, listEffectivity);
                                        }
                                    }
                                    else{
                                        System.out.println(p2ActiveMonster.getNama() + " milik Player 2 menyerang balik menggunakan" + p2ActiveMonster.getMoves().get(p2PilMove).getMoveName());
                                        p2ActiveMonster.getMoves().get(p2PilMove).applyEffect(p2ActiveMons, p1ActiveMonster, listEffectivity);
                                        if (p1ActiveMons.getIsDead()){
                                            System.out.printf("%s is Dead :(%n", p1ActiveMonster.getNama());
                                            if (!pemain1.isLose()){  
                                                pemain1.printMonsters();
                                                System.out.println("Choose New Monster Id: ");
                                                int MonsterId = action.nextInt();
                                                while (pemain1.getListMonster().get(MonsterId-1).getStats().getHealthPoint() <= 0 || pemain1.getListMonster().get(choosenId-1).getSleepCounter() > 0){
                                                    if (pemain1.getListMonster().get(MonsterId-1).getStats().getHealthPoint() <= 0){   
                                                        System.out.println("Monster sudah mati, silahkan pilih monster lain.");
                                                        System.out.println("Choose Monster Id : ");
                                                        MonsterId = action.nextInt();
                                                    }
                                                    else if (p1.getListMonster().get(choosenId-1).getSleepCounter() > 0){
                                                        System.out.println("Monster sedang tertidur, silahkan pilih monster lain.");
                                                        System.out.println("Choose Monster Id : ");
                                                        MonsterId = action.nextInt();
                                                    }
                                                } //getMonsterPool==getListMonster
                                                p1ActiveMonster = pemain1.getMonsterPool.get(MonsterId-1);
                                                System.out.printf("%s's Active Monster : %s%n", pemain1.getPlayerName(), p1ActiveMonster.getNama());
                                            }
                                            else{
                                                endgame = true;
                                            }
                                        }
                                    }
                                }
                            }
                            else if ((p1ActiveMonster.getMoves().get(p1PilMove).getMovePriority() < p2ActiveMonster.getMoves().get(p2PilMove).getMovePriority()) || (p1ActiveMonster.getStats().getspeed() < p2ActiveMonster.getStats().getspeed())){                                   
                                System.out.println(p2ActiveMons.getName() + " milik Player 2 menyerang duluan");
                                p2ActiveMons.getMoves().get(p2PilMove).applyEffect(p2ActiveMonster, p1ActiveMonster, listEffectivity);
                                if (p1ActiveMons.getIsDead()){
                                    System.out.printf("%s is Dead :(%n", p1ActiveMonster.getNama());
                                    if (!pemain1.isLose()){
                                        p1.printMonsters();
                                        System.out.println("Choose New Monster Id: ");
                                        int MonsterId = action.nextInt();
                                        while (pemain1.getMonsterPool().get(MonsterId-1).getStats().getHealthPoint() <= 0 || pemain1.getMonsterPool().get(MonsterId-1).getSleepCounter() > 0){
                                            if (pemain1.getMonsterPool().get(MonsterId-1).getStats().getHealthPoint() <= 0){   
                                                System.out.println("Monster sudah mati, silahkan pilih monster lain.");
                                                System.out.println("Choose Monster Id : ");
                                                MonsterId = action.nextInt();
                                            }
                                            else if (p1.getMonsterPool().get(choosenId-1).getSleepCounter() > 0){
                                                System.out.println("Monster sedang tertidur, silahkan pilih monster lain.");
                                                System.out.println("Choose Monster Id : ");
                                                MonsterId = action.nextInt();
                                            }
                                        }
                                        p1ActiveMonster = p1.getMonsterPool().get(MonsterId-1);
                                        System.out.printf("%s's Active Monster : %s%n", pemain1.getPlayerName(), p1ActiveMonster.getNama());
                                    }
                                    else{
                                        endgame = true;
                                    }
                                }
                                else{
                                    if (p1ActiveMons.getIsSleep()){
                                        System.out.println(p1ActiveMons.getName() + " dalam kondisi sleep");
                                    } 
                                    else if (p1ActiveMons.getIsParalyze()){
                                        System.out.println("Monster ini baru saha terkena efek paralyze dari move p1 sebelumnya");
                                        System.out.println("Efek 25% akan dirandom...");
                                        int rand_int = rand.nextInt(4);
                                        if (rand_int == 0){
                                            System.out.println("Monster terkena stunt");
                                        }
                                        else{
                                            System.out.println("Monster tidak kena stunt");
                                            p1ActiveMons.getMoves().get(p1PilMove).applyEffect(p1ActiveMons, p2ActiveMons, listEffectivity);                                    
                                        }
                                    }
                                    else{
                                        System.out.println(p1ActiveMonster.getNama() + " milik Player 1 menyerang balik menggunakan" + p1ActiveMons.getMoves().get(p1PilMove).getName());
                                        p1ActiveMonster.getMoves().get(p1PilMove).applyEffect(p1ActiveMonster, p2ActiveMonster, listEffectivity);
                                        if (p2ActiveMonster.getIsDead()){
                                            System.out.printf("%s is Dead :(%n", p2ActiveMonster.getNama());
                                            if (!pemain2.isLose()){
                                                pemain2.printMonsters();
                                                System.out.println("Choose New Monster Id: ");
                                                int MonsterId = action.nextInt();
                                                while (p2.getMonsterPool().get(MonsterId-1).getStats().getHealthPoint() <= 0 || pemain2.getMonsterPool().get(MonsterId-1).getSleepCounter() > 0){
                                                    if (p2.getListMonster().get(choosenId-1).getStats().getHealthPoint() <= 0){   
                                                        System.out.println("Monster sudah mati, silahkan pilih monster lain.");
                                                        System.out.println("Choose Monster Id : ");
                                                        MonsterId = action.nextInt();
                                                    }
                                                    else if (p2.getListMonster().get(choosenId-1).getSleepCounter() > 0){
                                                        System.out.println("Monster sedang tertidur, silahkan pilih monster lain.");
                                                        System.out.println("Choose Monster Id : ");
                                                        MonsterId = scan.nextInt();
                                                    }
                                                }
                                                p2ActiveMons = p2.getMonsterPool.get(choosenId-1);
                                                System.out.printf("%s's Active Monster : %s%n", p2.getNama(), p2ActiveMons.getName());
                                            }
                                            else{
                                                endgame = true;
                                            }
                                        }
                                    }
                                }
                                
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