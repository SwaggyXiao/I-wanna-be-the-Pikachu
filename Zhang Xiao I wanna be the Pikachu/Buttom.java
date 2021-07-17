import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Buttom extends Actor
{
    private MouseInfo m;
    private GreenfootImage idleImage;
    private GreenfootImage mouseOverImage;
    //MyWorld a;
    boolean stop;
    private boolean mouseOver;
    private boolean clicked;
    String image;
    public Buttom (String image){
        mouseOver = false;
        //see if mouse is on top of the buttom
        clicked = false;//see if it is been clicked
        this.image=image;//get which image it is
        initImages();//set the image
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
            //assign the boolean mouseOver
            if (mouseOver){
                this.setImage(mouseOverImage);
                //the larger image when the mouse is on it
            } else {
                this.setImage(idleImage);
                //original small image if the mouse is not on it
            }

        }

    }    
    private void initImages(){
        if(image!="ReGame.png"){
        idleImage = new GreenfootImage (image);
        idleImage.scale(100,100);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(120,120);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }   
    else{
        idleImage = new GreenfootImage (image);
        idleImage.scale(300,135);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(360,162);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
        
    }    
}
    
}  