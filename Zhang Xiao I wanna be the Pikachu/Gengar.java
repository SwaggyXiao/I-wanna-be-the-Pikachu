import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.ArrayList;
public class Gengar extends Actor
{
    MyWorld m;
    private GreenfootImage gengar;
    private int counterForBat=0;
    private int moveUpOrDown_Counter=0;
    public int Gengar_Damage=3;
    //the demage gengar brings every frame
    private GreenfootImage attact;
    private GreenfootImage gengarInverse;
    private GreenfootImage attactInverse;
    boolean stop;
    //see if the world stops
    Pikachu p;
    private Rock targetRock;
    private ArrayList<Rock> rocks;
    int gengarSpeed=15;//speed of gengar
    int rockSpeed;//speed of rock
    BlackDemage BD;//the black ball
    int shootTimer=1;//the timer to shot the tiny black ball
    public Gengar(Actor a){
        attact=new GreenfootImage("Gengar GX.png");
        attact.scale(150,150);
        gengar=new GreenfootImage("gengar.png");
        attactInverse=new GreenfootImage("Gengar GX.png");
        gengarInverse=new GreenfootImage("gengar.png");
        attactInverse.mirrorHorizontally();//create an image facing left
        attactInverse.scale(150,150);//set The Size of the image
        gengarInverse.mirrorHorizontally();//create an image facing left
        setImage(gengar);
        //all of these are the different images of the gengar which creates animations
        p=(Pikachu)a;
        //the pikahcu that gengar is following
    }

    public void addedToWorld (World w){
        m = (MyWorld)w;
        rockSpeed=m.theMovingSpeedOfTheWorld;
    }//get the world where gengar exist

    public void act() 
    {
        stop=m.doThePagePause();
        //get if the world is pause

        if(!stop) {

            //Hei I just love sine!!!
            //resetUpArray();
            move(p.getX(),p.getY(),rockSpeed);//follow pikachu
            addTheBat();//check if needed to add bat
            counterForBat++;//counter for creating the bat
            moveUpOrDown_Counter++;//counter for gengar move up and down
        }    //stop the animation if the world is stopping
    }

    private void addTheBat(){
        if(counterForBat==7){
            Bat bat = new Bat();
            int dx = Greenfoot.getRandomNumber(50);
            //random x location
            int dy = Greenfoot.getRandomNumber(100);
            //random y location
            getWorld().addObject(bat,this.getX()-50+dx,this.getY()-50+dy);
            //the new location of the bat
            counterForBat=0;//clear the counter
        }//create a bat every 7 frames
    }    //add the bat to create animations

    private void targetClosestRock ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        int numRocks;
        // Get a list of all Flowers in the World, cast it to ArrayList
        // for easy management

        numRocks = getWorld().getObjects(Rock.class).size();
        // If any flowers are found
        if (numRocks > 50) // If lots of flowers are found, search small area
        {
            rocks = (ArrayList)getObjectsInRange(80, Rock.class);
        }
        else if (numRocks > 20) // If less Flowers are found, search wider radius
        {
            rocks = (ArrayList)getObjectsInRange(180, Rock.class);
        }
        else    // If even fewer Flowers are found, search the whole World
            rocks = (ArrayList)getWorld().getObjects(Rock.class);

