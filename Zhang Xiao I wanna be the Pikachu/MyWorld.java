/* Hi, Mr. Cohen
 * This really funnnnn game is about the journey of pikachu
 * I know you r very familiar with this game, but I still want to say that
 * this game is about keeping pikachu away from enemy and last as long as you can
 * 
 * ALL THE INSTRUCTIONS ARE IN THE "OPTION" BUTTOM IN CASE YOU DIDN"T CLICK IT
 * 
 * 
 * In this game, pikachu has 5 bloods, its own energy and the points (which you can spend it as dollars)
 * 
 * Pikachu can through thunder to some enemy, but it reduce its energy
 * 
 * Pikachu can also do a "big kill" to clear the page when it has full energy, 
 * but the energy will then go to 0.
 * 
 * There is a shop where you can upgrades and buy more fun stuff
 * 
 * There is a short repond at the end of the game.
 * 
 * Enjoy your game😊
 * 
 * 
 */



import greenfoot.*;
public class MyWorld extends World
{
    private GreenfootSound boom;
    //the sound for thunder
    private GreenfootSound pikaBoom;
    //the sound for pikachu big kill
    public GreenfootSound BGM;
    //the sound for beginning BGM
    Click_B Click_B_Icon;
    private GreenfootSound click;
    //the sound for clicking buttoms
    public int theMovingSpeedOfTheWorld=4;
    private Bird bird;// the actor bird as enemy
    private Attention bigAttention1;
    private Attention bigAttention2;
    private Rock rock1;// the actor of first rock as enemy
    private Rock rock2;// the actor of second rock as enemy (if necessary)
    int timerForBoom=0;//the timer of the pikachu big kill
    private Resume resume;//the actor of the resume icon
    private Restart restart;//the actor of the restart icon
    private Hospital hospital;//the actor of the hospital icon
    private BlackScreen Black;
    private BlackScreen black;
    int counterForBird=0;
    //the counter that controls dropping birds
    int rockRandomPosition1;// the X position of the first rock
    int rockRandomPosition2;// the X position of the second rock (if necessary)
    int counterForRock=0;//the timer to drop the rock
    int counterForTB=0;//The timer to drop the point icon
    public static Pikachu pikachu;//the main charactor PIKACHU
    int rockNumber;
    //The numer of rocks that will drop (usually 1 or 2)
    private Bird b;
    //the actor bird
    private PausePage pause;
    int timeForRock=300;
    int randomTime_Rock;
    int rockRandomPosition3;
    public Ground ground1;// the actor ground where it is moving
    private Ground ground2;
    private Cloud cloud;
    public boolean PagePause=false;
    boolean isKeyOff=true;
    private GreenfootImage HeartTwo;
    private GreenfootImage HeartThree;
    int birdX=1200;
    int birdY=100;

    public static final int RockLife=500;
    private ThunderBall TB;
    int TBlocationY;
    int dropTBTimer=0;
    MyShop Shop;

