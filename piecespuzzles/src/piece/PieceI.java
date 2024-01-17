package piece;

import java.util.List;

public interface PieceI {
    public  String getShape();
    public boolean[][] getCurrentMatrix();
    public int getWidth();
    public int getHeight();
    public int getOrientation();
    public Coordinates getInnerCenter();
    public Coordinates getOutterCenter();
    public void setOutterCenter(int x, int y);
    public boolean isOccupied(int i, int j);
    public String getKey();
    public void rotateClockWise();
    public void rotateAntiClockWise() ;
    public List<Coordinates> getOccupiedExternesCoordinates();
}
