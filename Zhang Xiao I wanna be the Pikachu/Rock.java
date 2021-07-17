import greenfoot.*; 
public class Rock extends Actor
{
    int gravity=2;//the gravitational acceleration
    int dy=0;//the speed going down
    public int dx;//the speed to move to left
    
    Thunder d;
    //the thunder actor
    HealthBar h;
    Pikachu p;
    private GreenfootImage bloodtype1;
    private GreenfootImage bloodtype2;
    MyWorld m;
    private HealthBar hpBar;
    private int RLife;//the remaining rock life

    private Gengar gengar;//
    private int ThunderKill;//Pikachu demage
    boolean stop;
    //the boolean which check if the world is stopped
    int rockHeight=674;//the minimun altitude the rock can reach
    public Rock (Actor a){
        
        p=(Pikachu) a;// the actor pikachu
        ThunderKill=p.ThunderKill;//the demage from thunder
        this.gengar=p.gengar;//find the gengar if possible
       
    }

    public void addedToWorld (World w){
        m = (MyWorld)w;
        RLife = m.RockLife;
        h= new HealthBar(500,this);//add the hp actor
              m.addObject(h,getX(),getY()-100);
            dx=m.theMovingSpeedOfTheWorld;
    }//get the world the rock exsist

    public void act() 
    {
         
        stop=m.doThePagePause();
        //get if the world is pause
        if(!stop){
            d= (Thunder)getOneIntersectingObject(Thunder.class);//check of collision
            if(getY()<rockHeight){
                dy=gravity+dy;//acceleration
                setLocation(getX(),getY()+dy);//moving down
            }//this runs when the rock is dropping and haven't reach the ground
            else{
                setLocation(getX()-dx, rockHeight);//moving right
                turn(-5);
            } 
            //create the rolling animation
            

            if(this.getX()<-50){
                getWorld().removeObject(this);//if it moves to the left of the screen, remove
            }    
            else {
                detectThunder();//detect if it's hitted by the thunder
            }
        }
        //if not pause, do the normal animation
    }

    private void detectThunder(){
        if(d!=null)//if the thunder ehit the tock
        {
            int randomSign = Greenfoot.getRandomNumber(2);
            if(randomSign==0) randomSign=-1;
            int randomRange = Greenfoot.getRandomNumber(11);
            RLife-=ThunderKill+randomSign*randomRange;
            //All the above are just determine the random blood loss
            if(RLife<=0){
                getWorld().removeObject(d);//remove the thunder
                getWorld().removeObject(h);//remove its healthbar
                Demage demage = new Demage();//create the booooommmmm animation
                getWorld().addObject(demage,this.getX(),this.getY()+15);
                getWorld().removeObject(this);//remove the rock
                p.TotalRockKilled++;
            }//if the rock have no more blood
            else{
                getWorld().removeObject(d);
                h.updateBlood(RLife);
            }    //the the rock is still alive, update its blood
        }
        else if(getDistance (p,this)<130)
        {
            p.oneDamage(1);//pikachu will lose one blood
            getWorld().removeObject(this);//remove the rock
        }//checking for collision with pikachu
    } 

    public void detectGengar(int d){
            int randomSign = Greenfoot.getRandomNumber(2);
            if(randomSign==0) randomSign=-1;
            int randomRange = Greenfoot.getRandomNumber(2);
            RLife-=d+randomRange*randomSign;
            ///All these above are calculations for the random gengar demage
            if(RLife<=0){
                Demage demage = new Demage();
                getWorld().addObject(demage,this.getX(),this.getY()+15);
                getWorld().removeObject(this);
                p.TotalRockKilled++;
            }
            else{
                h.updateBlood(RLife);
        }
    }    
    //the method for the gengar collision

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //code from mr. cohen to check for distance between 2 actor
}