import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BigKill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BigKill extends Actor
{
    private GreenfootImage killSign;
    private GreenfootImage blank;
    private GreenfootImage black;
    //the animation that will
    public boolean fullEnergy;
   
    public BigKill(){
        fullEnergy=false;
    //not yet have full energy
        killSign = new GreenfootImage("Attention Sign.png");
        killSign.scale​(100,100);
        //the base of the actor
        black = new GreenfootImage("black copy.png");
        black.scale​(100,100);
        black.setTransparency(100);
        killSign.drawImage(black,0,0);
        //the black screen that will cover it
        setImage(killSign);
    }    
    
    public void upDate(double energy) 
    {
        killSign = new GreenfootImage("Attention Sign.png");
        //get the base image
        killSign.scale​(100,100);
        //reset the image
        if((int)energy!=100){
            blank=new GreenfootImage(100,100-(int)energy);//cut the image
            blank.drawImage(black,0,0);
            if(fullEnergy) fullEnergy=false;
            //make the boolean false since the energy is not full
        }    
        //
        else fullEnergy=true;
        //the boolean is true so that pikachu can have big kill
        killSign.drawImage(blank,0,0);
        setImage(killSign);
    }    
    //recreate the image
    //method to update the energy
}
