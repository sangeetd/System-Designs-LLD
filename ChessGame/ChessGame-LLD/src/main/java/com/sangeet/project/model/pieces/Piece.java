package com.sangeet.project.model.pieces;

import com.sangeet.project.model.*;

import java.util.List;

public abstract class Piece {

    private PieceType pieceType;
    private Color pieceColor;
    private boolean isKilled;

    public Piece(PieceType pieceType, Color pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.isKilled = false;
    }

    public void setKilled(boolean killed) {
        if(this.isKilled == false){
            isKilled = killed;
        }
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public abstract boolean canMove(CellPosition fromPosition, CellPosition toPosition, Board board);
    public abstract List<Cell> possiblePositionsToMove(CellPosition fromPosition, Board board);

}
