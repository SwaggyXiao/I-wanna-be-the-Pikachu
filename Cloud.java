import greenfoot.*;
public class Cloud extends Actor
{
    int moveTime;
    MyWorld m;

    boolean stop;
    public Cloud(int timer){
        moveTime=timer-100;
        //timer is 200, so there is only 100 frames for the cloud to move to the center
    }    

    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
//assign the world
    public void act() 
    {
        stop=m.PagePause;
        //checking if the page is paused
        if(!stop){
            if(moveTime>0&&getX()<600) {
                setLocation(getX()+(int)(12),getY());
                moveTime--;
            }
            //continue moving until it mu=oves to the center of the screen
        }
    }    
}
