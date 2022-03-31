package com.company;

import Commands.Command;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
public class Database {
    private TreeSet<Person> collection;
    private ArrayList<String> historyLog;
    public void initialize(){
        collection = new TreeSet<Person>();
        historyLog = new ArrayList<String>();
    }

    public TreeSet<Person> getCollection() {
        return collection;
    }
//todo move collection stuff to commands classes from here
    public void addNewElement(Person person){
        collection.add(person);
    }
    public void clearCollection(){
            collection.clear();
    }
    public void updateHistoryLog(Command command){
        if (collection.size()>14)
            collection.remove(0);
        if(!command.getClass().getName().equals("Commands.Error"))
        historyLog.add(command.getClass().getName().replace("Commands.",""));
    }

    public String getHistoryLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String str :historyLog){
            stringBuilder.append(str);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int sumOfHeight(){
        int height = 0;
        for (Person person: collection){
            height+=person.getHeight();
        }
        return height;
    }
    public Long generateID(){
        return (Long) (long) (collection.size() + 1);//todo norm ID
    }
    public Person generateNewElement(){
        System.out.print("Enter name: ");
        String name = FieldFiller.fillString(true);
        System.out.print("Enter coordinates\nX coordinate: ");
        Integer XCoord = FieldFiller.fillXCoord();
        System.out.print("Y coordinate: ");
        float YCoord = FieldFiller.fillYCoord();
        System.out.print("Enter height: ");
        int height = FieldFiller.fillHeight();
        System.out.print("Enter eye color: ");
        Person.Color eyeColor = FieldFiller.fillColor();
        System.out.print("Enter hair color: ");
        Person.Color hairColor = FieldFiller.fillColor();
        System.out.print("Enter nationality: ");
        Person.Country country = FieldFiller.fillNationality();
        System.out.print("Enter location:\nX coordinate: ");
        Long XLoc = FieldFiller.fillXLoc();
        System.out.print("Y coordinate: ");
        float Yloc = FieldFiller.fillYCoord();
        System.out.print("Z coordinate: ");
        Float Zloc = FieldFiller.fillZLoc();
        return new Person(generateID(),name, XCoord, YCoord, null, height, eyeColor, hairColor,country, XLoc, Yloc, Zloc);
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(collection.size()>0) {
            for (Person person : collection) {
                stringBuilder.append("\n");
                stringBuilder.append(person);
            }
            stringBuilder.append("\n");
        } else stringBuilder.append("Collection is empty");
        return stringBuilder.toString();
    }

}
