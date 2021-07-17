import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightYellowScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LightYellowScreen extends Actor
{
    int Transparency;
    int Timer=0;
    boolean stop;
    MyWorld m;
    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
    //assign the world

    public void act() 
    {
        stop=m.PagePause;
        //see if the world is paused
        if(!stop){
            if(Timer<80){
                Timer++;
                this.getImage().setTransparency((int)255*(Timer)/80);
            }    
            //the yellow screen will reach 100% transparency in 80 frames
        }
    }
}

