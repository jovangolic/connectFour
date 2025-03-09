package vezbanje.javaPrimer.ConnectFour.p;

public class Grid {

    private int rows;
    private int cols;
    private int[][] grid;

    public Grid() {

    }

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initGrid();
    }

    // inicijalizacija tabele
    public void initGrid() {
        this.grid = new int[rows][cols];
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < cols; j++) {
                grid[i][j] = GridPosition.EMPTY.ordinal();
            }
        }
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public int getColumnCount() {
        return this.cols;
    }

    // postavljanje figura
    public int placePiece(int column, GridPosition piece) {
        if (column < 0 || column >= this.cols) {
            throw new Error("Invalid column");
        }
        if (piece == GridPosition.EMPTY) {
            throw new Error("Invalid piece");
        }
        // Place piece in the lowest empty row
        for (int row = this.rows - 1; row >= 0; row--) {
            if (this.grid[row][column] == GridPosition.EMPTY.ordinal()) {
                this.grid[row][column] = piece.ordinal();
                return row;
            }
        }
        return -1;
    }

    public boolean checkWin(int connectN, int row, int col, GridPosition piece) {
        // horizontalna provera
        int count = 0;
        for (var c = 0; c < this.cols; c++) {
            if (this.grid[row][c] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // vertikalna provera
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            if (grid[r][col] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // dijagonalna provera
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            // sabiramo red i kolonu za dijagonalu
            int d = row + col - r;
            if (d >= 0 && d < this.cols && this.grid[r][d] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // anti-dijagonalna provera
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            // racunamo anti-dijagonalu
            int ad = col - row + r;
            if (ad >= 0 && ad < this.cols && this.grid[r][ad] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        return false;
    }
}
