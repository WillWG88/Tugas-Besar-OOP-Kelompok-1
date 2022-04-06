
import java.util.*;

public class Main{
    public static void main(String[] args){
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
