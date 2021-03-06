package ch.heigvd.gen;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final static int NB_SQUARE = 40;
    private List<Square> boardList;

    public Board() {
        this.boardList = new ArrayList<Square>();

        this.boardList.add(new GoSquare());

        for (int i = 1; i < NB_SQUARE; i++) {
            this.boardList.add(new RegularSquare("Square " + i));
        }

        boardList.set(4, new IncomeTaxSquare());
        boardList.set(30, new GoToJailSquare(boardList.get(10)));

    }

    /**
     * This methods compute and gives the resulting square after a move
     *
     * @param currentSquare
     * @param fvTot         it's the number of square we have to move,
     *                      I do not know what fvTot means but it's named that in the book
     * @return
     */

    public Square getSquare(Square currentSquare, int fvTot) {

        if (fvTot < 2) {
            throw new IllegalArgumentException();
        }

        int squareNumber = boardList.indexOf(currentSquare); //should never be -1 as the currentSquare has to come from the board
        int newSquareNumber = (squareNumber + fvTot) % NB_SQUARE;

        return getSquares()[newSquareNumber];
    }

    /**
     * method that makes testing better
     *
     * @return
     */
    public Square[] getSquares() {
        return this.boardList.toArray(new Square[NB_SQUARE]);
    }


    /**
     * I should have returned a list and not an array for getSquares
     *
     * @param squareName
     * @param squareArray
     * @return
     */
    static public int indexFinder(String squareName, Square[] squareArray) {
        for (int i = 0; i < squareArray.length; i++) {
            if (squareName == squareArray[i].getName()) {
                return i;
            }

        }
        return -1;
    }

}
