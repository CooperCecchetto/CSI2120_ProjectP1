/**
 * Cooper Cecchetto
 * 300228878
 * CSI 2120
 * February 6th, 2023
 *
 * Represents a point in a 3D space with x y and z coordinates
 */
public class Point3D {
    private double X, Y, Z;

    public Point3D(double x, double y, double z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
    public double getZ() {
        return Z;
    }

    public String toString() {
        return getX() + "\t" + getY() + "\t" + getZ();
    }
}
