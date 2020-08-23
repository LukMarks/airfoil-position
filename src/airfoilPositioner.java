/*==============================
* Author: Lucas Marques Moreno
* Date: 08/20
* Description: A simple program
* to rotate and resize an
* airfoil form a .dat ou .csv
* file.
**==============================
* */

import java.io.*;
import java.util.ArrayList;

public class airfoilPositioner {


    // TODO define getter and setter
    private String airfoilName;
    private double chord;
    private double angleOfAttack;
    private double offset;
    private double span;
    private String isWing;
    private ArrayList<ArrayList<Double>> airfoilCoordinates = new ArrayList<>(2); // List that stores X and Y components
    private ArrayList<ArrayList<Double>> newAirfoilCoordinates = new ArrayList<>(2); // List that stores the rotated X and Y components

    public static void main(String[] args) {

        System.out.println("===============================");
        System.out.println(" Welcome to Airfoil Positioner");
        System.out.println("===============================");

        airfoilPositioner newProfile = new airfoilPositioner();
/*
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

        System.out.println("Offset: ");
        newProfile.setOffset(scanner.nextDouble());
        scanner.nextLine(); // handle enter key issue

        System.out.println("Is it a section of a wing? (y/n)");
        newProfile.setIsWing(scanner.nextLine());
        scanner.nextLine(); // handle enter key issue


        scanner.close();
*/
        newProfile.setAirfoilName("/home/lucas/Github/airfoil-positioner/out/production/airfoil-positioner/clarky.dat");
        newProfile.setChord(10.0);
        newProfile.setOffset(0);
        newProfile.shiftProfile();
        newProfile.openAirfoilFile(newProfile.airfoilName);
        newProfile.rotationalMatrix();
        newProfile.saveAirfoil();
        newProfile.saveAirfoil();
    }


    public void openAirfoilFile(String fileName){

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line ="";
            double test;
            initializerAirfoilCoordinates();
            while((line = br.readLine()) != null){
                String[] coord = line.split("\\s+");
                //System.out.println(coord[0]+"\t"+coord[1]);
                test = Double.parseDouble(coord[1]) + Double.parseDouble(coord[0]);
                //System.out.println(test);
                this.airfoilCoordinates.get(0).add(Double.parseDouble(coord[0]));
                this.airfoilCoordinates.get(1).add(Double.parseDouble(coord[1]));
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    public void rotationalMatrix(){
        //Apply the rotation matrix and the correct size to the airfoil.

        double newX;
        double newY;
        initializerNewAirfoilCoordinates();
        for(int i=0;i< this.airfoilCoordinates.get(1).size();i++){
            double angleRad = angleOfAttack * Math.PI/180;

            newX = this.airfoilCoordinates.get(0).get(i)*Math.cos(angleRad)*(this.chord-offset) + this.airfoilCoordinates.get(1).get(i)*Math.sin(angleRad)*this.chord;
            newY = (this.airfoilCoordinates.get(1).get(i)*Math.cos(angleRad) - this.airfoilCoordinates.get(0).get(i)*Math.sin(angleRad))*this.chord;
            this.newAirfoilCoordinates.get(0).add(newX);
            this.newAirfoilCoordinates.get(1).add(newY);
        }
    }

    public void saveAirfoil(){
        String saveName = this.airfoilName;
        System.out.println(saveName);
        try{
            FileWriter saveFile = new FileWriter(saveName);
            for(int i=0;i< this.newAirfoilCoordinates.get(1).size();i++){
                saveFile.write(this.newAirfoilCoordinates.get(0).get(i)+"\t"+this.newAirfoilCoordinates.get(0).get(i)+"\t"+this.span+"\n");
            }
        } catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void shiftProfile(){
        if(this.isWing == "n") {
            this.offset = chord * 0.25 + this.offset;
        }
    }

    public void initializerAirfoilCoordinates() {
        for (int i=0; i < 2; i++) {
            this.airfoilCoordinates.add(new ArrayList());
        }
    }

    public void initializerNewAirfoilCoordinates() {
        for (int i=0; i < 2; i++) {
            this.newAirfoilCoordinates.add(new ArrayList());
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

    public void setIsWing(String isWing){
        this.isWing = isWing.toLowerCase();
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

    public String getIsWing(){
        return this.isWing;
    }
}
