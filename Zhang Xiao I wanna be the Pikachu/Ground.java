import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends GameGround
{
      MyWorld m;
      
    int groundSpeed;
    
    boolean stop;
    public void addedToWorld (World w){
        m = (MyWorld)w;
        groundSpeed=m.theMovingSpeedOfTheWorld;
        //get the moving speed of the World
    }
    
    public void act() 
    {
        
        stop=m.doThePagePause();
        //get if the world is pause
        if(!stop){
            super.move(groundSpeed);
        }
        //the animation will act only if the world is not pause
    }   
}
