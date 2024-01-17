package piece;

import java.util.ArrayList;
import java.util.Arrays; // BP: import non utilisé
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import piece.observer.AbstractListenableModel;
/*
    * Notation de la correction : 
    * 
    * Dans les fichiers sources, vous trouverez des commentaires qui commencent par: 
    *      - BP: Best Practice, ce sont de simples conseils pour optimiser, rendre plus clair, rien de bien méchant
    *      - Warning: Ce sont de petites erreurs qui peuvent provoquer des bugs, ou un passage qui rend le code illisible
    *      - Error: Ce sont de grosses erreurs, c'est un -1000 points sur la note finale
    */

/**
 * Parent classe of piece
 */
public abstract class Piece extends AbstractListenableModel {
    protected final int width;
    protected final int height;
    protected int orientation; // 0 for North, 1 for East, 2 for south, 3 for west
    protected Coordinates outterCenter;
    protected static Map<String, boolean[][]> piecesMatrices = new HashMap<String, boolean[][]>(); // BP: Pas besoin de répéter `String, boolean[][]`, vous pouvez directement faire `new HashMap<>()`

    /**
     * Constructor for piece
     * @param width
     * @param height
     * @param orientation
     */
    public Piece(int width, int height, int orientation) {
        if(orientation > 3)
            throw new IllegalArgumentException("Orientation can't be upper than 3");
        
        this.width = width;
        this.height = height;
        this.orientation = orientation;

        // Build matrix
        boolean[][] matrix = buildMatrix();
        piecesMatrices.put(getKey(), matrix); // add to hashmap
    }

    /**
     * Constructor for piece
     * @param width
     * @param height
     */
    public Piece(int width, int height) {
        this(width, height, 0);
    }

    public static Piece clone(Piece piece) {
        Piece clone = null;
        if(piece.getShape().equals("T")) {
            clone = new PieceT(piece.getWidth(), piece.getHeight(), piece.getOrientation());
        }
        if(piece.getShape().equals("R")) {
            clone = new PieceR(piece.getWidth(), piece.getHeight(), piece.getOrientation());
        }
        if(piece.getShape().equals("L")) {
            clone = new PieceL(piece.getWidth(), piece.getHeight(), piece.getOrientation());
        }

        // BP: On ne laisse pas de code mort dans une livraison
        //clone.setOutterCenter(piece.getOutterCenter().getX(), piece.getOutterCenter().getY());
        return clone;
    }
    /**
     * Build a matrix for an orientation
     * @return a matrix for an orientation
     */
    protected boolean[][] buildMatrix() {
        int width = 0;
        int height = 0;

        // Vertical orientation
        if(orientation == 0 || orientation == 2 ) {
            width = this.width;
            height = this.height;
        } else { // horizontal orientation: switch dimensions
            width = this.height;
            height = this.width;
        }

        // buil matrix
        boolean[][] matrix =new boolean[height][width];
        for(int i =0; i< height; i++){
            for(int j = 0; j< width; j++){
                matrix[i][j] = matrixCellVal(i, j, height, width);
            }

        }

        return matrix;
    }

    /**
     * Return a matrix cell value based on piece shape and orientation
     * @param i cell row
     * @param j cell col
     * @param height matrix height
     * @param width matrix width
     * @return a matrix cell value based on piece shape and orientation
     */
    protected abstract boolean matrixCellVal(int i, int j, int height, int width);


    /**
     * Return the shape of the piece
     * @return
     */
    public abstract String getShape();


    /**
     * Return matrix of a piece base on its current orientation
     * @return
     */
    public boolean[][] getCurrentMatrix() {
        return piecesMatrices.get(getKey());
    }

    /**
     * Getter for a piece current width
     * @return
     */
    public int getWidth() {
        return getCurrentMatrix()[0].length;
    }


    /**
     * Getter for a piece current height
     * @return
     */
    public int getHeight() {
        return getCurrentMatrix().length;
    }


    /**
     * Return the orientation of a piece
     * @return the orientation of a piece
     */
    public int getOrientation() {
        return orientation;
    }


    /**
     * Retunr inner center of a piece
     * @return inner center of a piec
     */
    public Coordinates getInnerCenter() {
        return new Coordinates(getWidth() / 2, getHeight() / 2);
    }

    /**
     * Return outter center of a piece
     * @return outter center of a piece
     */
    public Coordinates getOutterCenter() {
        return outterCenter;
    }

    
    /**
     * Return all pieces matirces
     * @return all pieces matirces
     */
    public static Map<String, boolean[][]> getPiecesMatrices() {
        return piecesMatrices;
    }

    /**
     * Return the key of a piece in the pieces matrices map
     * @return
     */
    public String getKey() {
        return "" + getShape() + width + height + getOrientation() + "";
    }

    public void setOutterCenter(int x, int y){
        this.outterCenter = new Coordinates(x, y);

        this.fireChangement();
    }

