package model.placementstrategy;
import java.util.*; // BP: Import non utilisé
import model.Plateau;
import piece.Coordinates;
import piece.Piece;
import piece.PieceFactory;
public class ArbitraryStrategy implements PlacementStrategy{
    Tuple[] coordonnees;
    public ArbitraryStrategy( Tuple[] coordonnees){
        this.coordonnees=coordonnees;
    }
    public ArbitraryStrategy(){
        this.coordonnees= new Tuple[5];
        this.coordonnees[0] = new Tuple(6,6,1, new Coordinates(3 , 3));
        this.coordonnees[1] = new Tuple(6,15,2, new Coordinates(10 , 10));
        this.coordonnees[2] = new Tuple(10,10,1, new Coordinates(20 , 18));
        this.coordonnees[3] = new Tuple(15,20, 0, new Coordinates(17 , 6));
        this.coordonnees[4] = new Tuple(20,5,3, new Coordinates(10 , 23));
    }
    
    /*
     * methode sensé permettre une conctiguration arbitraire du plateau de jeu
     * il reçoit en parametre un tableau de couple sa longueur est egal au nombre de piece
     * contenant les couples de hauteurs et largeur pour chaque piece du jeu
     * reçoit 
     */
    @Override
    public void initialConfig( Plateau plateau ){
        // Vider le plateau
        plateau.reset();
        for(Tuple c: this.coordonnees){
            PieceFactory pieceFactory_temp=new PieceFactory(); // BP: Pas de snake_case en java, il faut utiliser le CamelCase
            pieceFactory_temp.withHeight(c.getV1());
            pieceFactory_temp.withWidth(c.getV2());
            pieceFactory_temp.withOrientation(c.getV3());
            Piece piece_temp= pieceFactory_temp.build(); // BP: Pareil ici pour la casse
            piece_temp.setOutterCenter(c.getV4().getX(), c.getV4().getY());
            
            try{
                plateau.addPiece(piece_temp, c.getV4());
            }
            catch(IllegalArgumentException e){
                System.out.println("une des coordonnées fournis est incorrect!!");
            }
        }
    }

}