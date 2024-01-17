package model.ai;


import java.util.Random;

import model.Evaluateur;
import model.Game;


/**
 * Choisi l'action avec le meilleur rendement(le plus petit rectangle)
 */
public class AdvancedAI extends RandomAI  {

    protected static int minArea = Integer.MAX_VALUE;
    protected static int next = 0;

     @Override
    public void nextMove(Game game) {


        if(next == 0) {
            randomMove(game); 

        } else if(next == 1) {
            randomRotate(game, false);
        } else if(next == 2) {
            randomRotate(game, true);
        }

        int newArea = Evaluateur.calculerAireRectangle(game.getBoard().getPieces());
        
        if(newArea >= minArea) {
            // Si l'action actuelle n'est pas efficace on choisis une autre action
            Random rand  = new Random(); // BP: Déclarez cet objet random une seule fois et réutilisez le
            next = rand.nextInt(3); // BP: Déclarez cette méthode static, ou ne déclarez pas next comme static
        }

        
    }
    


   
    
}
