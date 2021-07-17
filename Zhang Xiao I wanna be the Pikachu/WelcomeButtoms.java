import greenfoot.*;
public class WelcomeButtoms extends Actor
{
    private MouseInfo m;

    private GreenfootImage idleImage;
    private GreenfootImage mouseOverImage;
    WelcomePage a;//the main world it's in
    private boolean mouseOver;
    private boolean clicked;
    String image;
    public WelcomeButtoms (String image){
        mouseOver = false;
        clicked = false;
        this.image=image;
        initImages();
        //set the initial image
    }

    public void addedToWorld (World w){
        a = (WelcomePage)w;
    }

    public void act() 
    {
        if(!Greenfoot.mouseClicked(this)) checkClicking();
        //checking for clicking
    }    

    public void checkClicking(){

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
    public void initImages(){
        idleImage = new GreenfootImage (image);
        idleImage.scale(375,100);

        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(375*6/5,120);
//zoom in if the mouse is on top of it
        setImage(idleImage);
    }   
}
