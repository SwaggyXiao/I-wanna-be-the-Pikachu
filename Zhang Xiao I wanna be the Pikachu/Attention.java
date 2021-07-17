import greenfoot.*;
public class Attention extends Actor
{
    int timer=0;//timer to show the sign
    private GreenfootImage sign1;
    private GreenfootImage sign2;
    boolean stop;
    MyWorld m;
    public Attention (){
        sign1 = new GreenfootImage("Sign(Big).png");
        sign2 = new GreenfootImage("Sign(Small).png");
    }
    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
    //get the world
    public void act() 
    {
        stop=m.doThePagePause();//to see if the world is pause
        if(!stop){
            timer++;
            if(timer%16==0)
            {
                this.setImage(sign1);
            }    
            else if(timer%10==8)
            {
                this.setImage(sign2);
            }    
            else if(timer==33)
            {
                getWorld().removeObject(this);
            }    
        }//if not pause, do these
    }
}
