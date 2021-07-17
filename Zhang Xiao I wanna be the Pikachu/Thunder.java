import greenfoot.*;
public class Thunder extends Actor
{
    int rockBlood;
    int thunderSpeed = 20;
    public void act() 
    {
        setLocation(getX()+thunderSpeed,getY());
    } //moving at a speed of 20 pixels per frame to the right
}
