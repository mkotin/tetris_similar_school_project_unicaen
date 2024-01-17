import org.junit.Before;
import org.junit.Test;

import model.Game;
import model.GameConfig;
import model.Plateau;
import model.placementstrategy.RandomStrategy;
import piece.Coordinates;
import piece.Piece;
import piece.PieceFactory;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private Plateau plateau;

    @Before
    public void setUp() {
        // Create a Game instance with necessary dependencies
        plateau = new Plateau(10, 10, 3, new RandomStrategy());
        game = new Game(plateau);
    }

    @Test
    public void testStart() {
        // Test starting a new game
        game.start();
        assertTrue(game.isOnGoing());
        assertEquals(0, game.getScore());
        assertEquals(GameConfig.MAX_ACTIONS, game.getRemainingActions());
    }

    @Test
    public void testMovePiece() {
        // Test moving a piece
        game.start();
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();
        Coordinates position = new Coordinates(3, 3);

        assertTrue(game.movePiece(piece, position));
        assertEquals(GameConfig.MAX_ACTIONS - 1, game.getRemainingActions());
    }

    @Test
    public void testRotatePiece() {
        // Test rotating a piece
        game.start();
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece = pieceFactory.buildRandom();

        assertTrue(game.rotatePiece(piece, true));
        assertEquals(GameConfig.MAX_ACTIONS - 1, game.getRemainingActions());
    }

    @Test
    public void testCalculateScore() {
        // Test calculating the score
        game.start();
        game.calculateScore();
        assertEquals(0, game.getScore()); // Adjust based on your actual score calculation logic
    }

    @Test
    public void testGameOver() {
        // Test ending the game
        game.start();
        game.gameOver();
        assertFalse(game.isOnGoing());
    }

 
    
}

