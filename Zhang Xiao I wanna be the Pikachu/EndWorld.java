import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class EndWorld extends World
{

    private GreenfootSound boom;
    private GreenfootSound click;
    //the sound for clicking buttoms
    EndGround ground1;
    EndGround ground2;
    EndPikachu EP;
    BlackScreen BS;
    EndReport ER;
    Game_Over_Sign GOS;
    ReGame RG;
    public EndWorld(int groundX,int pikaX,int timer,int killedRock,int points,int energy)
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1,false); 
        boom=new GreenfootSound ("boom.mp3");
        click=new GreenfootSound ("buttom.mp3");
        ground1 = new EndGround();
        addObject(ground1,groundX,getHeight()-25);
        ground2 = new EndGround();
        BS=new BlackScreen(120);
        addObject(BS,600,400);
        if(groundX>600) addObject(ground2,groundX-1200,getHeight()-25);
        else addObject(ground2,groundX+1200,getHeight()-25);
        //see which ground should be added
        setPaintOrder(ReGame.class,EndReport.class,Game_Over_Sign.class,BlackScreen.class,EndPikachu.class,EndGround.class);
        EP=new EndPikachu();
        addObject(EP,pikaX,770 - 180/2);
        ER=new EndReport(timer/1000,killedRock,points,energy);
        addObject(ER,600,400);
        GOS=new Game_Over_Sign();
        addObject(GOS,600,100);
        RG=new ReGame();
        addObject(RG,1000,710);
        //add all the elements to the ending page
    }

    public void act(){
        boom.playLoop();
        if (Greenfoot.mouseClicked(RG))
        {
            boom.stop();
            click.play();
            //make the clicking sound
            Greenfoot.setWorld(new WelcomePage());
            //return to a new welcome page and start the new game
        }//checking if it click the return buttom

    } 
}    
