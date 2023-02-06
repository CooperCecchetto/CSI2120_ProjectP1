

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PointCloud {
    // Represents point cloud with an arraylist of points
    ArrayList<Point3D> points = null;

    PointCloud(String filename){
        //puts points in xyz file into list
        points = new ArrayList<>();

        Path filePath = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            //skip header
            br.readLine();
            String line = br.readLine();

            //read through lines and create list of points
            while (line != null) {
                String[] coords = line.split("\\s+");
                Point3D point = new Point3D(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
                points.add(point);

                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String filename, int num) {
        System.out.println("X\tY\tZ");
        for (Point3D p : points) {
            System.out.println(p.getX() + "\t" + p.getY() + "\t" + p.getZ());
        }

        //creates output file with details of all recorded points
        String newFileName = filename.split("\\.")[0] + "_p" + num + ".xyz";

        File file = new File(newFileName);
        try {
            FileWriter outFile = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(outFile);
            bw.write("x\ty\tz");
            bw.newLine();
            for (Point3D p : points) {
                bw.write(p.toString());
                bw.newLine();
            }

            bw.close();
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates empty point cloud
    PointCloud(){
        points = new ArrayList<>();
    }

    public void addPoint(Point3D pt){
        points.add(pt);
    }

    // Returns one point at random.

    public Point3D getPoint(){
        if(points != null){
            Random r = new Random();
            Point3D point = points.get(r.nextInt(points.size() - 1));
            return point;
        }
        else{
            System.out.println("Point Cloud does not exist");
            return null;
        }
    }

    public Plane3D getPlane(){
        Point3D p1 = getPoint();
        Point3D p2 = getPoint();

        while(p2 == p1){
            p2 = getPoint();
        }

        Point3D p3 = getPoint();

        while(p3 == p1 || p3 == p2){
            p3 = getPoint();
        }

        return new Plane3D(p1, p2, p3);
    }

    // Generates iterator for points of the cloud
    public Iterator<Point3D> iterator(){
        return points.iterator();
    }
}
