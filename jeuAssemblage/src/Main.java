import javax.swing.SwingUtilities;
import model.Game;
import model.GameConfig;
import model.Plateau;
import model.placementstrategy.ArbitraryStrategy;
import model.placementstrategy.PlacementStrategy;
import views.gui.MainWindow;

public class Main {
    
    public static void main(String[] args) {
        // Launching GUI
		SwingUtilities.invokeLater(new Runnable() { // BP: Utilisez une fonction anonyme (avec `() -> {}`)
            @Override
            public void run() {
                

                PlacementStrategy abritaryPlacementStrategy = new ArbitraryStrategy();
                Plateau plateau = new Plateau(GameConfig.BOARD_SIZE, GameConfig.BOARD_SIZE, GameConfig.COUNT_PIECES, abritaryPlacementStrategy);
                
                Game game = new Game(plateau);
                new MainWindow(game).setVisible(true);
            }
        });
        
    }
}
