import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Return here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Return extends Buttom
{
    MyShop a;
   public Return(){
        super("return.png");
    }
    public void addedToWorld (World w){
        a = (MyShop)w;
    }

    public void act() 
    {
        
        if(!Greenfoot.mouseClicked(this)) super.checkClicking();
        else getWorld().removeObject(this);
    } 
}
