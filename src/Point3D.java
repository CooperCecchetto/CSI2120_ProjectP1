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
        return getX() + ", " + getY() + ", " + getZ();
    }

    //calculates euclidean distance between selected point and point pt
    public double distance(Point3D pt){
        //euclidean distance = sqrt(sum of squared differences)
        return Math.sqrt(Math.pow((getX() - pt.getX()), 2) + Math.pow((getY() - pt.getY()), 2) + Math.pow((getZ() - pt.getZ()), 2));
    }
}
