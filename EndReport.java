import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndReport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndReport extends Actor
{
    GreenfootImage backBoard;
    //Andale Mono
    
    private Font f=new Font("Andale Mono",true,false,30);
    private Color c=Color.YELLOW;
    String report;
    public EndReport(int timer,int killRock, int points,int energy){
        backBoard=new GreenfootImage("report.png");
        report = "You have last "+ timer + " seconds.\n"+"\nYou have killed "+killRock+" rocks.\n"+"\nYou have gained "+points+" dollars \nin total.\n"+"\nYou have gained "+energy+" bottles \nof energy drinks.";
        if(timer>=120){
                    report = "You have last "+ timer + " seconds.\n"+"\nYou have killed "+killRock+" rocks.\n"+"\nYou have gained "+points+" dollars \nin total.\n"+"\nYou have gained "+energy+" bottles \nof energy drinks.\nLink to next station: \nhttps://youtu.be/nFPcSB_MbIs";
        }    
        backBoard.setFont(f);
        backBoard.setColor(c);
        backBoard.drawString(report,60,60);
        this.setImage(backBoard);
    }    
}
