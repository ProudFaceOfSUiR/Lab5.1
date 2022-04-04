package com.company;

import java.util.Date;

/**
 * Class carrying Persons to be part of the collection
 */
public class Person implements Comparable<Person> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(Long id, String name, Integer coordX, float coordY, java.util.Date creationDate, int height,
                  Color eyeColor ,Color hairColor, Country nationality, Long locX, float locY, Float locZ){
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(coordX, coordY);
        this.creationDate = creationDate;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = new Location(locX, locY, locZ);
    }

    public Long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int compareTo(Person person) {
        if (id > person.id)
            return 1;
        if(id < person.id)
            return -1;
        else return 0;
    }
    @Override
    public String toString(){
        String info = "ID: " + id + "\nName: " + name + "\nCoordinates:" + coordinates +
                "\nCreation date:" + creationDate + "\nHeight:" + height + "\nEye color:"
                + eyeColor + "\nHair Color:" + hairColor + "\nNationality:" + nationality + "\nLocation:" + location;
        return info;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public static class Coordinates {
        private Integer x; //Значение поля должно быть больше -616, Поле не может быть null
        private float y;

        public Coordinates(Integer x, float y){
            this.x = x;
            this.y = y;
        }

        public float getY() {
            return y;
        }

        public Integer getX() {
            return x;
        }

        @Override
        public String toString() {
            return "x=" + x +
                    ", y=" + y;
        }
    }
    public static class Location implements Comparable<Float>{
        private Long x; //Поле не может быть null
        private float y;
        private Float z; //Поле не может быть null

        public Location(Long x, float y, Float z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public Long getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public Float getZ() {
            return z;
        }

        @Override
        public String toString() {
            return "x=" + x +
                    ", y=" + y +
                    ", z=" + z;
        }

        @Override
        public int compareTo(Float location) {
            if((x+y+z)<location)
                return 1;
            if((x+y+z)>location)
                return -1;
            else return 0;
        }
    }
    public enum Color {
        GREEN("green"),
        RED("red"),
        BLACK("black"),
        YELLOW("yellow");
        private final String color;
        Color(String color){
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }
    public enum Country {
        USA("usa"),
        SPAIN("spain"),
        JAPAN("japan");
        private final String country;
        Country(String country){this.country = country;}

        public String getCountry() {
            return country;
        }
    }
}
