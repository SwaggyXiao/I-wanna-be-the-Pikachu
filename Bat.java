import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bat extends Actor
{
    int dx=4;//the speed moving to the left
    int dy=3;//speed moving up
    int timerForFlyingType=0;
    private GreenfootImage flyUp;
    private GreenfootImage flyHorizontal;
    private GreenfootImage flyDown;
    public Bat (){
        flyUp = new GreenfootImage("bat1.png");
        flyHorizontal = new GreenfootImage("bat2.png");
        flyDown = new GreenfootImage("bat3.png");
        //the three images of the bats animation
    }
    public void act() 
    {
        this.getImage().setTransparency(getImage().getTransparency() - 15);
        //graduately disappearing
        if (this.getImage().getTransparency() < 7)
        {
            getWorld().removeObject(this);
            //if it's transparency is to low, remove it.
        }
        else{
            setLocation(getX()-dx, getY()-dy);
            if(timerForFlyingType==3) this.setImage(flyHorizontal);
            else if(timerForFlyingType==6) this.setImage(flyDown);
            else if(timerForFlyingType==9) {
                this.setImage(flyUp);
                timerForFlyingType=0;
            }//change image every 3 seconds
      
            timerForFlyingType++;
        }
        
    }    
}
