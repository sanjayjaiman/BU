/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlinkedlist1;

/**
 *
 * @author sanjay
 */
public class ArtWork  {
    public String id ;
    String artist;
    Integer year;
    String location;

    public ArtWork(String id, String artist, int year, String Location) throws IllegalArgumentException {
        this.year = year;        
        if ( this.year < 0) {
            throw new IllegalArgumentException("Year out of bounds - " + year);
        }        
        this.id = id;
        this.artist = artist;
        this.location = Location;
//         print();
    }

    public ArtWork(String id, String artist, String year, String Location) throws NumberFormatException, IllegalArgumentException {
        this(id, artist, Integer.parseInt(year), Location);
    }
    
    public ArtWork(String[] tokens) throws NumberFormatException, IllegalArgumentException {
        this(tokens[0], tokens[1], tokens[2], tokens[3]);
    }
    
    @Override
    public String toString() {
        String s = "ID = " + id + "; Artist = " + artist + "; Year = " + year + ": Location = " + location;
        return s;
    }
    
    public String getId() {
        return id;
    }
    
    public void print() {
        System.out.println(toString());
    }
}
