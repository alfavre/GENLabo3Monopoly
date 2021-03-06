package ch.heigvd.gen;

import java.util.Random;

public class MonopolyGame {

    final static String PIECESET[] = {"The Boot", "the Teacup", "The Teapot", "The De-a-coudre", "The Dog", "Barack Obama", "The Epave", "La quatrieme personne"};
    static int index;
    static Random rng;
    final static int NB_ROUND = 20;
    private int roundCnt;
    private Cup cup;
    private Board board;
    private Player player[];
    private Player winner;

    public MonopolyGame(String[] playerNames) {

        rng = new Random();
        index = rng.nextInt(8);
        this.roundCnt = 0;
        this.cup = new Cup();
        this.board = new Board();
        this.player = new Player[playerNames.length];
        int i = 0;
        for (String s : playerNames) {
            this.player[i++] = new Player(this.cup, s, this.board, PIECESET[index]);
            this.index = (this.index + 1) % 8;
        }
    }

    public void playGame() {

        if (!(player.length >= 2 && player.length <= 8)) {
            System.out.println("Too few or too many player, you need 2 to 8 player to play");
            return;
        }

        for (; roundCnt < NB_ROUND; roundCnt++) {
            System.out.println("Round " + (roundCnt + 1));
            this.playRound();
        }

        winner = player[0];
        for (Player p : player) {
            System.out.println(p.getName() + " has made " + p.getNetWorth() + "$ while playing as " + p.getPiece().getName());
            if (p.getNetWorth() > winner.getNetWorth()) { // in tie case biggest number player wins
                winner = p;
            }
        }

        System.out.println("\nThe winner is " + winner.getName() + ", they made " + winner.getNetWorth() + "$");
        System.out.println(winner.getPiece().getName() + " best piece confirmed!");
        System.out.println("Congraturation");

    }

    private void playRound() {
        for (Player p : player) {
            System.out.println();
            p.takeTurn();
            System.out.println();

        }

    }

    public int getRoundCnt() {
        return roundCnt;
    }
}
