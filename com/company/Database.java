package com.company;

import Commands.Command;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

/**
 *Handles collection and does operations with it, such as adding new, generating ID, logging
 */
public class Database {
    private TreeSet<Person> collection;
    private ArrayList<String> historyLog;

    public void initialize(){
        collection = new TreeSet<>();
        historyLog = new ArrayList<>();
    }

    public TreeSet<Person> getCollection() {
        return collection;
    }

    /**
     * @param person
     */
    public void addNewElement(Person person){
        collection.add(person);
    }

    /**
     *
     */
    public void clearCollection(){
            collection.clear();
    }

    /**
     * @param command
     */
    public void updateHistoryLog(Command command){
        if (collection.size()>14)
            collection.remove(0);
        if(!command.getClass().getName().equals("Commands.Error"))
        historyLog.add(command.getClass().getName().replace("Commands.",""));
    }

    /**
     * @return String history log
     */
    public String getHistoryLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String str :historyLog){
            stringBuilder.append(str);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * @return total height of every Person
     */
    public int sumOfHeight(){
        int height = 0;
        for (Person person: collection){
            height+=person.getHeight();
        }
        return height;
    }

    /**
     * @returns ID
     */
    public Long generateID(){
        Long ID = 0L;
        if(collection.size()>0)
        for(Person p: collection){
            if(ID<p.getId()) ID = p.getId();
        }
        else ID = 0L;
        return ID+1;
    }

    /**
     * Generates new element with using console input
     * @param id
     * @return new Person
     */
    public Person generateNewElement(Long id){
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
        Date date = new Date();
        if(id==-1L) id = generateID();
        return new Person(id,name, XCoord, YCoord, date, height, eyeColor, hairColor,country, XLoc, Yloc, Zloc);
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
