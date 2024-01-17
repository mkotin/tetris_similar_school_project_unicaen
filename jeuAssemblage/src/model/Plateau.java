package model;

import java.util.ArrayList;
import java.util.List;

import model.placementstrategy.PlacementStrategy;
import model.placementstrategy.RandomStrategy;
import model.validators.AbstractValidator;
import model.validators.OverflowValidator;
import model.validators.OverlappingValidator;
import observer.AbstractListenableModel;
import piece.Coordinates;
import piece.Piece;
import piece.PieceFactory;
import piece.PieceL;
import piece.PieceR;
import piece.PieceT;

public class Plateau extends AbstractListenableModel{
    protected int width;
    protected int height;
    protected List<Piece> pieces;
    protected AbstractValidator validator;
    private PlacementStrategy placementStrategy;
    private int nbPieces;
    private boolean isEnabled;


    public Plateau(int width, int height, int nbPieces, PlacementStrategy strategy) {
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>();
        this.nbPieces = nbPieces;
        this.placementStrategy = strategy;
        setValidator();
        
    }

    public void setValidator() {
        this.validator = AbstractValidator.buildChain(new OverflowValidator(), new OverlappingValidator());
    }

    public boolean addPiece(Piece piece, Coordinates position){
        if(this.validate(piece, position)) {
            piece.setOutterCenter(position.getX(), position.getY());
            this.pieces.add(piece);
            return true;
        }
        return false;
    }

    public boolean movePiece(Piece piece, Coordinates position){
        
        if(validate(piece, position)) {
            piece.setOutterCenter(position.getX(), position.getY());
            return true;
        } else {
            return false;
        }
    }

    public boolean rotateClockWisePiece(Piece piece){
        piece.rotateClockWise();
        if(validate(piece, new Coordinates(piece.getOutterCenter().getY(), piece.getOutterCenter().getX()))) {
            return true;
        } else {
            piece.rotateAntiClockWise();
            return false;
        }
        
    }

    public boolean rotateAntiClockWisePiece(Piece piece){
        piece.rotateAntiClockWise();
        if(validate(piece, new Coordinates(piece.getOutterCenter().getY(), piece.getOutterCenter().getX()))) {
            return true;
        } else {
            piece.rotateClockWise();
            return false;
        }
    }

    public int evaluate() {
        throw new UnsupportedOperationException();
    }

    public void initialConfig(){
        this.placementStrategy.initialConfig(this);
        this.fireChangement();
    }

    public void initialConfigTmp(){
        PieceFactory pieceFactory = new PieceFactory();
        Piece piece1 = pieceFactory.buildRandom(); // BP: ces variables ne sont pas utilisées
        Piece piece2 = pieceFactory.buildRandom();
        PieceL pieceL = new PieceL(3,5,0);
        PieceR pieceR = new PieceR(3,5,2);
        PieceT pieceT = new PieceT(3,5,3);

        this.addPiece(pieceFactory.buildRandom(), new Coordinates(3 , 3));
        this.addPiece(pieceFactory.buildRandom(), new Coordinates(10 , 10));
        this.addPiece(pieceFactory.buildRandom(), new Coordinates(20 , 18));
        this.addPiece(pieceFactory.buildRandom(), new Coordinates(17 , 6));
        this.addPiece(pieceFactory.buildRandom(), new Coordinates(10 , 23));
        this.addPiece(pieceFactory.buildRandom(), new Coordinates(23 , 13));

    }

    public void setPlacementStrategy(PlacementStrategy placementStrategy) {
        this.placementStrategy = placementStrategy;
    }
    
    
    public boolean validate(Piece piece, Coordinates position){
        //return false;
        return this.validator.validate(this, piece, position);
    }

    public void reset() {
        this.pieces.clear();
        this.fireChangement();
    }




    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public AbstractValidator getValidator() {
        return validator;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
    
    public int getNbPieces() {
        return nbPieces;
    }

    public boolean randomPlacementStrategy() {
        return placementStrategy instanceof RandomStrategy;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        this.fireChangement();
    }

    public String affichagePlateau() {

        System.out.println(" on est dans la methode affichage plateau");
        int largeur = this.width;
        int hauteur = this.height;
        String res = "";
        // BP: Il est préférable d'utiliser un StringBuilder lors de concaténation de string dans une boucle.
        for (int i = 0; i < hauteur; i++) {
            res+="|";
            for (int j = 0; j < largeur; j++) {
                res+=".";
                for (Piece p : this.pieces) {


                    int pieceLigne= i - p.getOutterCenter().getX();
                    int pieceColone= j - p.getOutterCenter().getY();
            
                    // Vérification si les coordonnées relatives sont à l'intérieur de la matrice interne
                    if (pieceLigne >= 0 && pieceLigne < p.getCurrentMatrix().length &&
                        pieceColone >= 0 && pieceColone < p.getCurrentMatrix()[0].length &&
                        p.getCurrentMatrix()[pieceLigne][pieceColone]) {
                        // La case (i, j) devrait être occupée par une partie de la pièce
                        if(p.getCurrentMatrix()[pieceLigne][pieceColone]){
                            res+="*";
                        }
                    }
                    
                }
                //res+="."; 
            }
            res+="|"; 
            res+=("\n");  // Move to the next line after each row
        }
        return res;
    }

    

    



    
}
