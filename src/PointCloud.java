import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

public class PointCloud {
    PointCloud(String filename){

    }

    PointCloud(){

    }

    public void addPoint(Point3D pt){

    }

    Point3D getPoint(){

    }

    public void save(String filename) {
        //prints cluster counts
        for (Cluster<Integer, Integer> c : clusterCounts) {
            System.out.println("Cluster " + c.getKey() + " contains " + c.getCount() + " points");
        }
        System.out.println(noisePoints + " noise points");

        //creates output file with details of all recorded points
        String newFileName = filename.split("\\.")[0] + "_clusters_" + eps + "_" + ((int) minPts) + "_" + clusterCounts.size() + ".csv";

        File file = new File(newFileName);
        try {
            FileWriter outFile = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(outFile);
            bw.write("x,y,z,C,R,G,B");
            bw.newLine();
            for (Point3D p : DB) {
                bw.write(p.toString());
                bw.newLine();
            }

            bw.close();
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Iterator<Point3D> iterator(){

    }
}
