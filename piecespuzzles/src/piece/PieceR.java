package piece;

public class PieceR extends Piece {

    public PieceR(int width, int height, int orientation) {
        super(width, height, orientation);
    }

    public PieceR(int width, int height) {
        this(width, height, 0);
    }

    @Override
    protected boolean matrixCellVal(int i, int j, int height, int width) {
        return true;
    }

    @Override
    public String getShape() {
        return "R";
    }
    
}
