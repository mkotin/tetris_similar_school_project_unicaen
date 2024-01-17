package model.validators;

import java.util.List; // BP: Import non utilisé

import model.Plateau;
import piece.Coordinates;
import piece.Piece;

public abstract class AbstractValidator {
    protected AbstractValidator next;

    /**
     * Builda chain of validator
     * @param first
     * @param chain
     * @return
     */
    public static AbstractValidator buildChain(AbstractValidator first, AbstractValidator... chain) {
        AbstractValidator head = first;
        for (AbstractValidator nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * Subclasses will implement this method with concrete validation.
     * @param pieces list of pieces of plateau
     * @param piece the piece to move or add 
     * @param toPosition the destination position of piece
     * @return wether the deplacement or addition is valid
     */
    public abstract boolean validate(Plateau plateau, Piece piece, Coordinates toPosition);
    
}
