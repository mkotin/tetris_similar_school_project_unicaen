import org.junit.Before;
import org.junit.Test;

import model.Plateau;
import model.placementstrategy.RandomStrategy;
import piece.Coordinates;
import piece.Piece;
import piece.PieceFactory;

import static org.junit.Assert.*;

public class PlateauTest {

    private Plateau plateau;

    @Before
    public void setUp() {
        // Create a Plateau instance with necessary dependencies
        plateau = new Plateau(10, 10, 3, new RandomStrategy());
        plateau.setEnabled(true);
    }

    @Test
    public void testAddPiece() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates position = new Coordinates(3, 3);

        assertTrue(plateau.addPiece(piece, position));
        assertEquals(1, plateau.getPieces().size());
    }

    @Test
    public void testMovePiece() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates initialPosition = new Coordinates(3, 3);
        Coordinates newPosition = new Coordinates(5, 5);

        plateau.addPiece(piece, initialPosition);
        assertTrue(plateau.movePiece(piece, newPosition));
        assertEquals(newPosition, piece.getOutterCenter());
    }

    @Test
    public void testRotateClockwisePiece() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates position = new Coordinates(3, 3);

        plateau.addPiece(piece, position);
        assertTrue(plateau.rotateClockWisePiece(piece));
    }

    @Test
    public void testRotateAntiClockwisePiece() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates position = new Coordinates(3, 3);

        plateau.addPiece(piece, position);
        assertTrue(plateau.rotateAntiClockWisePiece(piece));
    }

    @Test
    public void testValidate() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates position = new Coordinates(3, 3);

        assertTrue(plateau.validate(piece, position));
    }

    @Test
    public void testReset() {
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();

        plateau.addPiece(piece, new Coordinates(3, 3));
        assertEquals(1, plateau.getPieces().size());

        plateau.reset();
        assertEquals(0, plateau.getPieces().size());
    }

    
}

