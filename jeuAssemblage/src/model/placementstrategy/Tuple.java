package model.placementstrategy;

import piece.Coordinates;

public class Tuple {
    private int v1;
    private int v2;
    private int v3;
    private Coordinates v4;
    /*
     * la classe Tuple a pour objectif de stocker les information d'une piece width, height, orientation
     * elle est sensé servir de substitut au type tuple qui existe en python
     */
     public Tuple(int v1, int v2, int v3, Coordinates v4) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }


    //dans notre contexte v1 est la largeur width
    //dans notre contexte v2 est la hauteur height
    //dans notre contexte v2 est l'orientation orientation

    
    public int getV1() {
        return v1;
    }
    
   

    public int getV2() {
        return v2;
    }
    public int getV3() {
        return v3;
    }
    public Coordinates getV4() {
        return v4;
    }
    public void setV1(int v1) {
        this.v1 = v1;
    }
    public void setV2(int v2) {
        this.v2 = v2;
    }
    public void setV3(int v3) {
        this.v2 = v3; // BP: Erreur de copier coller, il faut modifier `this.v3`. Ps: Votre IDE génère ce genre de méthode pour éviter des erreurs simples
    }
    public void setV4(Coordinates v4) {
        this.v4 = v4;
    }    
}