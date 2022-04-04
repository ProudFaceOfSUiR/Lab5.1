package com.company;

import java.util.Locale;
import java.util.Scanner;

public class FieldFiller {
    /**
     *
     * @param isNescessary depends if th field can be null
     * @return string from console
     */
    public static String fillString(boolean isNescessary){
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        if (isNescessary && string.equals("")){
            System.out.print("This field cannot be null, enter a valid string: ");
            string = fillString(true);
        } else if(string.equals("")){
            string = null;
        }
        return string;
    }

    /**
     *
     * @return new X coordinate
     */
    public static Integer fillXCoord(){
        Scanner scanner = new Scanner(System.in);
        Integer X = null;
        boolean valid = false;
        try {
             X = Integer.valueOf(scanner.nextLine());
             if (X>-616) valid = true;
        }catch (NumberFormatException ignored){
        }
        finally {
            if(!valid) {
                System.out.print("This field should be Integer and more than -616, try again: ");
                X = fillXCoord();
            }
        }
        return X;
    }

    /**
     *
     * @return new Y coordinate
     */
    public static float fillYCoord(){
        Scanner scanner = new Scanner(System.in);
        float Y;
        String scannerInp = scanner.nextLine();
        try {
            if(!scannerInp.equals(""))
            Y = Float.parseFloat(scannerInp);
            else Y = 0;
        }catch (NumberFormatException exception){
            System.out.print("This field should be float, try again: ");
            Y = fillYCoord();
        }
        return Y;
    }

    /**
     *
     * @return new color
     */
    public static Person.Color fillColor(){
        Scanner scanner = new Scanner(System.in);
        Person.Color colorEnum = null;
        String color = scanner.nextLine();
        for(Person.Color col:Person.Color.values()){
            if(color.equals(col.getColor())) colorEnum = col;
        }
        if(colorEnum==null) {
            System.out.print("Available choices: green, red, black, yellow. Try again: ");
            colorEnum = fillColor();
        }
        return colorEnum;
    }

    /**
     *
     * @return new height
     */
    public static int fillHeight(){
        Scanner scanner = new Scanner(System.in);
        int height = 0;
        boolean valid = false;
        try {
            height = Integer.parseInt(scanner.nextLine());

            if (height>0) valid = true;
        }catch (NumberFormatException ignored){
        }
        finally {
            if(!valid) {
                System.out.print("This field should be int and more than 0, try again: ");
                height = fillHeight();
            }
        }
        return height;
    }
    public static Person.Country fillNationality() {
        Scanner scanner = new Scanner(System.in);
        Person.Country countryEnum = null;
        String country = scanner.nextLine().toLowerCase(Locale.ROOT);
        for (Person.Country count : Person.Country.values()) {
            if (country.equals(count.getCountry())) countryEnum = count;
        }
        if (countryEnum == null) {
            System.out.print("Available choices: USA, Spain, Japan. Try again: ");
            countryEnum = fillNationality();
        }
        return countryEnum;
    }

    /**
     *
     * @return new location X
     */
    public static Long fillXLoc(){
        Scanner scanner = new Scanner(System.in);
        Long X;
        try {
            X = Long.parseLong(scanner.nextLine());
        }catch (NumberFormatException exception){
            System.out.print("This field should be Long, try again: ");
            X = fillXLoc();
        }
        return X;
    }

    /**
     *
     * @return new location Z
     */
    public static Float fillZLoc(){
        Scanner scanner = new Scanner(System.in);
        Float Z;
        try {
            Z = Float.parseFloat(scanner.nextLine());
        }catch (NumberFormatException exception){
            System.out.print("This field should be Float, try again: ");
            Z = fillZLoc();
        }
        return Z;
    }
}
