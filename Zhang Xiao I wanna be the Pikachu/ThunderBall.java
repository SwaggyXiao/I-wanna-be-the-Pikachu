import greenfoot.*; 
public class ThunderBall extends Actor
{
    Pikachu p;
    //the main charactor pikachu
    MyWorld m;
    //the world that this actor exist
    
    int counter=0;
    int initialY;
    //the initial Y value
    double dx;
    //the innitial x value
    boolean stop;
    public ThunderBall(Actor a){
        initialY=Greenfoot.getRandomNumber(500);
        dx=Math.asin((250-initialY)/(double)250);
        //assign the starting x
        p=(Pikachu)a;
    } 

    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
//assign the world
    public void act() 
    {
        
        stop=m.doThePagePause();
        //see if the world is paused
        if(!stop){
            movingPath(dx,counter);
            //the really hard pathy way determine buy trignometry
            hitPikachu();
            //checking collision with pikachu
            counter++;
        }
    }    

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.hypot(xLength, yLength);
        return (float)distance;
    }

    public void movingPath(double x,int timer){
        setLocation(getX()-7,350-(int)(250*Math.sin(0.065*(timer-x*250))));
    }    
    
    private void hitPikachu(){
        if(getDistance(this,p)<70){
            p.getPoints();
            getWorld().removeObject(this);
        }
    }//if the distance between them is less that 70, we say they hit each other
}
