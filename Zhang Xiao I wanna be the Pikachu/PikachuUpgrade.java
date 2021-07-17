import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class shengji升级 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PikachuUpgrade extends Product
{
   public PikachuUpgrade(){
       super("Pikachu Upgrade.png");
       
    }   
    public String toString(){
        return "\"Protein\"";   
    }    
    //the name of this actor
    public int price(){
        return 8;
    }
    //the price of it
}
