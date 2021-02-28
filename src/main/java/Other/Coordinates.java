package Other;

public class Coordinates {
    private float x;
    private Double y; //Поле не может быть null

    public void setCoordinatesFirst(float x, double y){
        this.x=x;
        this.y=Double.valueOf(y);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
