import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomePage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomePage extends World
{
    private GreenfootSound begin;
    //the sound for beginning BGM
    private GreenfootSound click;
    //the sound for clicking buttoms
    private LOGO logo;
    private OptionsButtom OB;
    boolean StartButtom=true;
    private PlayButtom PB;
    IntroReturn IR;
    Introduction introduction;
    public WelcomePage()
    {    
        super(1200, 800, 1,false); 
        logo=new LOGO();
        addObject(logo,-250,-300);
        OB=new OptionsButtom();
        PB=new PlayButtom();
        begin=new GreenfootSound ("Begin.mp3");
        begin.setVolume(40);
        click=new GreenfootSound ("buttom.mp3");
        IR=new IntroReturn();
        introduction=new Introduction();
    }
    public void act(){
        begin.playLoop();
        if(logo.getX()>=600){
            if(StartButtom) {
                addObject(PB,600,560);
                
                addObject(OB,600,720);
                 StartButtom=!StartButtom;
            }  
        else if (Greenfoot.mouseClicked(PB)){
            begin.stop();
            //
            click.play();
            //play the click sound
            Greenfoot.setWorld(new MyWorld());
        }
        else if (Greenfoot.mouseClicked(OB)){
            click.play();
            addObject(introduction,600,400);
            //add the instruction
            addObject(IR,1135,688);
            //add the return buttom
        }
        else if(IR!=null){
            if(Greenfoot.mouseClicked(IR)){
                click.play();
                removeObject(introduction);
                //remove the instruction
                removeObject(IR);
                //remove the return buttom
            }    
        }    
    }
   
    }    
}
