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
public class Painting extends ArtWork {
    private String paintType;
    private String material;
    
    public Painting(String id, String artist, int year, String location, String paintType, String material) throws IllegalArgumentException{
        super(id, artist, year, location);
        this.paintType = paintType;
        this.material = material;
    }
    public Painting(String[] tokens) throws NumberFormatException, IllegalArgumentException{
        this(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5]);
    }
    @Override
    public String toString() {
        String out = super.toString() + "; Paint Type = " + paintType + "; Material = " + material;
        return out;
    }
    @Override
    public void print() {
        System.out.println("Painting :: " + toString());
    }
}
