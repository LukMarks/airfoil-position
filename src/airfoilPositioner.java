/*==============================
* Author: Lucas Marques Moreno
* Date: 08/20
* Description: A simple program
* to rotate and resize an
* airfoil form a .dat ou .csv
* file.
**==============================
* */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class airfoilPositioner {


    // TODO define getter and setter
    private String airfoilName;
    private double chord;
    private double angleOfAttack;
    private double offset;
    private double span;
    private String isWing;
    
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
        newProfile.openAirfoilFile(newProfile.airfoilName);

    }


    public void openAirfoilFile(String fileName){

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line ="";


            while((line = br.readLine()) != null){
                String[] coord = line.split("\\s");
                System.out.println(coord[0]+"\t"+coord[1]);
            }

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public double[][] rotationalMatrix(double[][] airfoil){
        double[][] rotatedAirfoil = new double[2][];

        for(int i=0;i< airfoil.length;i++){

            double angleRad = angleOfAttack * Math.PI/180;
            shiftProfile();

            rotatedAirfoil[1][i] = airfoil[1][i]*Math.cos(angleRad)*(chord-offset) + airfoil[2][i]*Math.sin(angleRad)*chord;
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

    public void shiftProfile(){
        if(this.isWing == "n") {
            this.offset = chord * 0.25 + this.offset;
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
