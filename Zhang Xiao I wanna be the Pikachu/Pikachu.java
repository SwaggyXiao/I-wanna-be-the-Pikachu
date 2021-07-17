import greenfoot.*;
public class Pikachu extends Actor
{
    private GreenfootSound PikaThrowThunder;
    private GreenfootSound pikaJump;
    private GreenfootSound pikaPoint;
    private GreenfootSound pikaDemage;
    //all the sound that will help the animationa and use in the codes blow
    double dy=0;
    int pikachuBlood=5;
    //pikachu's blood
    int pikachuPiont=0;
    //this is the "money" you have in the game
    int ThunderKill=100;
    //the demage of thunder
    int limitingJumpTime=1;
    //the limit times pikachu can jump
    public ShowPoint sp;
    //the actor that will show the point
    int counterForFoot=6;
    //every 6 frames the pikachu will change feets
    int normalPikachuHeight=180;
    double energyLevel=0;
    int jumpTime=0;
    double Gravity = 1.5;
    //the gravitational acceleration
    int pikachuSpeed = 2;
    //the speed of pikachu
    int turningAngles = 20;
    //the turning angle when pikachu fall down
    int dyThatNeedsTurning=5;
    int groundSerface=770;
    ///the height the pikachu
    int leftSerfaceLimit=50;
    //the leftest pikachu can reach
    int timeToShootTheThunder = 1;
    //the timer to shoot thunder
    int jumpForOneTap=30;
    //the jump speed when only one tap
    int speedMoveForward=6;
    //speed where pikachu mives in x direction
    public static Gengar gengar;
    boolean gengarSwith=false;//when the game start, the user can not have gengar
    private GreenfootImage superpika2;
    private GreenfootImage pika1;
    private GreenfootImage pika2;
    private GreenfootImage superpika1;
    //all the different images of pikachu
    public GreenfootImage pikaboom;
    MyWorld a;//the main world
    Timer T;
    boolean stop;
    public Heart h;
    //the hearts on the top left corner
    public BigKill bk;
    //the energy level of pikachu
    boolean ifHitting=false;
    //check if pikachu is hitted by rocks or birds
    int flashingTimer=0; 
    //the time for pikachu to flash
    int TotalRockKilled=0;
    //the counter for how many rocks pikachu killed
    int TotalPointsGet=0;
    //the counter for how many points pikachu get
    int TotalEnergyGet=0;
    //the counter for how many energys pikachu get
    public SimpleTimer timer = new SimpleTimer();
    //change the frames into second
    boolean isPikachuStop=false;
    //check if the pikachu is not moving
    public Pikachu (){
        pikaJump=new GreenfootSound ("jump.wav");
        pikaJump.setVolume(80);
        PikaThrowThunder=new GreenfootSound ("ThrowThunder.wav");
        PikaThrowThunder.setVolume(80);
        pikaPoint=new GreenfootSound ("getPoints.wav");
        pikaPoint.setVolume(90);
        pikaDemage=new GreenfootSound ("demage.mp3");

        pika1 = new GreenfootImage("pikachu.png");
        pika2 = new GreenfootImage("pikachucopy.png");
        timer.mark();
        //start the timer
        superpika2 = new GreenfootImage("superPikachucopy.png");
        superpika1 = new GreenfootImage("superPikachu.png");
        pikaboom = new GreenfootImage("打雷.png");
        //assign all the image and sounds
    }

    public void addedToWorld (World w){
        a = (MyWorld)w;
        //assign the world
        h=new Heart(pikachuBlood);
        a.addObject(h, 230,60);
        //add the heart on the left top corner
        bk=new BigKill();
        a.addObject(bk, 75,170);
        //set the energy level
        sp=new ShowPoint(pikachuPiont);
        a.addObject(sp, 264,170);
        //show the coin pikachu get
        T=new Timer();
        a.addObject(T, 640,60);
        //add the time counter
    }

    public void act() 
    {
        stop=a.doThePagePause();
        //get if the world is pause
        if(!stop){
            if(isPikachuStop){
                isPikachuStop=false;
                timer.start();
            }    
            controlJump();
            //the method that is responsible for jumping
            controlHorizontalMovement();
            //control the movement of left and right
            stopByTheBorder(leftSerfaceLimit);
            //will be stop by the border
            rotateUpAndDown();
            throwThunder();
            //method to throw thunder
            updateEnergy();
            //for every frames, increase certain amount of energy
            flashing();
            //check the flashing animation
            renewTimer();
            //the method where checking if pikachu is hitted
            if(timer.millisElapsed()/1000==120){
                Greenfoot.setWorld(new EndWorld(a.ground1.getX(), this.getX(),timer.millisElapsed(),TotalRockKilled,TotalPointsGet,TotalEnergyGet));
                //lead to the ending world
                a.BGM.stop();
            }    
        }
        else{
            if(!isPikachuStop){
                isPikachuStop=true;
                timer.pause();
                //if the pikachu is not moving, the timer will stop
            }    

        }    
        //if not pause, do the normal animation
    }    

    public void getPoints(){
        if (pikaPoint.isPlaying()){
            pikaPoint.stop();
            pikaPoint=new GreenfootSound ("getPoints.wav");
            //reassign the new music
        }
        pikaPoint.play();
        //the music that will play when pikachu hit the point
        pikachuPiont++;
        TotalPointsGet++;
        sp.updatePoint(pikachuPiont);
    }  

    public void getEnergy(){
        if (pikaPoint.isPlaying()){
            pikaPoint.stop();
            pikaPoint=new GreenfootSound ("getPoints.wav");
            //reassign the new music
        }
        pikaPoint.play();
        //the music that will play when pikachu hit the energy
        energyLevel=99;
        //the energy will be set to 99
        TotalEnergyGet++;
    }    

