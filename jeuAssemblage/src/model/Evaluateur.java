package model;

import java.util.ArrayList; // BP: Import non utilisé
import java.util.List;
import piece.*;
public class Evaluateur {

    public static Plateau board;

    public static int calculerAireRectangle(List<Piece> pieces) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Piece piece : pieces) {
            int midWidhtPiece = piece.getWidth() / 2;
            int midHeightPiece = piece.getHeight() / 2;

            int startRowPiece = piece.getOutterCenter().getX() - midHeightPiece;
            int endRowPiece = piece.getOutterCenter().getX() + midHeightPiece;
            int startColPiece = piece.getOutterCenter().getY() - midWidhtPiece;
            int endColPiece = piece.getOutterCenter().getY() + midWidhtPiece;

            minX = Math.min(minX, startRowPiece);
            minY = Math.min(minY, startColPiece);
            maxX = Math.max(maxX, endRowPiece);
            maxY = Math.max(maxY, endColPiece);
        }

        int largeurRectangle = maxX - minX + 1;
        int hauteurRectangle = maxY - minY + 1;

        return largeurRectangle * hauteurRectangle;
    }

    
}
