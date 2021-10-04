package com.sangeet.project.model;

import com.sangeet.project.model.pieces.Piece;

public class Move {

    private Player moverMaker;
    private Cell startCell;
    private Cell endCell;

    public Move(Player moverMaker, Cell startCell, Cell endCell) {
        this.moverMaker = moverMaker;
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public Player getMoverMaker() {
        return moverMaker;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }



}
