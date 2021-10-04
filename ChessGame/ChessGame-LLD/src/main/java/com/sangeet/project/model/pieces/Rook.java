package com.sangeet.project.model.pieces;

import com.sangeet.project.model.*;
import com.sangeet.project.model.pieces.Piece;

import java.util.List;

public class Rook extends Piece {

    public Rook(PieceType pieceType, Color pieceColor) {
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
