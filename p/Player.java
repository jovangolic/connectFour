package vezbanje.javaPrimer.ConnectFour.p;

public class Player {

    private String name;
    private GridPosition piece;

    public Player() {

    }

    public Player(String name, GridPosition piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return this.name;
    }

    public GridPosition getPieceColor() {
        return this.piece;
    }
}
