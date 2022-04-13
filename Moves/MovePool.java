package Moves;

import java.util.ArrayList;
import java.util.List;

public class MovePool {
    private List<Move> listMove;

    public MovePool(){
        List<Move> listMove = new ArrayList<Move>();
        this.listMove = listMove;
    }

    public List<Move> getListMove(){
        return this.listMove;
    }

    public void printMovePool(){
        for (Move mo : listMove){
            System.out.printf("%d %s%n", mo.getMoveID(), mo.getMoveName());
        }
    }

    public void addMove(Move m){
        getListMove().add(m);
    }
}
