import org.junit.Test;

import piece.Piece;

import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void testPieceConstructor() {
        // Test constructor with valid parameters
        Piece piece = new ConcretePiece(3, 4, 1);
        assertEquals(3, piece.getWidth());
        assertEquals(4, piece.getHeight());
        assertEquals(1, piece.getOrientation());

        // Test constructor with invalid orientation
        try {
            new ConcretePiece(3, 4, 5);
            fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testClone() {
        // Test cloning of a piece
        Piece originalPiece = new ConcretePiece(3, 4, 2);
        Piece clonedPiece = Piece.clone(originalPiece);

        assertEquals(originalPiece.getWidth(), clonedPiece.getWidth());
        assertEquals(originalPiece.getHeight(), clonedPiece.getHeight());
        assertEquals(originalPiece.getOrientation(), clonedPiece.getOrientation());
    }

    @Test
    public void testRotateClockwise() {
        // Test clockwise rotation
        Piece piece = new ConcretePiece(3, 3, 0);
        boolean[][] originalMatrix = piece.getCurrentMatrix();

        piece.rotateClockWise();
        boolean[][] rotatedMatrix = piece.getCurrentMatrix();

        // Check that the matrix is rotated clockwise
        assertNotEquals(originalMatrix, rotatedMatrix);
    }

    // Add more test methods for other functionalities of the Piece class

    // Example of a concrete class implementing the abstract Piece class for testing
    private static class ConcretePiece extends Piece {

        public ConcretePiece(int width, int height, int orientation) {
            super(width, height, orientation);
        }

        @Override
        protected boolean matrixCellVal(int i, int j, int height, int width) {
            // Implement the matrixCellVal method for testing
            return false;
        }

        @Override
        public String getShape() {
            // Implement the getShape method for testing
            return null;
        }
    }
}