    private Energy en;
    private LightYellowScreen LYS;
    int randomTime_Bird=Greenfoot.getRandomNumber(100);
    int randomTime_TB=Greenfoot.getRandomNumber(100);
    int randomTime_EN=Greenfoot.getRandomNumber(100);
    //GreenfootImage[] lifes={HeartOne,HeartTwo,HeartThree};
    public MyWorld()//constructor所有会在刚刚开始的时候出现的物体
    {
        super(1200, 800, 1, false);//supercall

        click=new GreenfootSound ("buttom.mp3");
        BGM=new GreenfootSound ("BGM.mp3");
        BGM.setVolume(25);
        ground1 = new Ground();
        addObject(ground1,600,getHeight()-25);
        ground2 = new Ground();
        addObject(ground2,1800,getHeight()-25);
        pikachu= new Pikachu();
        addObject (pikachu, 100, getHeight()/2);
        pikaBoom= new GreenfootSound ("pikachu attack.mp3");
        boom = new GreenfootSound ("thunder.mp3");
        Shop=new MyShop(this);
        Click_B_Icon=new Click_B();
        setPaintOrder(Resume.class, Restart.class,Hospital.class,PausePage.class, BlackScreen.class,LightYellowScreen.class,HealthBar.class,Pikachu.class,BlackDemage.class,Gengar.class,Bird.class,Rock.class,Ground.class);

    }
    //public GreenfootSound bgm = new GreenfootSound("Pokémon BGM.mp3");
    //public void music(){
    //    bgm.playLoop();
    //}
    //int x =Greenfoot.getRandomNumber(10);
    //range 0-9
    public void act()
    {
        //if (!bgm.isPlaying()){
        //    bgm.play();
        //}

        if(!PagePause){
            BGM.playLoop();
            if(timerForBoom==0){

                dropTBTimer++;
                counterForRock++;
                addTB();
                addBird(birdX,birdY);
                rockMove();
                if (Greenfoot.isKeyDown("k")&&pikachu.bk.fullEnergy&&timerForBoom==0)
                {
                    pikachu.setImage(pikachu.pikaboom);
                    pikachu.setLocation(pikachu.getX()+45,pikachu.getY()-85);
                    pikachu.groundSerface=690;
                    pikachu.energyLevel=0;
                    setPaintOrder(Resume.class, Restart.class,Hospital.class,PausePage.class,Pikachu.class,LightYellowScreen.class,Gengar.class,Bird.class,Rock.class, HealthBar.class,Cloud.class,BlackScreen.class,Ground.class,Click_B.class);
                    black=new BlackScreen(140);
                    addObject(black,600,400);
                    cloud=new Cloud(200);
                    addObject(cloud,-600,100);
                    timerForBoom++;
                }
            }
            else{
                BigKill();
            }   
        }
        stopTheWorld();

    }

    public void addTB(){
        if (dropTBTimer==randomTime_EN+150){
            int i=Greenfoot.getRandomNumber(5);
            if(i==3){
                en=new Energy(pikachu);
                addObject(en,1300,en.initialY+100);
            }

            int randomTime_EN=Greenfoot.getRandomNumber(100);
        }    
        else if(dropTBTimer==randomTime_TB+300){
            TB = new ThunderBall(pikachu);
            addObject(TB,1300,TB.initialY+100);
            dropTBTimer=0;

            int randomTime_TB=Greenfoot.getRandomNumber(100);
        }
    }

    public void addBird(int x,int y){
        if(counterForBird==400+randomTime_Bird){
            bird =new Bird(pikachu);
            addObject(bird,x,y);
            counterForBird=150;
            randomTime_Bird=Greenfoot.getRandomNumber(100);
        }
        else{
            counterForBird++;
            //
        }    
    }    

    private void rockMove()
    {
        if((counterForRock+randomTime_Rock)%timeForRock==timeForRock-50)
        {   
            if(rockNumber==0)
            {
                bigAttention1 =new Attention();
                addObject(bigAttention1,rockRandomPosition1,75);
                //add the attention sign
            }    
            else if(rockNumber==1)
            {
                bigAttention1 =new Attention();
                addObject(bigAttention1,rockRandomPosition1,75);
                bigAttention2 =new Attention();
                addObject(bigAttention2,rockRandomPosition2,75);
                //add the two attention signs
            }    
        }//at this point on the period, it can add the attention sign
        else if((counterForRock+randomTime_Rock)%timeForRock==0)
        {
            if(rockNumber==0)
            {
                rock1=new Rock(pikachu);
                addObject(rock1,rockRandomPosition1,0);
            }    //add one rock
            else if(rockNumber==1)
            {
                rock1=new Rock(pikachu);
                addObject(rock1,rockRandomPosition1,0);
                rock2=new Rock(pikachu);
                addObject(rock2,rockRandomPosition2,0);
            }   //add the two rocks
            counterForRock=0;
        }//at this point on the period, it will add the rocks
        if(counterForRock==1){
            rockRandomPosition1=Greenfoot.getRandomNumber(1200);
            rockNumber = Greenfoot.getRandomNumber(2);
            if(rockNumber==1){
                rockRandomPosition2=Greenfoot.getRandomNumber(1200);
                while(rockRandomPosition1-rockRandomPosition2>=-300&&rockRandomPosition1-rockRandomPosition2<=300){
                    rockRandomPosition2=Greenfoot.getRandomNumber(1200);
                }    //50% to get 2 rocks
                //prevent the two rocks ae too close to each other
            }    
            //determine should we put down 1 or 2 rocks
            randomTime_Rock=Greenfoot.getRandomNumber(100);
        }// at this point on the period, it will give the new lcation of the rock
    }

    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
    //code from Mr. Cohen to get distances

