import java.lang.System;
import java.util.Scanner;

public class Game {
    int turn = 0;
    boolean loop = true;

    Player player1 = new Player();
    Player player2 = new Player();

    MonsterPool mnstrPoolPlayer1 = new MonsterPool();
    MonsterPool mnstrPoolPlayer2 = new MonsterPool();

    MovePool movePoolNormal = new MovePool();
    MovePool movePoolSpecial = new MovePool();
    MovePool movePoolDefault = new MovePool();
    MovePool movePoolSpecial = new MovePool();

    EffectivityPool ePool = new EffectivityPool();

    Scanner scanner = new Scanner(System.in);

}
