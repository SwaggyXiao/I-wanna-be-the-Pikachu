import greenfoot.*;
//public class Heart1
public class Heart extends Actor
{
    int life;
    private GreenfootImage hearts;
    //the image base for lots of heats
    private GreenfootImage oneHeart;
    //the only one heart
    int shakeTime=0;
    boolean moveLeft=true;
    public Heart(int blood){
        
        life=blood;
        hearts=new GreenfootImage(5*75-5,70);
        //each heart will have a 5 pixel apart from each other
        
        
        oneHeart = new GreenfootImage("heart.png");
        //assign the image
        oneHeart.scale​(70,70);
        for(int i=0;i<life;i++){
            
            hearts.drawImage(oneHeart,i*75,0);
            //draw the one heart to the base
        }    
        //just trying to draw 5 heart
        setImage(hearts);
    }
    public void act(){
        if(life<=3) {
            if(shakeTime==life+1){
                if(moveLeft) setLocation(this.getX()-4,this.getY());
                else if(!moveLeft) setLocation(this.getX()+4,this.getY());
                shakeTime=0;
                moveLeft=!moveLeft;
            }    
            //each amount of blood will shake at different speed
            else shakeTime++;
        }
        //this will shake the heart when it's lower than 3 blood
    } 
    
    public void updateBlood(int RLife)
    {
        life=RLife;
        hearts=new GreenfootImage(5*75-5,70);
        for(int i=0;i<life;i++){
            hearts.drawImage(oneHeart,i*75,0);
        }      
        setImage(hearts);
    }    
}