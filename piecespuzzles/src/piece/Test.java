package piece;

import java.util.Map;

public class Test {
    
    public static void main(String[] args) {
        // BP: Si ce sont vraiment des tests, ils doivent être fait dans les classes qui vont bien et ne pas paraître dans la version livraison
        // ===================== Test creation =================
        // Piece L
        // PieceL pieceN = new PieceL(3,5,0);
        // PieceL pieceE = new PieceL(4,10,1);
        // PieceL pieceS = new PieceL(3,5,2);
        // PieceL pieceW = new PieceL(3,5,3);

        // Piece T
        // PieceT pieceN = new PieceT(3,5,0);
        // PieceT pieceE = new PieceT(3,5,1);
        // PieceT pieceS = new PieceT(3,10,2);
        // PieceT pieceW = new PieceT(3,5,3);

      // Piece R
        // PieceR pieceN = new PieceR(3,5,0);
        // PieceR pieceE = new PieceR(3,5,1);
        // PieceR pieceS = new PieceR(3,5,2);
        // PieceR pieceW = new PieceR(3,5,3);

        // System.out.println(pieceN);
        // System.out.println(pieceE);
        // System.out.println(pieceS);
        // System.out.println(pieceW);

        // ===================== Test rotation =================
        // Clockwise
        boolean clockwise = true; // change this to test clockwise or anticw
        Piece[] tab = {new PieceL(5,9), new PieceR(5,9), new PieceT(5,9)};
        for(int i=0; i<3; i++) {
            System.out.println("=============================");
            Piece piece = tab[i];
            
            for(int j=0; j<=4; j++) {
                System.out.println(piece);
                if(clockwise)
                    piece.rotateClockWise();
                else
                    piece.rotateAntiClockWise();
            }
        }

      // ===================== Test hashmap =================
        // Piece piece1 = new PieceT(3,7);
        // Piece piece2 = new PieceT(3,7);
        // Piece piece3 = new PieceT(3,7);
        // Piece piece4 = new PieceT(3,7);
        // // Piece pieceR = new PieceR(4,6);
        // // Piece pieceR2 = new PieceR(4,6);

        //  for (Map.Entry<String, boolean[][]> entry : AbstractPiece.piecesMatrices.entrySet()) {
        //     System.out.println(entry.getKey() + ":" + entry.getValue());
        //  }

        //  piece1.rotateClockWise();
        //  System.out.println(piece1 + "\n" + piece2);

        //  for (Map.Entry<String, boolean[][]> entry : AbstractPiece.piecesMatrices.entrySet()) {
        //     System.out.println(entry.getKey() + ":" + entry.getValue());
        //  }

         // ===================== Test Factory =================
        //  PieceFactory pFactory = new PieceFactory();
        //  Piece p1 = pFactory.buildRandom();
        //  Piece p2 = pFactory.buildRandom();

        //  Piece p5 = pFactory.withHeight(5).withWidth(3).withOrientation(1).build();

        //  Piece p3 = pFactory.withPiece(1).build();

        //  Piece p4 = pFactory.build();

        //  System.out.println(p1);
        //  System.out.println(p2);
        //  System.out.println(p3);
        //  System.out.println(p4);
        //  System.out.println(p5);

        // Piece p = new PieceL(5, 5);
        // p.setOutterCenter(3, 9);
        // for (Coordinates coord : p.getOccupiedExternesCoordinates()) {
        //   System.out.println("(" + coord.getX() + "," + coord.getY() + ")");
        // }


        
        
    }
}