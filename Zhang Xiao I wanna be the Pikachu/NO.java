import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NO extends Actor
{
    private MouseInfo m;

    private GreenfootImage idleImage;
    private GreenfootImage mouseOverImage;
    MyShop a;
    private boolean mouseOver;
    private boolean clicked;
    public NO (){
        mouseOver = false;
        clicked = false;
        initImages();//set the image
    }

    public void addedToWorld (World w){
        a = (MyShop)w;
    }
    //add the world

    public void act() 
    {
        if(!Greenfoot.mouseClicked(this)) checkClicking();
        //if it's not being clicked, always check for clicking
    }    

    private void checkClicking(){

        m = Greenfoot.getMouseInfo();
        if (m != null){
            // MouseOver State
            if (!mouseOver && Greenfoot.mouseMoved(this)){
                mouseOver = true;
            }
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
                mouseOver = false;
            }
            if (mouseOver){
                this.setImage(mouseOverImage);
            } else {
                this.setImage(idleImage);
            }

        }

    }    
    private void initImages(){
        idleImage = new GreenfootImage ("NO.png");
        idleImage.scale(100,100);

        mouseOverImage = new GreenfootImage("NO.png");
        mouseOverImage.scale(120,120);
//zoom in the image
        setImage(idleImage);
    }   
}

    


