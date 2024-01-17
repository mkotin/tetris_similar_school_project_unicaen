package piece;

/**
 * La classe PieceL représente une pièce en forme de L dans un jeu de formes.
 * Elle étend la classe abstraite Piece.
 */
public class PieceL extends Piece {

    /**
     * Constructeur de la classe PieceL avec spécification de la largeur, de la hauteur et de l'orientation.
     *
     * @param width La largeur de la pièce.
     * @param height La hauteur de la pièce.
     * @param orientation L'orientation de la pièce (0 pour le nord, 1 pour l'est, 2 pour le sud, 3 pour l'ouest).
     */
    public PieceL(int width, int height, int orientation) {
        super(width, height, orientation);
    }

    /**
     * Constructeur de la classe PieceL avec spécification de la largeur et de la hauteur.
     * L'orientation par défaut est définie à 0 (nord).
     *
     * @param width La largeur de la pièce.
     * @param height La hauteur de la pièce.
     */
    public PieceL(int width, int height) {
        this(width, height, 0);
    }

    /**
     * Détermine la valeur de la cellule dans la matrice de la pièce en fonction de l'orientation.
     *
     * @param i L'indice de ligne dans la matrice.
     * @param j L'indice de colonne dans la matrice.
     * @param height La hauteur de la matrice.
     * @param width La largeur de la matrice.
     * @return true si la cellule à la position (i, j) est active, sinon false.
     */
    @Override
    protected boolean matrixCellVal(int i, int j, int height, int width) {
        boolean res = false;
        switch (orientation) {
            case 0: // nord
                res = (j == 0 || i == (height - 1));
                break;

            case 1: // est
                res = (i == 0 || j == 0);
                break;

            case 2: // sud
                res = (i == 0 || j == (width - 1));
                break;

            case 3: // ouest
                res = (j == (width - 1) || i == (height - 1));
                break;

            default: // nord par défaut
                res = (j == (width - 1) || i == (height - 1));
                break;
        }

        return res;
    }

    /**
     * Obtient la forme de la pièce, qui est "L".
     *
     * @return La forme de la pièce.
     */
    @Override
    public String getShape() {
        return "L";
    }
}
