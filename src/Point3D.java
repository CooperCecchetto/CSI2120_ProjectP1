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
