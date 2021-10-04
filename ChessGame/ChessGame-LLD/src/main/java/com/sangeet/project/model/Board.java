package com.sangeet.project.model;

import com.sangeet.project.model.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private List<List<Cell>> cells;

    public Board() {
        cells = new ArrayList<>();
        resetBoard();
    }

    public void resetBoard(){
        //0 - 8 as rows List
        //0 - 8 as cols List<List>
        cells.add(0, Arrays.asList(
                //pieces and empty cells
                //pieces in order
                new Cell(new Rook(PieceType.ROOK, Color.WHITE), new CellPosition(0, 0)),
                new Cell(new Knight(PieceType.KNIGHT, Color.WHITE), new CellPosition(0, 1)),
                new Cell(new Bishop(PieceType.BISHOP, Color.WHITE), new CellPosition(0, 2)),
                new Cell(new Queen(PieceType.QUEEN, Color.WHITE), new CellPosition(0, 3)),
                new Cell(new King(PieceType.KING, Color.WHITE), new CellPosition(0, 4)),
                new Cell(new Bishop(PieceType.BISHOP, Color.WHITE), new CellPosition(0, 5)),
                new Cell(new Knight(PieceType.KNIGHT, Color.WHITE), new CellPosition(0, 6)),
                new Cell(new Rook(PieceType.ROOK, Color.WHITE), new CellPosition(0, 7))
        ));
        cells.add(1, Arrays.asList(
                //pieces and empty cells
                //pieces in order
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 0)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 1)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 2)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 3)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 4)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 5)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 6)),
                new Cell(new Pawn(PieceType.PAWN, Color.WHITE), new CellPosition(1, 7))
        ));

        //2 - 5
        //empty cells
        for(int i = 2; i <= 5; i++){
            cells.add(i, Arrays.asList(
                    //empty cells
                    new Cell(null, new CellPosition(i, 0)),
                    new Cell(null, new CellPosition(i, 1)),
                    new Cell(null, new CellPosition(i, 2)),
                    new Cell(null, new CellPosition(i, 3)),
                    new Cell(null, new CellPosition(i, 4)),
                    new Cell(null, new CellPosition(i, 5)),
                    new Cell(null, new CellPosition(i, 6)),
                    new Cell(null, new CellPosition(i, 7))
            ));

            //6 - 7 black side
            cells.add(6, Arrays.asList(
                    //pieces and empty cells
                    //pieces in order
                    new Cell(new Rook(PieceType.ROOK, Color.BLACK), new CellPosition(6, 0)),
                    new Cell(new Knight(PieceType.KNIGHT, Color.BLACK), new CellPosition(6, 1)),
                    new Cell(new Bishop(PieceType.BISHOP, Color.BLACK), new CellPosition(6, 2)),
                    new Cell(new Queen(PieceType.QUEEN, Color.BLACK), new CellPosition(6, 3)),
                    new Cell(new King(PieceType.KING, Color.BLACK), new CellPosition(6, 4)),
                    new Cell(new Bishop(PieceType.BISHOP, Color.BLACK), new CellPosition(6, 5)),
                    new Cell(new Knight(PieceType.KNIGHT, Color.BLACK), new CellPosition(6, 6)),
                    new Cell(new Rook(PieceType.ROOK, Color.BLACK), new CellPosition(6, 7))
            ));
            cells.add(7, Arrays.asList(
                    //pieces and empty cells
                    //pieces in order
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 0)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 1)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 2)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 3)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 4)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 5)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 6)),
                    new Cell(new Pawn(PieceType.PAWN, Color.BLACK), new CellPosition(7, 7))
            ));

        }

    }

    public Cell getCellCoordinate(int x, int y) throws RuntimeException {
        if(x < 0 || x > 7 || y < 0 || y > 7){
            throw new RuntimeException("Coordinate out of bounds");
        }

        List<Cell> cellAtRow = cells.get(x);
        for(Cell cell: cellAtRow){
            if(cell.getCellPosition().getX() == x && cell.getCellPosition().getY() == y) {
                return cell;
            }
        }

        return null;

    }

}

