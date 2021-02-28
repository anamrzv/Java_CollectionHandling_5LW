package Other;

public class Location {
    private Integer x; //Поле не может быть null
    private float y;
    private Double z; //Поле не может быть null

    private String name="Безымянное положение";

    public String getName(){
        return name;
    }

    public String getLocation(){
            return " Координаты x: "+x+" y: "+y+" z: "+z;
    }

    public void setLocation(int x, float y, double z, String name){
        this.x= Integer.valueOf(x);
        this.y=y;
        this.z= Double.valueOf(z);
        if (name!="") this.name=name;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}