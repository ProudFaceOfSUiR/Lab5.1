package com.company;

import Commands.Command;

import java.util.ArrayList;
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

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(collection.size()>0) {
            collection.forEach(stringBuilder::append);
            stringBuilder.append("\n");
        } else stringBuilder.append("Collection is empty");
        return stringBuilder.toString();
    }

}
