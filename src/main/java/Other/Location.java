package Other;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.y, y) == 0 &&
                x.equals(location.x) &&
                z.equals(location.z) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }
}