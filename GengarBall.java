import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gengerball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GengarBall extends Product
{
    public GengarBall(){
        super("gengarBall.png");
        
    }    
    public String toString(){
        return "\"Gengar\"";   
    }    
    //the name of this actor
    public int price(){
        return 20;
    }   
    //the price of it
}
