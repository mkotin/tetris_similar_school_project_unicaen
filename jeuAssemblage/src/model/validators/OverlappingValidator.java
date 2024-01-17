package model.validators;

import java.util.List;

import model.Plateau;
import piece.Coordinates;
import piece.Piece;

public class OverlappingValidator extends AbstractValidator {

    public static int count =0;

    @Override
    public boolean validate(Plateau plateau, Piece piece, Coordinates toPosition) {
        boolean isValid = true;
   
        List<Piece> pieces = plateau.getPieces();
        for (Piece pieceExistante : pieces) {
            // If same piece
            if(pieceExistante.equals(piece)) {
                continue;
            }

            if (piecesOverlap(pieceExistante, toPosition, piece)) {
                isValid = false;
                break; // Pas besoin de vérifier les autres pièces si une collision est déjà détectée
            }
        }
    
        if(!isValid)
            return isValid;
        
        if(next != null) {
            return next.validate(plateau, piece, toPosition);
        }
    
        return isValid;
    }
    
   
    private boolean piecesOverlap(Piece pieceExistante, Coordinates toPosition, Piece newPiece) {

        List<Coordinates> cordonneesExistantes = pieceExistante.getOccupiedExternesCoordinates();
        Piece copyeNewPiece = Piece.clone(newPiece);
        copyeNewPiece.setOutterCenter(toPosition.getX(), toPosition.getY());
        List<Coordinates> newCoordinates = copyeNewPiece.getOccupiedExternesCoordinates();
    
        for (Coordinates existingCoord : cordonneesExistantes) {
            for (Coordinates newCoord : newCoordinates) {
                if(existingCoord.getX() == newCoord.getX() && existingCoord.getY() == newCoord.getY()){
                    return true;
                }
            }
        }
        return false;
    }
    
}
