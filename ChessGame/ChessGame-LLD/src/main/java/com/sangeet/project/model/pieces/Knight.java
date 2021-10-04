package com.sangeet.project.model.pieces;

import com.sangeet.project.model.*;

import java.util.List;

public class Knight extends Piece{
    public Knight(PieceType pieceType, Color pieceColor) {
        super(pieceType, pieceColor);
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
