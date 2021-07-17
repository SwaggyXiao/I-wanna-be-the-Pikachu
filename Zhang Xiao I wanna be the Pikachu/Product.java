import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Product here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Product extends Actor
{
    private MouseInfo m;
    private GreenfootImage idleImage;
    private GreenfootImage mouseOverImage;
    //declare the image
    MyShop a;
    boolean stop;
    private boolean mouseOver;
    private boolean clicked;
    String image;
    public Product (String image){
        mouseOver = false;
        //at first mouse is not over it
        clicked = false;
        //at first it's not clicked
        this.image=image;
        initImages();
        //set the initial images
    }

    public void addedToWorld (World w){
        a = (MyShop)w;
    }
    //get the world that the buttom is in

    public void act() 
    {
        checkClicking();
    }    //checking for clicking

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
        idleImage.scale(150,150);
        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(175,175);
        //the image will zone in if mouse is over it
        setImage(idleImage);
    }   
    
}
