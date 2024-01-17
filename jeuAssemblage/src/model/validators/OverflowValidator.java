package model.validators;

import java.util.List; // BP: Import non utilisé

import model.Evaluateur; // BP: Import non utilisé
import model.Plateau;
import piece.Coordinates;
import piece.Piece;

public class OverflowValidator extends AbstractValidator {
 

    @Override
    public boolean validate(Plateau plateau, Piece piece, Coordinates toPosition) {
        boolean isValid = false;
        Piece copyPiece = Piece.clone(piece);
        copyPiece.setOutterCenter(toPosition.getX(), toPosition.getY());
        
        int midPieceWidth = copyPiece.getWidth() / 2;
        int midePieceHeight = copyPiece.getHeight() / 2;

        int pieceCenterRow = copyPiece.getOutterCenter().getX();
        int pieceCenterCol = copyPiece.getOutterCenter().getY();

        int starRowPiece = pieceCenterRow - midePieceHeight;
        int endRowPiece = pieceCenterRow + midePieceHeight;
        int startColPiece = pieceCenterCol - midPieceWidth;
        int endColPiece = pieceCenterCol + midPieceWidth;

        boolean debordement = starRowPiece < 0 || endRowPiece > plateau.getHeight() || startColPiece < 0 || endColPiece > plateau.getWidth();

        isValid  = !debordement;

        if(!isValid)
            return isValid;
        
        if(next != null) {
            return next.validate(plateau, piece, toPosition);
        }

        return isValid;
    }
    
}