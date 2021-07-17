import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YES here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YES extends Actor
{
    private MouseInfo m;

    private GreenfootImage idleImage;
    private GreenfootImage mouseOverImage;
    MyShop a;
    
    private boolean mouseOver;
    private boolean clicked;
    public YES (){
        mouseOver = false;
        clicked = false;
        initImages();
    }

    public void addedToWorld (World w){
        a = (MyShop)w;
    }

    public void act() 
    {
        if(!Greenfoot.mouseClicked(this)) checkClicking();
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
        idleImage = new GreenfootImage ("YES.png");
        idleImage.scale(100,100);

        mouseOverImage = new GreenfootImage("YES.png");
        mouseOverImage.scale(120,120);

        setImage(idleImage);
    }   
}
