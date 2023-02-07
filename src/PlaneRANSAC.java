/**
 * Cooper Cecchetto
 * 300228878
 * CSI 2120
 * February 6th, 2023
 */
import java.util.ArrayList;
import java.util.List;

public class PlaneRANSAC {

    PointCloud pc, tmpCloud, dominantCloud;
    Plane3D dominantPlane;
    Plane3D tmpPlane;
    private double eps;

    public PlaneRANSAC(PointCloud pc) {
        this.pc = pc;
        this.tmpCloud = new PointCloud();
        this.dominantCloud = new PointCloud();
        this.dominantPlane = null;
    }

    public void setEps(double eps){
        this.eps=eps;
    }

    public double getEps(){
        return eps;
    }

    public void RANSAC(PointCloud pc){

        //return tmpPlane;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        return (int) Math.ceil(Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }

    public void run(int numberOfIterations, String filename){

        for (int j = 0; j < 3; j++){

            for (int i = 0; i < numberOfIterations; i++) {
                tmpCloud.points.clear();
                tmpPlane = pc.getPlane();

                for (Point3D point : pc.points) {
                    double distance = tmpPlane.getDistance(point);
                    if(distance <= eps){
                        tmpCloud.addPoint(point);
                    }
                }

                if (tmpCloud.points.size() > dominantCloud.points.size()) {
                    dominantCloud = tmpCloud;
                    dominantPlane = tmpPlane;
                }
            }


            dominantCloud.save(filename, (j+1));

            for(Point3D p : dominantCloud.points){
                pc.points.remove(p);
            }
        }
        pc.save(filename, 0);
    }


    public static void main(String args[]){
        PlaneRANSAC pr;
        pr.setEps(3);
        pr.run(pr.getNumberOfIterations(0.99, 0.8), "PointCloud1.xyz");
    }
}
