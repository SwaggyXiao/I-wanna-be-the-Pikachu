import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class showPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowPoint extends Actor
{
    
    String numbers;
    
    private Font f=new Font("Comic Sans MS",true,false,40);//set the font to "Comic Sans MS"
    private GreenfootImage Points;
    private GreenfootImage theString;
    private GreenfootImage ThunderBall;
        private Color c=Color.YELLOW;//set Color to be yellow
    public ShowPoint(int pointsGet){
       numbers="X "+pointsGet;//set the string that will be shown
        Points = new GreenfootImage(170,70);
        ThunderBall= new GreenfootImage("ThunderBall.png");
        //stick the image thunderball to the back board
        Points.drawImage(ThunderBall,0,0);
        Points.setFont(f);
        Points.setColor(c);
        Points.drawString(numbers,85, 50);//draw the string to the back board
        this.setImage(Points);
        
    }    
    
    public void updatePoint(int pointsGet)
    {
        int increaseWidth = 0;
        if (pointsGet!= 0) increaseWidth=(int)Math.log(pointsGet);
        
        numbers="X "+pointsGet;
        Points = new GreenfootImage(155+increaseWidth*30,70);
        //determine the space that a letter will cost
        ThunderBall= new GreenfootImage("ThunderBall.png");
        Points.drawImage(ThunderBall,0,0);
        Points.setFont(f);
        Points.setColor(c);
        Points.drawString(numbers,85, 50);
        //draw the new string
        this.setImage(Points);
        //same process as previous
    }    
    //this will update the points if necessary
}
