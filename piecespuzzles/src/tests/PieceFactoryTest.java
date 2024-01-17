import org.junit.Test;

import piece.Piece;
import piece.PieceFactory;
import piece.PieceL;
import piece.PieceR;
import piece.PieceT;

import static org.junit.Assert.*;

public class PieceFactoryTest {

    @Test
    public void testBuild() {
        // Test building a PieceL
        Piece pieceL = new 	PieceFactory().withWidth(3).withHeight(5).withOrientation(2).withPiece(0).build();
        assertTrue(pieceL instanceof PieceL);
        assertEquals(3, pieceL.getWidth());
        assertEquals(5, pieceL.getHeight());
        assertEquals(2, pieceL.getOrientation());

        // Test building a PieceT
        Piece pieceT = new PieceFactory().withWidth(4).withHeight(3).withOrientation(1).withPiece(1).build();
        assertTrue(pieceT instanceof PieceT);
        assertEquals(4, pieceT.getWidth());
        assertEquals(3, pieceT.getHeight());
        assertEquals(1, pieceT.getOrientation());

        // Test building a PieceR
        Piece pieceR = new PieceFactory().withWidth(5).withHeight(3).withOrientation(3).withPiece(2).build();
        assertTrue(pieceR instanceof PieceR);
        assertEquals(5, pieceR.getWidth());
        assertEquals(3, pieceR.getHeight());
        assertEquals(3, pieceR.getOrientation());
    }

    @Test
    public void testBuildRandom() {
        // Test building a random piece
        Piece randomPiece = new PieceFactory().buildRandom();
        assertNotNull(randomPiece);
        assertTrue(randomPiece instanceof Piece); // Ensure it's an instance of Piece
    }

    
}

