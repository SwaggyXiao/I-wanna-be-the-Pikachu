import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Demage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Demage extends Actor
{
    int dx=4;
    //the speed moving to the left
    int timer=0;
    int TransparencyDecay=4;
    public void act() 
    {
        disappear(TransparencyDecay);
    }    
    public void disappear(int TransparencyDecay){
        this.getImage().setTransparency(getImage().getTransparency() - TransparencyDecay);
        if (this.getImage().getTransparency() < TransparencyDecay+1)
        {
            getWorld().removeObject(this);
        }
        //when the transparency is less than 5, remove it
        else{
            setLocation(getX()-dx, getY());
            timer++;
            //the timer increases
        }
        //moving to the right
    }    
}
