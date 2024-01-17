package piece;

public class PieceT extends Piece  {

    public PieceT(int width, int height, int orientation) {
        super(width, height, orientation);
    }

    public PieceT(int width, int height) {
        this(width, height, 0);
    }

    @Override
    protected boolean matrixCellVal(int i, int j, int height, int width) {
        boolean res = false;
        switch (orientation) {
            case 0: // north
                res = (i==0 || j == (width/2));
                break;
        
            case 1: // east
                res = (i == (height / 2) || j == (width-1));
                break;
        
            case 2: // south
                res = (i == (height-1) || j == (width/2));
                break;
        
            case 3: // west
                res = ((j == 0) || i == (height/2));
                break;
        
            default: // north
                res = (i==0 || j == (width/2));
                break;
        }

        return res;
    }

    @Override
    public String getShape() {
        return "T";
    }
    
}