        if (rocks.size() > 0)
        {
            // set the first one as my target
            targetRock = rocks.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = MyWorld.getDistance (p, targetRock);

            // Loop through the objects in the ArrayList to find the closest target
            for (Rock o : rocks)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = MyWorld.getDistance(p, a);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetRock = o;
                    closestTargetDistance = distanceToActor;
                }
            }
        }
    }

    /**
     * 
     * 
     * 
     * 
     *                                  Credic to Mr. Cohen
     *                          I literally can not make that code lol
     * 
     * 
     *                                      Certificate of bug simulation (IDK if u can see this comment haha)
     * 
     * 
     */
    private void move(int theX, int theY,int rockSpeed)
    {
        targetClosestRock();
        //get the closest rock on the page
        if(targetRock==null){
            goBackToPikachu(theX, theY,gengar,gengarInverse);
        }
        //use for the Gengar which doesn't have a target yet to "Follow Pikachu"
        else{
            if (targetRock.getWorld() != null&&targetRock.getX()>0){
                if(getDistance(this,targetRock)>120){
                    catchTheRock();
                    //go for the nearest rock on the page
                }
                //for all rock that is 120 pixels away
                else{
                    setLocation(getX()-targetRock.dx+(int)(3*Math.sin(0.15*moveUpOrDown_Counter)),getY()+(int)(3*Math.sin(0.15*moveUpOrDown_Counter)));
                    //using a very hard sine function to model the path of the gengar
                    if(shootTimer==0){
                        BD=new BlackDemage(targetRock);
                        getWorld().addObject(BD,getX(),getY());
                        //add the ball
                        shootTimer=30;
                        //timer to shoot the ball, every 30 frames
                    }//if the gengar touch the rock, it will shoot a black ball
                    shootTimer--;
                    //counting down the shooting timer
                    targetRock.detectGengar(Gengar_Damage);
                    //the target rock will lose blood
                }    
            }
            else goBackToPikachu(theX,theY,attact,attactInverse);
            //this happen if and only if there haven't been any rock yet, go back to pikachu
        }
    }

    private void catchTheRock(){
        if(targetRock.getX()<this.getX()){
            if(this.getImage()!=attactInverse)this.setImage(attactInverse);
            //check if the image are correct
            turnTowards(targetRock.getX()+2*(this.getX()-targetRock.getX()), targetRock.getY()+2*(this.getY()-targetRock.getY()));
            //turn toward the rock
            move(-gengarSpeed);
        }//if the rock is on its left side
        else {
            if(this.getImage()!=attact)this.setImage(attact);
            //check if the image are correct
            turnTowards(targetRock.getX(), targetRock.getY());
            //turn toward the rock
            move(gengarSpeed);//
        }//if the rock is on its right side
    }    

    private void goBackToPikachu(int theX, int theY,GreenfootImage a,GreenfootImage b){
        if(getDistance(this, theX-50, theY+ (int)(17*Math.sin(0.15*moveUpOrDown_Counter))-100)<100){
            this.setImage(gengar);
            //set the image when it is following the pikachu
            if(this.getRotation()>=359||this.getRotation()<=1) 
            {
                this.setRotation(0);
            }
            else if(this.getRotation()>180) this.setRotation(this.getRotation()+2);//turn at the shortest direction
            else if(this.getRotation()<=180) this.setRotation(this.getRotation()-2);//turn at the shortest direction
            setLocation(theX-50,theY+ (int)(17*Math.sin(0.15*moveUpOrDown_Counter))-100);//reset the following position
        }//if it is close to pikachu, stick to it
        else{
            if((theX-50)>this.getX()){
                this.setImage(a);
                turnTowards(theX-50,theY+ (int)(17*Math.sin(0.15*moveUpOrDown_Counter))-100);
                //turn toward pikachu
                move(gengarSpeed);
            }
            else{
                this.setImage(b);
                turnTowards(theX-50+2*(this.getX()-(theX-50)),theY+ (int)(17*Math.sin(0.15*moveUpOrDown_Counter))-100+2*(this.getY()-(theY+ (int)(17*Math.sin(0.15*moveUpOrDown_Counter))-100)));
                //turn toward pikachu
                move(-gengarSpeed);
            }    
        }//else will face pikachu and moving at it
    }    

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //mr. Cohen's method of getting distance (i mantion it sooooo many timessssss)
    public static float getDistance (Actor a, double x, double y)
    {
        double distance;
        double xLength = a.getX() - x;
        double yLength = a.getY() - y;
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //a new version of the getting distance method

}