    private void rotateUpAndDown(){
        if(dy>=-dyThatNeedsTurning && dy<=dyThatNeedsTurning){
            setRotation(0);

        }//pikachu will face up if jumping
        else if(dy< -dyThatNeedsTurning){
            setRotation(-turningAngles);
        }//pikachu will face straight if velocity=0
        else if(dy>dyThatNeedsTurning){
            setRotation(turningAngles);
        }//pikachu will face duwn if falling
    }

    private void stopByTheBorder(int y)
    {
        if(getY()+ normalPikachuHeight/2>= groundSerface){
            dy=0;
            //when pikachu landed on ground, there is no acceleration
            jumpTime=0;
            //the jumptime will be cleared
            setLocation(getX() - pikachuSpeed, groundSerface - normalPikachuHeight/2);
            //set the location where pikachu moving left
        }  
        if(getX()<=y){
            setLocation(y, getY());
        }  
        else if(getX()>=1150){
            setLocation(1150, getY());
            //if the 
        }
    }    

    private void controlJump()
    {
        String key = Greenfoot.getKey(); 
        if ("w".equals(key)&&jumpTime<limitingJumpTime)
        {

            if (pikaJump.isPlaying()){
                pikaJump.stop();
                //
                pikaJump=new GreenfootSound ("jump.wav");
                pikaJump.setVolume(80);
            }

            pikaJump.play();
            dy=-jumpForOneTap;
            jumpTime++;
        }

        else if(Greenfoot.isKeyDown("b")&&gengarSwith){
            gengar = new Gengar(this);
            getWorld().addObject(gengar,0,0);
            gengarSwith=false;
        }    
        dy=dy+Gravity;
        //add the acceleration
        setLocation(getX(), (int) (getY()+dy));
        //reset the location
    }

    private void controlHorizontalMovement()
    {
        if(Greenfoot.isKeyDown("d"))
        {
            move(speedMoveForward);
            //move to right with specific speed
        }
        else if(Greenfoot.isKeyDown("a"))
        {
            move(-speedMoveForward);
            //move to left with specific speed
        }
    }    

    private void throwThunder(){
        if (Greenfoot.isKeyDown("j")&&energyLevel>=10)
        {
            speedMoveForward=10;
            groundSerface=735;
            //the new ground level
            timeToShootTheThunder--;
            //the timer will decrease
            if(timeToShootTheThunder==20)
            {
                this.setImage(superpika1);
            }
            else if(timeToShootTheThunder==5){
                this.setImage(superpika2);
                Thunder thunder = new Thunder();
                getWorld().addObject(thunder,getX()-20,getY()-30);
                PikaThrowThunder.play();
                energyLevel-=10;
                //shoot the thunder and add the music for animation
            }
            else if(timeToShootTheThunder==0){
                timeToShootTheThunder=21;
            }
            //when the timer goes a round, it will start another loop
        }
        else
        {
            timeToShootTheThunder=21;
            speedMoveForward=6;
            //the normal moving speed
            groundSerface=770;
            //the new ground level
            if(speedMoveForward==6){
                pikaMovingFeet();
            }
        }

    } 

    public void pikaMovingFeet(){
        if(counterForFoot==3)
        {
            this.setImage(pika2);
            counterForFoot++;
            //every 3 seconds, switch feet
        }
        else if(counterForFoot==6)
        {
            this.setImage(pika1);
            counterForFoot=-1;
        }   //every 6 seconds, switch feet
        counterForFoot++;
    }  

    public void oneDamage(int kill){
        if (pikaDemage.isPlaying()){
            pikaDemage.stop();
            pikaDemage=new GreenfootSound ("demage.mp3");
        }
        //play the demage music

        pikaDemage.play();
        pikachuBlood-=kill;
        //pikachu will lose the blood which imported by the parameter
        if(pikachuBlood<=0) {
            Greenfoot.setWorld(new EndWorld(a.ground1.getX(), this.getX(),timer.millisElapsed(),TotalRockKilled,TotalPointsGet,TotalEnergyGet));
            //lead to the ending world
            a.BGM.stop();
        }
        //if pikachu have no more blood, the game automaticly stop
        else {
            h.updateBlood(pikachuBlood);
            ifHitting=true;
            flashingTimer=0;
            //pikachu start flashing
        }
        //it will updat pikachu's heart
    }    

    private void updateEnergy(){
        if(energyLevel<100)
        {
            energyLevel+=0.10;
            bk.upDate(energyLevel);//update the energy
        }
        //the energy will only increase if the energy is less than 100
        //pikachu will increase it's energy by 0.1 per frames
    }

    private void flashing(){
        if(ifHitting){
            if(flashingTimer==35){
                this.getImage().setTransparency(255);
                ifHitting=false;
            }   
            else if(flashingTimer%7==0&&(flashingTimer/7)%2==0)
                this.getImage().setTransparency(40);
            else if(flashingTimer%7==0&&(flashingTimer/7)%2==1)
                this.getImage().setTransparency(255);
            //for every 7 frames, the image will have a low transparency.
            //this make the flashing animation
            flashingTimer++;//timer increases
        }    
        else if(this.getImage().getTransparency()!=255) this.getImage().setTransparency(255);
        //incase the mistaken on transparancy happens
    }  
    String strForTime;
    private void renewTimer(){
        strForTime=timeInMinute(timer.millisElapsed());
        T.renewTimer(strForTime);//renew the time
    }  
    //This will give the picture of the timing on the top of the game page
    private String timeInMinute(int time){
        int i=(int)time/1000;
        if(i%60<10){
            return i/60+":0"+i%60;
        }    
        else return i/60+":"+i%60;
    }   
}
