/**
 * Cooper Cecchetto
 * 300228878
 * CSI 2120
 * February 6th, 2023
 *
 * Represents a 3D plane by its a b c and d values in the equation of form ax + by + cz + d = 0
 */
public class Plane3D {
    private double a, b, c, d;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    /**
     * Constructor that takes 3 points and determines their common plane using normal vector
     */
    public Plane3D(Point3D p1, Point3D p2, Point3D p3){

        Point3D vector1 = new Point3D(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
        Point3D vector2 = new Point3D(p3.getX() - p1.getX(), p3.getY() - p1.getY(), p3.getZ() - p1.getZ());

        Point3D normalVector = new Point3D(
                vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
                -(vector1.getX() * vector2.getZ() - vector1.getZ() * vector2.getX()),
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX()
        );

        this.a = normalVector.getX();
        this.b = normalVector.getY();
        this.c = normalVector.getZ();
        this.d = (normalVector.getX() * p1.getX()) + (normalVector.getY() * p1.getY()) + (normalVector.getZ() * p1.getZ());
    }

    /**
     * Constructor that takes all parameters in the form ax + by + cz + d = 0
     */
    public Plane3D(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Calculates distance of a given point to plane
     * @param pt Point whose distance is to be evaluated
     * @return Distance of given point to plane in question
     */
    public double getDistance(Point3D pt){
        return Math.abs(this.a*pt.getX() + this.b*pt.getY() + this.c*pt.getZ() + this.d) / Math.sqrt(Math.pow(this.a, 2) + Math.pow(this.b, 2) + Math.pow(this.c, 2));
    }

    public String toString(){
        return("Plane defined by equation: "
                + a + "x + " + b + "y + "
                + c + "z + " + d + " = 0");
    }
}
