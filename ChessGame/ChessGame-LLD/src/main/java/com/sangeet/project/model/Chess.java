package com.sangeet.project.model;

import com.sangeet.project.model.pieces.King;
import com.sangeet.project.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Board board;
    private Player[] players;
    private Player currentPlayer;
    private List<Move> moveHistory;
    private GameStatus gameStatus;

    public void initialize(Player player1, Player player2){

        board = new Board();
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;

        currentPlayer = player1.getPlayerSide() == Color.WHITE ? player1 : player2;

        moveHistory = new ArrayList<>();

        gameStatus = GameStatus.ACTIVE;

    }

    public void playerMove(Player player, CellPosition fromPosition, CellPosition toPosition){

        Cell from = board.getCellCoordinate(fromPosition.getX(), fromPosition.getY());
        Cell to = board.getCellCoordinate(fromPosition.getX(), fromPosition.getY());
        Move move = new Move(player, from, to);
        makeMove(move);

    }

    private void makeMove(Move move) {

        Piece sourcePiece = move.getStartCell().getPieceAtCell();
        Piece destinationPiece = move.getEndCell().getPieceAtCell();
        Player moverMaker = move.getMoverMaker();

        if(sourcePiece == null){
            return;
        }

        if(moverMaker != currentPlayer){
            return;
        }

        if(sourcePiece.getPieceColor() != moverMaker.getPlayerSide()){
            return;
        }

        //if end cell has a piece and if that cell is of the same color as that of start cell color
        if(!move.getEndCell().isCellFree() &&
          sourcePiece.getPieceColor() == destinationPiece.getPieceColor()){
            return;
        }

        if(!move.getStartCell().isCellFree() &&
        !sourcePiece
                .canMove(
                move.getStartCell().getCellPosition(),
                move.getEndCell().getCellPosition(),
                board
        )){
            return;
        }

        //kill the piece at end cell
        if(destinationPiece != null && !destinationPiece.isKilled()){
            destinationPiece.setKilled(true);
        }

        // castling?
        if (sourcePiece != null && sourcePiece.getPieceType() == PieceType.KING
                && ((King) sourcePiece).isCastled()) {
            ((King) sourcePiece).setCastled(true);
        }

        //moves to history
        moveHistory.add(move);

        //move the source piece to end cell
        move.getEndCell().setPieceAtCell(sourcePiece);
        move.getStartCell().setPieceAtCell(null);

        if (destinationPiece != null && destinationPiece.getPieceType() == PieceType.KING) {
            if (moverMaker.getPlayerSide() == Color.WHITE) {
                gameStatus = GameStatus.WHITE_WIN;
            }
            else {
                gameStatus = GameStatus.BLACK_WIN;
            }
        }

        currentPlayer = currentPlayer == players[0] ? players[1] : players[0];

    }

}
