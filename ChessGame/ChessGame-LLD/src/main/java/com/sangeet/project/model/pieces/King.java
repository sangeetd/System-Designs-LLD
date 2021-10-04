package com.sangeet.project.model.pieces;

import com.sangeet.project.model.*;

import java.util.List;

public class King extends Piece{

    private boolean castled;

    public King(PieceType pieceType, Color pieceColor) {
        super(pieceType, pieceColor);
        castled = false;
    }

    public boolean isCastled() {
        return castled;
    }

    public void setCastled(boolean castled) {
        this.castled = castled;
    }

    @Override
    public boolean canMove(CellPosition fromPosition, CellPosition toPosition, Board board) {
        return false;
    }

    @Override
    public List<Cell> possiblePositionsToMove(CellPosition fromPosition, Board board) {
        return null;
    }
}
