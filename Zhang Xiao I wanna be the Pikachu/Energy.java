import greenfoot.*;
public class Energy extends Actor
{
    Pikachu p;
    //the main charactor pikachu
    MyWorld m;
    //the world this actor will be in

    int counter=0;
    int initialY;
    
    //the starting Y value
    double dx;
    //the double for some trignometry calculations
    boolean stop;
    public Energy(Actor a){
        initialY=Greenfoot.getRandomNumber(500);
        dx=Math.asin((250-initialY)/(double)250);
        p=(Pikachu)a;
    } 

    public void addedToWorld (World w){
        m = (MyWorld)w;
    }//assign the world

    public void act() 
    {
        stop=m.doThePagePause();
        //get the stop boolean from the world to see if it stops
        if(!stop){
            movingPath(dx,counter);//create the sinosodial path way of the energy bottle
            hitPikachu();//checking for collision
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
    //the method to get distance between 2 actors

    public void movingPath(double x,int timer){
        setLocation(getX()-7,350-(int)(250*Math.sin(0.065*(timer-x*250))));
    }    
    //yes i know lots of calculations
    
    private void hitPikachu(){
        if(getDistance(this,p)<70){
            p.getEnergy();
            getWorld().removeObject(this);
        }
    }
    //collision ditection
}
