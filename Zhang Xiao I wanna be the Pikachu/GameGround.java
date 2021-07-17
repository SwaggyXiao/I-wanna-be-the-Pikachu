import greenfoot.*;

public class GameGround extends Actor
{
    GreenfootImage ground;
    public GameGround(){
        ground=new GreenfootImage("地面.png");
        this.setImage(ground);
    }
    
    public void move(int speed){
        setLocation(getX()-speed,getY());
        if(getX()==-600){
            setLocation(1800,getY());
        }   
        //the ground will connect to the end
    }
}
