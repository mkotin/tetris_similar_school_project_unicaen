package views.gui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import model.GameConfig;

/**
 * The {@code PieceColors} class represents a utility class for managing colors associated with different game pieces.
 * It provides a mapping of piece identifiers to their corresponding colors based on the {@code GameConfig}.
 */
public class PieceColors {

    /** 
     * A mapping of piece identifiers to their corresponding colors.
     */
    private Map<String, Color> colors;

    /**
     * Constructs a new {@code PieceColors} instance and initializes the color mapping.
     * The default colors are retrieved from the {@code GameConfig} class.
     */
    public PieceColors() {
        colors = new HashMap<>();
        colors.put("T", GameConfig.PIECE_T_COLOR);
        colors.put("L", GameConfig.PIECE_L_COLOR);
        colors.put("R", GameConfig.PIECE_R_COLOR);
        
        // BP: Vous pouvez faire ça en une seule instruction, exemple : 
        colors = Map.of(
            "T", GameConfig.PIECE_T_COLOR,
            "L", GameConfig.PIECE_L_COLOR,
            "R", GameConfig.PIECE_R_COLOR
        );
    }

    /**
     * Returns the mapping of piece identifiers to their corresponding colors.
     * 
     * @return A {@code Map} containing piece identifiers as keys and their associated colors as values.
     */
    public Map<String, Color> getColors() {
        return colors;
    }
}
