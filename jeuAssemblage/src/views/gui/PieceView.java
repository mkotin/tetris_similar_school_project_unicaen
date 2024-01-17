package views.gui;

import java.awt.BasicStroke; // BP: Import non utilisé
import java.awt.Color;
import java.awt.Dimension; // BP: Import non utilisé
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke; // BP: Import non utilisé
import java.util.HashMap; // BP: Import non utilisé
import java.util.Map; // BP: Import non utilisé

import javax.swing.BorderFactory; // BP: Import non utilisé
import javax.swing.JComponent; // BP: Import non utilisé
import javax.swing.JPanel; // BP: Import non utilisé
import javax.swing.border.LineBorder;

import model.Game;
import model.GameConfig; // BP: Import non utilisé
import piece.Coordinates;
import piece.Piece;
import piece.observer.ListeningModel;

public class PieceView extends DraggableComponent implements ListeningModel  {
    private Piece piece;
    private Game game;
    private Color color = Color.RED;
    private int cellWidth=0;
    private int cellHeight=0;
    private PieceColors pieceColors;
    
    private int draggedCenterRow = -1;
    private int draggedCenterCol = -1;
    private boolean isSelected = false;
    // BP: Pas de todo dans une version de livraison
    // TODO: associé une couleur a chaque piece

    public static PieceView currentSelectedPiece = null;
    public static PieceView prevSelectedPiece = null;


    

    public PieceView(Game game, Piece piece, int cellWidth, int cellHeight) {
        // Listen to piece
        this.piece = piece;
        this.piece.addListening(this);

        // ref to game
        this.game = game;

        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;

        this.pieceColors = new PieceColors();

        drawPiece();
       
       
    }

    public void drawPiece() {
        // Calculer les coordonnées du coin supérieur gauche du rectangle
        Coordinates outterCenter = piece.getOutterCenter();

        int topLeftX = outterCenter.getY() - (piece.getWidth() / 2);
        int topLeftY = outterCenter.getX() - (piece.getHeight() / 2);


        setBounds(topLeftX * cellWidth, topLeftY *cellHeight, (piece.getWidth() * cellWidth) , (piece.getHeight() * cellHeight));
        repaint();
    }

    


    public Piece getPiece() {
        return piece;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        repaint();
    }

    


    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
           
        if(isSelected == true) {
            setBorder(new LineBorder(Color.BLACK, 2));
        } else {
            setBorder(null);
        }

        for (int i=0 ; i < piece.getHeight(); i++) {
            for (int j=0; j < piece.getWidth(); j++) {

                Color color = Color.WHITE;
                if(piece.isOccupied(i, j) == true) {
                    color = this.pieceColors.getColors().get(piece.getShape());
                    g2d.setColor(color);
                    g2d.fillRect(j*cellWidth,i*cellHeight, cellWidth,cellHeight);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(j*cellWidth, i*cellHeight, cellWidth,cellHeight);
                }
            }
        }
    }


    @Override
    public int ajustX(int x) {
        int col = (x + cellWidth / 2) / cellWidth;
        return col * cellWidth;
    }


    @Override
    public int ajustY(int y) {
        int row = (y + cellHeight / 2) / cellHeight;
        return row * cellHeight;
    }

    @Override
    public void afterMouseDragged(int x, int y) {
        deselection();
        // TODO validate
        int col = x / cellWidth;
        int row = y /cellHeight;

        this.draggedCenterRow = row + piece.getHeight() / 2;
        this.draggedCenterCol = col + piece.getWidth() / 2;
  
    }

    public void onMouseReleased() {
        if(!validateMove())
            return;

        if(draggedCenterRow != -1 && draggedCenterCol != -1) {
            if(!game.movePiece(piece, new Coordinates(draggedCenterRow, draggedCenterCol))) {
                
                drawPiece();
            }
        }
    } 

    @Override
    public void onMouseCliecked() {
        if(!validateMove())
            return;
        
            deselection();
        // Border selected piece
        this.isSelected = true;
        this.drawPiece();

        PieceView.currentSelectedPiece = this;

        
    }

    @Override
    public void onMouseEntered() {
        return;
        
    }

    public static void deselection() {
        // Remove previous selected piece border
        if(PieceView.currentSelectedPiece != null) {
            PieceView.currentSelectedPiece.isSelected = false;
            PieceView.currentSelectedPiece.drawPiece();
        }
    }

    private boolean validateMove() {
        return game.isOnGoing();
    }

    public void onSelected() {
        this.isSelected = true;
    }




    @Override
    public void modelUpdtae(Object arg0) {
        drawPiece();
    }




    

}
