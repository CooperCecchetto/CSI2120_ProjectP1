/**
 * Cooper Cecchetto
 * 300228878
 * CSI 2120
 * February 6th, 2023
 *
 * RANSAC algorithm
 */

public class PlaneRANSAC {
    PointCloud pc, tmpCloud, dominantCloud;
    Plane3D dominantPlane, tmpPlane;
    private double eps;

    // Generates instance of ransac for specified point cloud, and initializes dominant cloud
    public PlaneRANSAC(PointCloud pc) {
        this.pc = pc;
        this.dominantCloud = new PointCloud();
    }

    public void setEps(double eps){
        this.eps=eps;
    }

    public double getEps(){
        return eps;
    }

    //Calculation for number of iterations of ransac algorithm to find dominant plane
    //with specified confidence and estimated percentage of points on said plane
    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        return (int) Math.ceil(Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }

    //Runs ransac algorithm numberOfIterations times to find the dominant plane of a cloud
    public void run(int numberOfIterations, String filename){

        dominantCloud.points.clear();
        dominantPlane = new Plane3D(0,0,0,0);

        for (int i = 0; i < numberOfIterations; i++) {

            tmpCloud = new PointCloud();
            tmpPlane = pc.getPlane();

            for (Point3D point : pc.points) {
                if(tmpPlane.getDistance(point) <= getEps()){
                    tmpCloud.addPoint(point);
                }
            }
            if (tmpCloud.points.size() > dominantCloud.points.size()) {
                dominantCloud = tmpCloud;
                dominantPlane = tmpPlane;
            }
        }
    }

    public static void main(String[] args){
        String[] file = new String[3];
        file[0] = "PointCloud1.xyz";
        file[1] = "PointCloud2.xyz";
        file[2] = "PointCloud3.xyz";
        PointCloud pc = new PointCloud(file[0]);
        PlaneRANSAC pr = new PlaneRANSAC(pc);
        pr.setEps(0.3);
        for(int i = 0; i < 3; i++) {
            pr.run(pr.getNumberOfIterations(0.99, 0.1), file[0]);

            System.out.println("Dominant plane " + (i + 1) + " defined by equation "
                    + pr.dominantPlane.getA() + "x + " + pr.dominantPlane.getB() + "y + "
                    + pr.dominantPlane.getC() + "z + " + pr.dominantPlane.getD() + " = 0");

            pr.dominantCloud.save(file[0], i+1);

            for(Point3D p : pr.dominantCloud.points){
                pc.points.remove(p);
            }
        }
        pc.save(file[0], 0);
    }
}
