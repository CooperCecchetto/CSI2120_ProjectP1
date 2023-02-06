/**
 * Cooper Cecchetto
 * 300228878
 * CSI 2120
 * February 6th, 2023
 */
import java.util.ArrayList;
import java.util.List;

public class PlaneRANSAC {

    PointCloud pc, tmpCloud, dominantCloud = new PointCloud();
    Plane3D dominantPlane = null;
    //ArrayList<ArrayList<Point3D>> dominantPoints = new ArrayList<ArrayList<Point3D>>();
    Plane3D tmpPlane;
    //ArrayList<Point3D> processingPoints = new ArrayList<>();
    private double eps;

    public PlaneRANSAC(PointCloud pc) {
        this.pc = pc;
    }

    public void setEps(double eps){
        this.eps=eps;
    }

    public double getEps(){
        return eps;
    }

    public Plane3D RANSAC(PointCloud pc){
        tmpCloud.points.clear();
        tmpPlane = pc.getPlane();

        for (Point3D point : pc.points) {
            double distance = tmpPlane.getDistance(point);
            if(distance <= eps){
                tmpCloud.addPoint(point);
            }
        }
        return tmpPlane;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        return (int) Math.ceil(Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }

    public void run(int numberOfIterations, String filename){
        pc = new PointCloud(filename);
        PlaneRANSAC ransac = new PlaneRANSAC(pc);
        //ArrayList<Point3D> tempPoints = new ArrayList<>();

        for (int j = 0; j < 3; j++){

            for (int i = 0; i < numberOfIterations; i++) {
                ransac.RANSAC(pc);
                if (ransac.tmpCloud.points.size() > dominantCloud.points.size()) {
                    dominantCloud = tmpCloud;
                    dominantPlane = tmpPlane;
                }
            }

            /*System.out.println("Dominant plane " + (j+1) + " defined by equation: "
                    + dominantPlane.getA() + "x + " + dominantPlane.getB() + "y + "
                    + dominantPlane.getC() + "z + " + dominantPlane.getD() + " = 0");*/
            dominantCloud.save(filename, (j+1));

            for(Point3D p : dominantCloud.points){
                pc.points.remove(p);
            }
        }
        pc.save(filename, 0);
    }


    public static void main(String args[]){
        PointCloud pc = new PointCloud();
        PlaneRANSAC pr = new PlaneRANSAC(pc);
        pr.run(pr.getNumberOfIterations(0.99, 80), "PointCloud1.xyz");
    }
}
