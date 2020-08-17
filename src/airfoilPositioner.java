/*==============================
* Author: Lucas Marques Moreno
* Date: 08/20
* Description: A simple program
* to rotate and resize an
* airfoil form a .dat ou .csv
* file.
**==============================
* */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class airfoilPositioner {

    //private Scanner x;

    public static void main(String[] args) {

        System.out.println("===============================");
        System.out.println(" Welcome to Airfoil Positioner");
        System.out.println("===============================");

        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter with: ");
        System.out.println("The name of the airfoil (with extension)");
        //System.out.print("Airfoil's name: ");
        String airfoilName = scanner.nextLine();

        System.out.println("Chord: ");
        double chord = scanner.nextDouble();
        scanner.nextLine(); // handle enter key issue

        System.out.println("Angle of Attack: ");
        double angleAttack = scanner.nextDouble();
        scanner.nextLine(); // handle enter key issue

        System.out.println("Distance From Root: ");
        double planeDistance = scanner.nextDouble();
        scanner.nextLine(); // handle enter key issue

        System.out.println(planeDistance);
        scanner.close();*/

        // String[] lines = Files.readAllLines(new File("clarky.dat").toPath()).toArray(new String[0]);

        //System.out.println(lines);

      File airfoil = new File("/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat");
      try {
          Scanner airfoilFile = new Scanner(new File("/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat"));
          while(airfoilFile.hasNext()){
              String a = airfoilFile.next();
              String b = airfoilFile.next();
          }

          airfoilFile.close();
      } catch (Exception e) {
          System.out.println("Could not find the file");
      }


/*
        Scanner scnr = new Scanner(airfoil);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            System.out.println(line);
        }
        Scanner x;
        try{
            x = new Scanner(new File("/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat"));
        }
        catch(Exception e){
            System.out.println("Could not find the file");
        }
        while(x.hasNext()){
            String a = x.next();
            String b = x.next();

            System.out.println(a+"  "+b);
        }

        x.close();
    }*/

  /*  public void openFile(){
        try{
            x = new Scanner(new File("/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat"));
        }
        catch(Exception e){
            System.out.println("Could not find the file");
        }
    }

    public void readFile(){
        while(x.hasNext()){
            String a = x.next();
            String b = x.next();

            System.out.println(a+"  "+b);
        }
    }

    public void closeFile(){
        x.close();
    }*/
    }

    public double[][] rotationalMatrix(double[][] airfoil, double chord, double angle){
        double[][] rotatedAirfoil = new double[2][];

        for(int i=0;i< airfoil.length;i++){

            double angleRad = angle * Math.PI/180;

            double zLocal = chord*0.25;

            rotatedAirfoil[1][i] = airfoil[1][i]*Math.cos(angleRad)*(chord-zLocal) + airfoil[2][i]*Math.sin(angleRad)*chord;
            rotatedAirfoil[2][i] = (airfoil[2][i]*Math.cos(angleRad)-airfoil[1][i]*Math.sin(angleRad))*chord;
        }
        return rotatedAirfoil;
    }

    public void saveAirfoil(double[][] airfoil, double z, String name){
        String saveName = "rotated"+name;
        try{
            FileWriter saveFile = new FileWriter(saveName);
            for(int i=0;i< airfoil.length;i++){
                saveFile.write(airfoil[1][i]+"\t"+airfoil[2][i]+"\t"+z+"\n");
            }
        } catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

}
