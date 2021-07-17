import greenfoot.*;
public class HealthBar extends Actor
{
    private GreenfootImage HPGreen;
    int restLife;
    int totalLife;
    int beforeLife;
    MyWorld m;
    boolean stop;
    //see if the world is paused
    Rock r;
    //the actor rock that will use this health bar
    public HealthBar(int TLife,Actor a){
        totalLife=TLife;
        beforeLife=totalLife;
        r=(Rock)a;
        HPGreen = new GreenfootImage("PH Bar.png");
        //set the background image
        setImage(HPGreen);
    }    

    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
    //assign the world
    public void act() 
    {
        stop=m.doThePagePause();
        if(!stop) bloodLocation();
        //if the world is not paused, the hp bar will keep following the rock
    }  

    public void bloodLocation(){
        if(r.getWorld()==null){
            getWorld().removeObject(this);
        }    
        //if the rock disappears, it will disappear as well
        else{
            setLocation(r.getX(),r.getY()-80);
        }    
        //always be 80 pixels above the rock
    }    
    private GreenfootImage redBlood;
    //the part where turns red
    public void updateBlood(int RLife)
    {
        restLife=RLife;
        //the rest life is assigned
        if((int)((1-(double)restLife/totalLife)*89)!=0){
            redBlood= new GreenfootImage(89, 11);
            redBlood.setColor(Color.RED);
            redBlood.fillRect(0, 0,(int)((1-(double)restLife/totalLife)*89),11);

            HPGreen.drawImage(redBlood, 4+89-(int)((1-(double)restLife/totalLife)*89),4);
        }
        //use the red rectangle to cover the green part
    }    
}
