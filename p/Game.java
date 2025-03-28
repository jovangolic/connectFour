package vezbanje.javaPrimer.ConnectFour.p;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    static Scanner input = new Scanner(System.in);
    private Player[] players;
    private Grid grid;
    private Map<String, Integer> score;
    private int targetScore;
    private int connectN;

    public Game() {

    }

    public Game(Grid grid, int connectN, int targetScore) {
        this.grid = grid;
        this.connectN = connectN;
        this.targetScore = targetScore;
        this.players = new Player[] {
                new Player("Player 1", GridPosition.YELLOW),
                new Player("Player 2", GridPosition.RED)
        };
        this.score = new HashMap<>();
        for (Player player : this.players) {
            this.score.put(player.getName(), 0);
        }
    }

    private void printBoard() {
        System.out.println("Board");
        int[][] grid = this.grid.getGrid();
        for (var r = 0; r < grid.length; r++) {
            String row = "";
            for (int i : grid[r]) {
                if (i == GridPosition.EMPTY.ordinal()) {
                    row += "0";
                } else if (i == GridPosition.YELLOW.ordinal()) {
                    row += "Y";
                } else if (i == GridPosition.RED.ordinal()) {
                    row += "R";
                }
            }
            System.out.println(row);
        }
        System.out.println();
    }

    private int[] playMove(Player player) {
        printBoard();
        System.out.println(player.getName() + "'s turn");
        int colCnt = this.grid.getColumnCount();

        System.out.print("Enter column between 0 and " + (colCnt - 1) + " to add piece: ");
        int moveColumn = input.nextInt();
        int moveRow = this.grid.placePiece(moveColumn, player.getPieceColor());
        return new int[] { moveRow, moveColumn };
    }

    private Player playRound() {
        while (true) {
            for (Player player : this.players) {
                int[] pos = playMove(player);
                int row = pos[0];
                int col = pos[1];
                GridPosition pieceColor = player.getPieceColor();
                if (this.grid.checkWin(this.connectN, row, col, pieceColor)) {
                    this.score.put(player.getName(), this.score.get(player.getName()) + 1);
                    return player;
                }
            }
        }
    }

    public void play() {
        int maxScore = 0;
        Player winner = null;
        while (maxScore < this.targetScore) {
            winner = playRound();
            System.out.println(winner.getName() + " won the round");
            maxScore = Math.max(this.score.get(winner.getName()), maxScore);

            this.grid.initGrid(); // reset grid
        }
        System.out.println(winner.getName() + " won the game");
    }
}
