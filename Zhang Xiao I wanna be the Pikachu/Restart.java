import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Restart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Restart extends Buttom
{
    MyWorld a;
    public Restart(){
        super("restart.png");
    }   
    public void addedToWorld (World w){
        a = (MyWorld)w;
    }

    public void act() 
    {
        stop=a.PagePause;
        if(stop) super.checkClicking();
        else getWorld().removeObject(this);
    }   
}
