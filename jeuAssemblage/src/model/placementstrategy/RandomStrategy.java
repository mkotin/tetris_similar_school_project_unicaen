package model.placementstrategy;
import java.util.*;

import model.Plateau;
import model.ai.RandomAI;
import piece.Coordinates;
import piece.Piece;
import piece.PieceFactory;
public class RandomStrategy implements PlacementStrategy{
    
    public RandomStrategy(){}

    @Override
    public void initialConfig( Plateau plateau){
        
        while (plateau.getPieces().size() < plateau.getNbPieces()) {
            initialConfigAux(plateau);
        }

    }

    public void initialConfigAux( Plateau plateau){
        // Vider le plateau
        plateau.reset();
        int i=0;
        Random random= new Random(); // BP: Déclarez cet objet dans le constructeur pour qu'il soit réutilisé
        ArrayList<Integer> exclusionX= new ArrayList<Integer>(); // BP: Pas besoin de préciser le type dans le diamant. Correction : `new ArrayList<>()`
        ArrayList<Integer> exclusionY= new ArrayList<Integer>(); 
        ArrayList<Integer> exclusionWidth= new ArrayList<Integer>();
        ArrayList<Integer> exclusionHeight= new ArrayList<Integer>();
        boolean shouldBreak = false;

        while (i<plateau.getNbPieces() && !shouldBreak){
            int outerX=RandomAI.generateRandomIntWithExclusionsAndMax(exclusionX, 30);
            int outerY=RandomAI.generateRandomIntWithExclusionsAndMax(exclusionY, 30);
            exclusionX.add(outerX);
            exclusionY.add(outerY);
            Coordinates outterCenter= new Coordinates(outerX, outerY);
            PieceFactory pieceFactory_temp=new PieceFactory(); // BP: Pas de snake_case, utilisez CamelCase
            Piece piece_temp= pieceFactory_temp.buildRandom();

            while(!plateau.validate(piece_temp, outterCenter)){
            
                if(exclusionX.size() >= plateau.getHeight() || exclusionY.size() >= plateau.getWidth()){
                    shouldBreak = true;
                    break;
                }
                outerX=RandomAI.generateRandomIntWithExclusionsAndMax(exclusionX, 30);
                outerY=RandomAI.generateRandomIntWithExclusionsAndMax(exclusionY, 30);
                exclusionX.add(outerX);
                exclusionY.add(outerY);
                
                outterCenter = new Coordinates(outerX, outerY);
                piece_temp= pieceFactory_temp.buildRandom();
            }
            plateau.addPiece(piece_temp,outterCenter);
            i++;
        }
    }
}