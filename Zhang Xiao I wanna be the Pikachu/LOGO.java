import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LOGO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LOGO extends Actor
{
    private GreenfootImage Logo;
    private int xMovingSpeed=6;
    private int yMovingSpeed=4;
    
    
    public LOGO(){
        Logo=new GreenfootImage("LOGO.png");
        Logo.scale(650,450);
        setImage(Logo);
        //set the image
    }    
    public void act() 
    {
        if(getX()<600){
            setLocation(getX()+xMovingSpeed,getY()+yMovingSpeed);
        }    
        //moving till it's x cordinate reach 600
        //it will move to the center of the screen
    }    
}
