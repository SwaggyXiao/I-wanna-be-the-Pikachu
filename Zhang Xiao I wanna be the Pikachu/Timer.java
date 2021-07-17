import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    GreenfootImage clock;
    GreenfootImage Background;
    String Time;

    private Font f=new Font("Andale Mono",true,false,80);
    //set font "Andale Mono"
    private Color c=Color.RED;
    //set the color into red
    public Timer(){
        Background=new GreenfootImage(400,100);
        clock=new GreenfootImage("clock.png");
        clock.scale(100,100);
        Background.drawImage(clock,0,0);
        Time="0:00";
        //the string will be shown in the screen
        Background.setFont(f);
        Background.setColor(c);
        Background.drawString(Time,100,85);
        this.setImage(Background);
    }    

    public void renewTimer(String time){
        if(Time!=time){
            //the loop will go if the time has changed
            Time=time;//renew the string
            Background=new GreenfootImage(400+70*(Time.length()-4),100);
            clock=new GreenfootImage("clock.png");
            clock.scale(100,100);
            Background.drawImage(clock,0,0);
            Background.setFont(f);
            Background.setColor(c);
            Background.drawString(Time,100,85);
            this.setImage(Background);
        }    
    }    //renew the new time
}
