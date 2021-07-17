import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class jia4xue2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddBlood extends Product
{
   public AddBlood(){
       super("addBlood.png");
       
       
    }   
    public String toString(){
        return "\"HP Up\"";
    }     
    //the name of this actor
    public int price(){
        return 7;
    }
    //the price of it
}
