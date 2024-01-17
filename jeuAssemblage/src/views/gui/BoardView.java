package views.gui;

import javax.swing.BorderFactory; // BP: Import non utilisé
import javax.swing.JComponent; // BP: Import non utilisé
import javax.swing.JLabel; // BP: Import non utilisé
import javax.swing.JPanel;

import model.Game;
import model.Plateau;
import observer.ListeningModel;
import piece.Piece;

import java.awt.Toolkit; // BP: Import non utilisé
import java.awt.Dimension; // BP: Import non utilisé
import java.awt.BorderLayout; // BP: Import non utilisé
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout; // BP: Import non utilisé
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent; // BP: Import non utilisé
import java.awt.event.MouseEvent; // BP: Import non utilisé
import java.awt.event.MouseListener; // BP: Import non utilisé


/**
 * cette classe permet de créer une grille graphique pour la simulation
 */
public class BoardView extends JPanel implements ListeningModel{
    public static final long serialVersionUID = 1L;


    private boolean[][] cell; // BP: Variable inutilisée
    private Plateau plateau; // Model de la vue
    private Game game;

    private int rows=0;
    private int cols=0;

    protected int cellWidth;
    protected int cellHeight;
    protected List<PieceView> pieceViews = new ArrayList<>();
    protected boolean addPieces = true;

    /**
     * 
     * @param game
     * @param plateau
     */
    public BoardView(Game game, Plateau plateau) {
        
        // Listen to plateau
        this.plateau = plateau;
        this.plateau.addListening(this);

        this.game = game; // reference to game
        
        this.rows = plateau.getHeight();
        this.cols = plateau.getWidth();
        setLayout(null);

        
        //repaint();

        
    }

    /**
     * 
     * @return
     */
    public int getCellWidth() {
        return cellWidth;
    }

    /**
     * 
     * @param cellWidth
     */
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    /**
     * 
     * @return
     */
    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void addPiece(PieceView p) {
        add(p);
    }

    public void addPieces() {
        for (Piece piece : plateau.getPieces()) {

            PieceView pieceView = new PieceView(game, piece, getCellWidth(), getCellHeight());
            pieceViews.add(pieceView);
            addPiece(pieceView);
        }
    }




    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
         cellWidth = this.getWidth() / this.rows;
         cellHeight = this.getHeight() / this.cols;
        for (int i=0 ; i < this.rows; i++) {
            for (int j=0; j < this.cols; j++) {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(j*cellWidth,i*cellHeight, cellWidth,cellHeight);
                g2d.setColor(Color.GRAY);
                g2d.drawRect(j*cellWidth, i*cellHeight, cellWidth,cellHeight);
                
            }
        }

        if(addPieces) {
            addPieces();
            addPieces = false;
        }
    }


    @Override
    public void modelUpdtae(Object source) {
        // Remove old pieces
        removeAll();
        revalidate();
        repaint();

        // add new pieces
        addPieces = true;
        repaint();
    }


    public Plateau getPlateau() {
        return plateau;
    }


    
   


}
