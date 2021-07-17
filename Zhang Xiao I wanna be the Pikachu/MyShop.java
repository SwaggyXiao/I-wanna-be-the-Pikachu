import greenfoot.*;
public class MyShop extends World
{

    private MyWorld m;
    private Return returnButtom;
    private GengarBall gengar;
    private PikachuUpgrade pikachuUpgrade;

    private AddBlood addBlood;
    private Cashier cashier;
    boolean select = false;
    private DoubleJump dj;

    private GreenfootSound click;
    int[] productBought={0,0,0};
    public MyShop(MyWorld m)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        this.m = m;
        setPaintOrder(YES.class,NO.class,Cashier.class,GengarBall.class,AddBlood.class,PikachuUpgrade.class);
        click=new GreenfootSound ("buttom.mp3");
        returnButtom=new Return();
        addObject(returnButtom,1120,87);

        gengar=new GengarBall();
        addObject(gengar,187,100);
        pikachuUpgrade=new PikachuUpgrade();
        addObject(pikachuUpgrade,187,300);
        addBlood=new AddBlood();
        addObject(addBlood,187,500);
        dj=new DoubleJump();
        addObject(dj,187,700);
    }

    public void act (){
        clicking();
    }

    private void clicking(){
        if (Greenfoot.mouseClicked(returnButtom)){
            click.play();
            Greenfoot.setWorld(m);
            //go back to the game world
        }else if (Greenfoot.mouseClicked(gengar)){
            click.play();
            addChattingBox(gengar.toString(),gengar.price());
            //add the chatting specific box for the produce
        }else if (Greenfoot.mouseClicked(pikachuUpgrade)){
            click.play();
            addChattingBox(pikachuUpgrade.toString(),pikachuUpgrade.price());
            //add the chatting specific box for the produce
        }else if (Greenfoot.mouseClicked(addBlood)){
            click.play();
            addChattingBox(addBlood.toString(),addBlood.price());
            //add the chatting specific box for the produce
        }else if (Greenfoot.mouseClicked(dj)){
            click.play();
            addChattingBox(dj.toString(),dj.price());
            //add the chatting specific box for the produce
        }
    } 

    private void addChattingBox(String str,int price){
        cashier=new Cashier(str,price,m.pikachu);
        addObject(cashier,600,400);
    }    

}
