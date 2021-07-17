import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlackDemage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackDemage extends Demage
{
    private GreenfootImage blackBall;
    //the image of the black ball
    Rock r;
    //the rock this is targetting
    int TransparencyDecay;
    //the Transparency will go lower after time
    public BlackDemage(Actor a){
        blackBall=new GreenfootImage("Black ball.png");
        blackBall.scale(50,50);
        r=(Rock)a;
        TransparencyDecay=25;
        this.setImage(blackBall);

    }    

    public void act() 
    {
        if(r.getWorld() != null)
        {
            if(getDistance(this,r)>16){
                turnTowards(r.getX(),r.getY());
                move(8);
            }
            //if the distance is greater that 16, we say there is no collision
            else{
                setLocation(getX()-4,getY());
                super.disappear(TransparencyDecay);
            }    //disappear when it follows the rock
        }    
        else{
            setLocation(getX()-4,getY());
            super.disappear(TransparencyDecay);
        }    //this disappear method is for the rock that is disappear first
    }    

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //the method to get the distance
}