    private void stopTheWorld(){
        if(Greenfoot.isKeyDown("space")&&!PagePause&&isKeyOff){
            click.play();
            //create the sound of clicking
            PagePause=true;
            //the page is paused
            isKeyOff=false;
            //make the computer only detect the pressing key once
            Black=new BlackScreen(100);
            addObject(Black,600,400);
            pause=new PausePage();
            addObject(pause,600,400);
            //create the paused image
            resume=new Resume();
            addObject(resume,300,300);
            //create the icon of Resume
            restart=new Restart();
            addObject(restart,450,300);
            //create the icon of restart
            hospital=new Hospital();
            addObject(hospital,600,300);
            //create the icon of hostital
        } else if (Greenfoot.mouseClicked(hospital)&&PagePause){
            click.play();
            Greenfoot.setWorld(Shop);
        }else if (Greenfoot.mouseClicked(restart)&&PagePause){
            click.play();
            Greenfoot.setWorld(new WelcomePage());
        }
        else if((Greenfoot.isKeyDown("space")&&PagePause&&isKeyOff)||Greenfoot.mouseClicked(resume)){
            click.play();
            removeObject(Black);
            removeObject(pause);
            isKeyOff=false;
            PagePause=false;
        } 
        else if(!(Greenfoot.isKeyDown("space"))){
            isKeyOff=true;
        }

    }

    public boolean doThePagePause(){
        return PagePause||timerForBoom!=0;
    }

    public void BigKill(){

        if(timerForBoom==20)pikaBoom.play();

        if(timerForBoom==100){
            boom.play();
            //play the boooooooom sound
            LYS=new LightYellowScreen();
            addObject(LYS,600,400);
            //add the lighting
            timerForBoom++;
        }    
        else if(timerForBoom==200){
            removeObject(LYS);
            removeObject(black);
            removeObject(cloud);
            //remove all the animation element
            timerForBoom=0;
            //turn the timer off
            setPaintOrder(Resume.class, Restart.class,Hospital.class,PausePage.class, BlackScreen.class,HealthBar.class,LightYellowScreen.class,Pikachu.class,BlackDemage.class,Gengar.class,Bird.class,Rock.class,Ground.class);
            if(rock1!=null)removeObject(rock1);
            //remove the first rock if it exist
            if(rock2!=null)removeObject(rock2);  
            //remove the second rock if it exist
            if(bigAttention1!=null){
                removeObject(bigAttention1);
                pikachu.TotalRockKilled++;
            }
            if(bigAttention2!=null){
                pikachu.TotalRockKilled++;
                removeObject(bigAttention2);
            }
            //remove both attention sign since no rocks are coming
            if(bird!=null) removeObject(bird);
            //remove the bird  if it exist
            counterForBird=0;
            counterForRock=0;
            //all the counter
        }    
        else if(timerForBoom>=1){
            timerForBoom++;
            //if the timer starts, it will continue increasing
        }    

    }
    //this one is for the pikachu that uses the big kill
    public void addGengarClickB(){
        addObject(Click_B_Icon,460,170);
    }    
}