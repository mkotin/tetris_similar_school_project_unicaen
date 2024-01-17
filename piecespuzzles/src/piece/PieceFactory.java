package piece;

import java.util.Random;

/**
 * Factory for class piece
 */
public class PieceFactory {

    protected int width;
    protected int height;
    protected int orientation;
    protected int piece;

    

    /**
     * Constructor for PieceFactory
     */
    public PieceFactory() {
        randomValues(); // default
    }

    /**
     * Use custom width
     * @param width
     * @return PieceFactory
     */
    public PieceFactory withWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * Use custom height
     * @param height
     * @return PieceFactory
     */
    public PieceFactory withHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Use custom orientation
     * @param orientation
     * @return PieceFactory
     */
    public PieceFactory withOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    /**
     * Use custome piece
     * @param piece
     * @return PieceFactory
     */
    public PieceFactory withPiece(int piece) {
        this.piece = piece;
        return this;
    }

    /**
     * Build a new instance of piece
     * @return a new instance of piece
     */
    public Piece build() {
        if(piece == 0) {
            return new PieceL(width, height, orientation);
        } else if (piece == 1) {
            return new PieceT(width, height, orientation);
        } else {
            return new PieceR(width, height, orientation);
        }
    }
    
    /**
     * Randomly build a new insance of piece
     * @return a new random instance of piece
     */
    public Piece buildRandom(){
        randomValues();
        return build();
    }

    /**
     * Generate random odd
     * @param min
     * @param max
     * @return
     */
    private  int generateRandomOdd(int min, int max) {
        Random rand = new Random(); // BP: Instanciez cet object dans le constructeur pour le réutiliser
        int range = (max - min) / 2 + 1;
        return rand.nextInt(range) * 2 + min;
    }

    /**
     * Set random values for properties
     */
    private void randomValues() {
        Random rand = new Random(); // BP: Instanciez cet object dans le constructeur pour le réutiliser
        this.height = generateRandomOdd(3, 6);
        this.width = generateRandomOdd(3, 4);
        this.orientation = rand.nextInt(4);
        this.piece = rand.nextInt(3);
    }
}
