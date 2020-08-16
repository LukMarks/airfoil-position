/*==============================
* Author: Lucas Marques Moreno
* Date: 08/20
* Description: A simple program
* to rotate and resize an
* airfoil form a .dat ou .csv
* file.
**==============================
* */

import java.util.Scanner;

public class airfoilPositioner {

    public static void main(String[] args){

        System.out.println("===============================");
        System.out.println(" Welcome to Airfoil Positioner");
        System.out.println("===============================");

        Scanner scanner = new Scanner(System.in);

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

        scanner.close();
    }

}
