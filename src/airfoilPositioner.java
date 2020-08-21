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


    // TODO define getter and setter
    private String airfoilName;
    private double chord;
    private double angleOfAttack;
    private double offset;
    private  double span:
    private boolean isWing;
    
    public static void main(String[] args) {

        System.out.println("===============================");
        System.out.println(" Welcome to Airfoil Positioner");
        System.out.println("===============================");

        airfoilPositioner newProfile = new airfoilPositioner();

        Scanner scanner = new Scanner(System.in); // define scanner to read keyboad inputs

        System.out.println("Please enter with: ");
        System.out.println("The name(path) of the airfoil (with extension)");

         newProfile.setAirfoilName(scanner.nextLine());

        System.out.println("Chord: ");
        newProfile.setChord(scanner.nextDouble());
        scanner.nextLine(); // handle enter key issue

        System.out.println("Angle of Attack: ");
        newProfile.setAngleOfAttack(scanner.nextDouble());
        scanner.nextLine(); // handle enter key issue

        System.out.println("Distance From Root: ");
        newProfile.setSpan(scanner.nextDouble());
        scanner.nextLine(); // handle enter key issue

        scanner.close();

        newProfile.openAirfoil();

        // String[] lines = Files.readAllLines(new File("clarky.dat").toPath()).toArray(new String[0]);

        //System.out.println(lines);

    /*  File airfoil = new File(airfoilName);//"/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat");
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
*/

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


    public void openAirfoil(String name){
        //File airfoil = new File(name);//"/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat");
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

    }
    

    public double[][] rotationalMatrix(double[][] airfoil){
        double[][] rotatedAirfoil = new double[2][];

        for(int i=0;i< airfoil.length;i++){

            double angleRad = angle * Math.PI/180;

            double zLocal = chord*0.25;

            rotatedAirfoil[1][i] = airfoil[1][i]*Math.cos(angleRad)*(chord-zLocal) + airfoil[2][i]*Math.sin(angleRad)*chord;
            rotatedAirfoil[2][i] = (airfoil[2][i]*Math.cos(angleRad)-airfoil[1][i]*Math.sin(angleRad))*chord;
        }
        return rotatedAirfoil;
    }

    public void saveAirfoil(double[][] airfoil){
        String saveName = "rotated"+airfoilName;
        try{
            FileWriter saveFile = new FileWriter(saveName);
            for(int i=0;i< airfoil.length;i++){
                saveFile.write(airfoil[1][i]+"\t"+airfoil[2][i]+"\t"+span+"\n");
            }
        } catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    // Define the setters functions
    public void setAirfoilName(String airfoilName){
        this.airfoilName = airfoilName;
    }

    public void setChord(double chord){
        this.chord = chord;
    }

    public void setAngleOfAttack(double angleOfAttack){
        this.angleOfAttack = angleOfAttack;
    }

    public void setOffset(double offset){
        this.offset = offset;
    }

    public void setSpan(double span){
        this.span = span;
    }

    public void setIsWing(boolean isWing){
        this.isWing = isWing;
    }

    // Define getters function

    public String getAirfoilName(){
        return this.airfoilName;
    }

    public double getChord(){
        return this.chord;
    }

    public double getAngleOfAttack(){
        return this.angleOfAttack;
    }

    public double getOffset(){
        return this.offset;
    }

    public double getSpan() {
        return this.span;
    }

    public boolean getIsWing(){
        return this.isWing;
    }
}
