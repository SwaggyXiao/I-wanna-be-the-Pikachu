import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroReturn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroReturn extends Buttom
{
    WelcomePage a;
   public IntroReturn(){
        super("return.png");
    }
    public void addedToWorld (World w){
        a = (WelcomePage)w;
    }

    public void act() 
    {
        
        if(!Greenfoot.mouseClicked(this)) super.checkClicking();
        else a.removeObject(this);
    } 
}