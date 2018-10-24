/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileiowithprocessing;

/**
 *
 * @author sanjay
 */
public class ArtWork {
    public String id ;
    String artist;
    Integer year;
    String location;

    public ArtWork(String[] tokens) throws NumberFormatException, IllegalArgumentException {
        id = tokens[0];
        artist = tokens[1];
        year = Integer.parseInt(tokens[2]);
        location = tokens[3];
        
        int intYear = year.intValue();
        
        print();
        if ( intYear < 0) {
            throw new IllegalArgumentException("Year out of bounds - " + intYear);
        }
    }
    public String toString() {
        String s = "ID = " + id + "; Artist = " + artist + "; Year = " + year + ": Location = " + location;
        return s;
    }
    
    public void print() {
        System.out.println(toString());
    }
}