    /**
     * Return wether a cell of a piece is occupied or not
     * @param i column
     * @param j row
     * @return
     */
    public boolean isOccupied(int i, int j){
        return getCurrentMatrix()[i][j];
    }

    /**
     * Rotatce clockwise
     */
    public void rotateClockWise() {
        orientation = (orientation + 1) % 4;
        addMatrixToHashmap(); // add matrix to hashmap if not exists
        this.fireChangement();
    }

    /**
     * Rotatce anti clockwise
     */
    public void rotateAntiClockWise() {
        orientation = (orientation - 1 + 4) % 4;
        addMatrixToHashmap(); // add matrix to hashmap if not exists
        this.fireChangement();
    }

    /**
     * Add matrix to hashmap if not exists
     */
    protected void addMatrixToHashmap() {
        if(!piecesMatrices.containsKey(getKey())) {
            piecesMatrices.put(getKey(), buildMatrix());
        }
    }
    public List<Coordinates> getOccupiedExternesCoordinates() {
        return getOccupiedExternesCoordinatesAux(getCurrentMatrix(), getOutterCenter().getX(), getOutterCenter().getY());
    }

    // Méthode pour obtenir les coordonnées externes dans la petite matrice
    private List<Coordinates> getOccupiedExternesCoordinatesAux(boolean[][] smallMatrix, int centerX, int centerY) {
        int rows = smallMatrix.length;
        int cols = smallMatrix[0].length;

        // Stocker les coordonnées externes
        List<Coordinates> externalCoordinates = new ArrayList<>();

        int index = 0; // BP: variable inutile

        // Parcourir la petite matrice
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Vérifier si la valeur dans la petite matrice est 1
                if (smallMatrix[i][j] == true) { // BP: Possibilité de faire `if (smallMatrix[i][j])` sans le `== true`
                    // Calculer les coordonnées externes dans la grande matrice
                    int externalX = centerX - rows / 2 + i;
                    int externalY = centerY - cols / 2 + j;

                    // Stocker les coordonnées externes
                    externalCoordinates.add(new Coordinates(externalX, externalY));
                    index++;
                }
            }
        }

        // Redimensionner le tableau résultant pour éliminer les cases non utilisées
        return externalCoordinates;
    }

    private int[] getOccupiedExternesXCoordinates() { // BP: Cette méthode privée n'est pas utilisé
        boolean[][] smallMatrix = this.getCurrentMatrix();
        int centerX = getOutterCenter().getX();
        int centerY = getOutterCenter().getY();// BP: variable inutile

        int rows = smallMatrix.length;
        int cols = smallMatrix[0].length;

        // Stocker les coordonnées externes
        int[] externalXCoordinates = new int[rows];

        int index = 0;// BP: variable inutile

        // Parcourir la petite matrice
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int externalX = centerX - rows / 2 + i;
                externalXCoordinates [i] = externalX;
                index++;
            }
        }

        // Redimensionner le tableau résultant pour éliminer les cases non utilisées
        return externalXCoordinates;
    }

    private int[] getOccupiedExternesYCoordinates() { // BP: Cette méthode privée n'est pas utilisé
        boolean[][] smallMatrix = this.getCurrentMatrix();
        int centerX = getOutterCenter().getX(); // BP: variable inutile
        int centerY = getOutterCenter().getY();

        int rows = smallMatrix.length;
        int cols = smallMatrix[0].length;

        // Stocker les coordonnées externes
        int[] externalYCoordinates = new int[cols];

        int index = 0;// BP: variable inutile

        // Parcourir la petite matrice
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int externalY = centerY - cols / 2 + j;
                externalYCoordinates [i] = externalY;
                index++;
            }
        }

        // Redimensionner le tableau résultant pour éliminer les cases non utilisées
        return externalYCoordinates;
    }


    // BP: On ne met pas du code mort dans la version de livraison
    // public boolean[][] hardClockWiseRotation(boolean[][] mat) {
    //     final int M = mat.length;
    //     final int N = mat[0].length;
    //     boolean[][] ret = new boolean[N][M];
    //     for (int r = 0; r < M; r++) {
    //         for (int c = 0; c < N; c++) {
    //             ret[c][M-1-r] = mat[r][c];
    //         }
    //     }
    //     return ret;
    // }

    /**
     * Return string version of piece
     */
    @Override
    public String toString() {
        String string="";
        // BP: Quand on fait de la concatenation de string dans une boucle, on utilise un StringBuilder
        boolean[][] matrix = getCurrentMatrix();
        for(int i =0; i< getHeight(); i++){
            for(int j = 0; j< getWidth(); j++){
                if(matrix[i][j]) {
                    string+=" 1 ";
                } else {
                    string+=" . ";                    
                }
            }
            string+="\n";
        }
        string+="\n";
        return string;
    }

    




    



    

    


}
