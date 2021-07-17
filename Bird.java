import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor
{
    Pikachu p;// the main charactor pikachu
    int timer=0;//a counter
    int speed;//speed of bird
    int pikachuX;//the x value of pikachu
    int pikachuY;//the y value of pikachu
    private GreenfootImage fly1;
    private GreenfootImage fly2;
    private GreenfootImage attack;
    int flyDistance;//the distance the bird can fly
    MyWorld m;//the main world
    boolean stop;//if the world stop
    public Bird (Actor a){
        p=(Pikachu)a;
        speed=10;//speed of bird
        flyDistance = Greenfoot.getRandomNumber(700);//the Random distance the bird can fly
        fly1 = new GreenfootImage("bird fly 1.png");
        fly2 = new GreenfootImage("bird fly 2.png");
        attack=new GreenfootImage("bird attact 1.png");
        //assign the image
    }//constuctor

    public void addedToWorld (World w){
        m = (MyWorld)w;
    }
    //get the world
    
    public void act() 
    {
        stop=m.doThePagePause();//get if the world should stop
        if(!stop){
            if(timer<150){
                //change image every 7 frames
                if(timer%15==7){
                    this.setImage(fly1);
                }    
                else if(timer%15==0){
                    this.setImage(fly2);
                    setLocation(getX()-10,getY());//move
                } 
                setLocation(getX()-flyDistance/150,getY());//250 is the time for the bird to fly
            }    
            //in the first 150 frames, the bird will flying
            else{
                if(timer<180){
                    this.setImage(attack);
                    turnTowards (p.getX(), p.getY());
                } //in the next 30 frames, bird will target pikachu
                else
                {
                    move (20);//move 20 pixels
                }
            }  
            timer++;
            disappear();//remove the bird if posible
        } //only run when the world is not stopped
    }

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //method to get the distance between two actors
    private void disappear(){
        if((this.getX()<-300||this.getY()>1500)){
            getWorld().removeObject(this);
        }    
        //remove the bird if it's out of bound
        else if(getDistance(p,this)<100){
            p.oneDamage(1);
            //give pikachu one demage
            getWorld().removeObject(this);
        }
        //the bird will let pikachu loss one blood and remove itself
    } 
    //the method where the bird
}
