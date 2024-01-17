package views.console;
import model.placementstrategy.Tuple; // BP: Import non utilisé
import piece.Coordinates;
import piece.Piece;
import piece.PieceL;
import piece.PieceR;
import piece.PieceT;

import java.util.Scanner;

import model.Game;
import model.Plateau;
import model.placementstrategy.ArbitraryStrategy;
import model.placementstrategy.PlacementStrategy;

public class Demo {

    /*
     * Notation de la correction :
     *
     * Dans les fichiers sources, vous trouverez des commentaires qui commencent par:
     *      - BP: Best Practice, ce sont de simples conseils pour optimiser, rendre plus clair, rien de bien méchant
     *      - Warning: Ce sont de petites erreurs qui peuvent provoquer des bugs, ou un passage qui rend le code illisible
     *      - Error: Ce sont de grosses erreurs, c'est un -1000 points sur la note finale
     */

    public static void main(String [] args){
        PlacementStrategy abritaryPlacementStrategy = new ArbitraryStrategy();
        Plateau board = new Plateau(30, 30, 4, abritaryPlacementStrategy);
        board.initialConfig();
        
        Game jeu=new Game(board);
        Boolean termine= true; // BP: variable inutilisée
        jeu.start();
        Scanner scanner = new Scanner(System.in);
        //boucle de jeu
        while(jeu.isOnGoing()){
            System.out.println(board.affichagePlateau());
            System.out.println("**deplacement restant: ||"+jeu.getRemainingActions()+"|| **");
            if(jeu.canMove()){
                System.out.println("--déplacez une piece--");
                Piece[] tableau_piece = board.getPieces().toArray(new Piece[0]);
                for(int i=01
                 ; i<board.getPieces().size(); i++){
                    if( tableau_piece[i] instanceof PieceT){
                        System.out.println("T -- centre : ("+tableau_piece[i].getOutterCenter().getX()+","+tableau_piece[i].getOutterCenter().getY()+") -- indice :"+i);
                    }
                    if(tableau_piece[i] instanceof PieceL){
                        System.out.println("L -- centre : ("+tableau_piece[i].getOutterCenter().getX()+","+tableau_piece[i].getOutterCenter().getY()+")-- indice :"+i);
                    }
                    if(tableau_piece[i] instanceof PieceR){
                        System.out.println("R -- centre : ("+tableau_piece[i].getOutterCenter().getX()+","+tableau_piece[i].getOutterCenter().getY()+")-- indice :"+i);
                    }
                }
                
                System.out.println(" --saisissez l'indice de la piece que vous voulez déplacer--");

                String piece_to_move_string=scanner.nextLine(); // BP: Pas de snake_case, utilisez CamelCase
                int piece_to_move= Integer.parseInt(piece_to_move_string);
                while(piece_to_move>tableau_piece.length || piece_to_move<0){
                    System.out.println(board.affichagePlateau());
                    System.out.println("**deplacement restant: ||"+jeu.getRemainingActions()+"|| **");
                    System.out.println("--!l'indice saisie ne correspond à aucune piece , veuillez saisir un indice valide!--");
                    piece_to_move_string=scanner.nextLine();
                    piece_to_move= Integer.parseInt(piece_to_move_string);
                }

                System.out.println("--quel action voulez vous effectuer --");
                System.out.println("** d: deplacement | rh: rotation horaire | rah: rotation anti-horaire **");
                String action=scanner.nextLine();

                while(!action.equals("d") && !action.equals("rh") && !action.equals("rah")){
                    System.out.println("--!l'action saisie est invalide, veuillez saisir une action valide! --");
                    action=scanner.nextLine(); 
                }

                if(action.equals("d")){
                    System.out.println("--ou voulez vous déplacer votre piece?--");
                    System.out.println("--saisisez la coordonée x de l'emplacement d'arrivé de votre piece--");
                    String x_input = scanner.nextLine();
                    System.out.println("--saisisez la coordonée y de l'emplacement d'arrivé de votre piece--");
                    String y_input = scanner.nextLine();

                    int x_coordinate= Integer.parseInt(x_input);

                    int y_coordinate= Integer.parseInt(y_input);
                    while(!board.validate(tableau_piece[piece_to_move], new Coordinates(x_coordinate, y_coordinate))){
                        System.out.println(board.affichagePlateau());
                        System.out.println("**deplacement restant: ||"+jeu.getRemainingActions()+"|| ** \n");
                        System.out.println("--!les coordonées saisies ne sont pas valides!--");
                        System.out.println("--saisisez une coordonée x valide--");
                        x_input = scanner.nextLine();
                        System.out.println("--saisisez une coordonée x valide--");
                        y_input = scanner.nextLine();

                        x_coordinate= Integer.parseInt(x_input);
                        y_coordinate= Integer.parseInt(y_input);
                    }
                    
                    System.out.println("la pièce vas etre déplacé à l'emplacement ("+x_coordinate+","+y_coordinate+")");
                    tableau_piece[piece_to_move].getOutterCenter().setX(x_coordinate);
                    tableau_piece[piece_to_move].getOutterCenter().setY(y_coordinate);
                }
                if(action.equals("rh")){
                    board.rotateClockWisePiece(tableau_piece[piece_to_move]);
                }
                if(action.equals("rah")){
                    board.rotateAntiClockWisePiece(tableau_piece[piece_to_move]);
                }
            }


        }
        scanner.close();
    }
    
}