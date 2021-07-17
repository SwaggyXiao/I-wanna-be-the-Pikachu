import greenfoot.*;
public class BlackScreen extends Actor
{
    int Transparency;
    public BlackScreen(int Transparency){
        this.Transparency=Transparency;
        this.getImage().setTransparency(Transparency);
        //get the parameter about Transparency
    }    
    //set the transparency
}
