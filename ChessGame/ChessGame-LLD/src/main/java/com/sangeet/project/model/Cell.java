package com.sangeet.project.model;

import com.sangeet.project.model.pieces.Piece;

public class Cell {

    private Color cellColor;
    private Piece pieceAtCell;
    private CellPosition cellPosition;

    public Cell(Color cellColor, Piece pieceAtCell, CellPosition cellPosition) {
        this.cellColor = cellColor;
        this.pieceAtCell = pieceAtCell;
        this.cellPosition = cellPosition;
    }

    public Cell(Piece pieceAtCell, CellPosition cellPosition) {
        this.pieceAtCell = pieceAtCell;
        this.cellPosition = cellPosition;
    }

    public boolean isCellFree(){ return pieceAtCell == null; }

    public Color getCellColor() {
        return cellColor;
    }

    public Piece getPieceAtCell() {
        return pieceAtCell;
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    public void setPieceAtCell(Piece pieceAtCell) {
        this.pieceAtCell = pieceAtCell;
    }

    public void setCellPosition(CellPosition cellPosition) {
        this.cellPosition = cellPosition;
    }
}
