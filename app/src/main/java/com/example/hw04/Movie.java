package com.example.hw04;

public class Movie {

    private String _name;
    private String _description;
    private String _genre;
    private int _rating;
    private int _year ;
    private String _imbd;

    public String getName() {
        return _name;
    }


    public String getDescription() {
        return _description;
    }



    public String getGenre() {
        return _genre;
    }



    public int getRating() {
        return _rating;
    }



    public int getYear() {
        return _year;
    }



    public String getImbd() {
        return _imbd;
    }



    public Movie (String name, String description, String genre, int rating, int year, String imbd)
    {
        this._name = name;
        this._description = description;
        this._genre = genre;
        this._rating = rating;
        this._year = year;
        this._imbd = imbd;
    }

}
