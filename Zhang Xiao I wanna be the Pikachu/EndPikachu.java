import greenfoot.*;
public class EndPikachu extends Actor
{
    int counterForFoot=0;
    //counter for the walking animation
    GreenfootImage pika1;
    GreenfootImage pika2;
    
    public EndPikachu(){
        pika1 = new GreenfootImage("pikachu.png");
        pika2 = new GreenfootImage("pikachucopy.png");
        //get the image of pikachu's moving animation
    }    
    public void act() 
    {
        if(counterForFoot==3)
            {
                this.setImage(pika2);
                //change the image
                counterForFoot++;
            }
            else if(counterForFoot==6)
            {
                this.setImage(pika1);
                //change the image
                
                counterForFoot=-1;
                //change the counter to 0;
            }   
        counterForFoot++;
    }    
}
