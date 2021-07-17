import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cashier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cashier extends Actor
{

    private GreenfootSound bought;
    //sound for buying stuff
    private GreenfootSound click;
    //sound for clicking
    //the sound for clicking buttoms
    private GreenfootImage psyduck;
    //the base or the background
    private String buy;
    //the string that will show on the screen
    private Font f=new Font("Comic Sans MS",true,false,30);
    private Color c=Color.MAGENTA;
    MyShop a;
    
    private YES yes;
    private NO no;
    //the YES NO buttom
    int price;//the price of the product
    Pikachu pikachu;//the main charactor pikachu
    boolean checkingRebought=false;
    //the boolean that will check if the customer buy same thing twice
    int[] productBought;
    //the array that will keep what the customer bought already
    public Cashier(String Buy,int price,Actor a){
        this.price=price;
        pikachu=(Pikachu)a;
        bought=new GreenfootSound ("BOUGHT.mp3");
        click=new GreenfootSound ("buttom.mp3");
        buy="Are you sure you want to buy "+Buy+"?"+"\nIt will cost you "+price+" dollars.";
        if(Buy.equals("\"Gengar\"")) buy=buy+" \n  Click \"B\" to call it.";
        psyduck = new GreenfootImage("Cashier.png");
        psyduck.scale(1200,800);
        psyduck.setFont(f);
        psyduck.setColor(c);
        psyduck.drawString(buy,280, 220);
        //psyduck.drawImage(new GreenfootImage(buy, 40, Color.MAGENTA, null), 280, 200);
        this.setImage(psyduck);
    }

    public void addedToWorld (World w){
        a = (MyShop)w;
        yes=new YES();
        a.addObject(yes,467,380);
        //add the yes buttom
        no=new NO();
        a.addObject(no,758,380);
        //add the no buttom
        productBought=a.productBought;
    }

    public void act() 
    {
        if(Greenfoot.mouseClicked(yes)){
            click.play();
            //play the click music
            a.removeObject(yes);
            clickYes();
        }   //yes operation
        else if(Greenfoot.mouseClicked(no)){
            click.play();
            //play the click music
            a.removeObject(no);
            if(yes!=null)a.removeObject(yes);
            a.removeObject(this);
            //remove everything on the chatting box
        }   //no operation
    }    

    public void clickYes(){
        if(price<=pikachu.pikachuPiont){
            checkingRebought(price);
            psyduck = new GreenfootImage("Cashier.png");
            psyduck.scale(1200,800);
            psyduck.setFont(f);
            psyduck.setColor(c);
            psyduck.drawString(buy,280, 220);
            this.setImage(psyduck);
            //redraw the image
        }    
        else{
            buy="You can not afford it!";
            psyduck = new GreenfootImage("Cashier.png");
            psyduck.scale(1200,800);
            psyduck.setFont(f);
            psyduck.setColor(c);
            psyduck.drawString(buy,280, 220);
            this.setImage(psyduck);
            //draw the image
        }    
    }    

    public void differentProduct(){
        if(price==20) {
            pikachu.gengarSwith=true;
            pikachu.a.addGengarClickB();
        }
        //make pikachu can call the gengar
        //add the icon of adding gengar by clicking B
        else if(price==4) pikachu.limitingJumpTime=2;
        //make pikahcu's limit increase by 1
        else if(price==8) pikachu.ThunderKill=200;
        //pikachu now have double demage
    }

    private void checkingRebought(int price){
        if(price!=7){
            //if the price is 7, which is adding blood, people can buy it mutiple times
            for(int i=0;i<productBought.length;i++){

                if(price==productBought[i]){
                    checkingRebought=true;
                }   
            }    
            //check from the array to see if it's already being bought
            if(!checkingRebought){
                //if it's not bought yet
                differentProduct();
                //make the change
                pikachu.pikachuPiont-=price;
                //take pikachu's money 
                pikachu.sp.updatePoint(pikachu.pikachuPiont);
                //update pikachu's point
                for(int i=0;i<productBought.length;i++){
                    if(productBought[i]==0){
                        productBought[i]=price;
                        break;
                    }   
                }   
                //add this price to the bought list
                buy="Thank you for your shopping.";
                checkingRebought=false;
                bought.play();
                //play the music effect
            }    
            else{
                buy="You already bought it.";
                //print they already bought it
            }    
        }
        else{
            if(pikachu.pikachuBlood<5){
                pikachu.pikachuPiont-=price;
                pikachu.sp.updatePoint(pikachu.pikachuPiont);
                pikachu.pikachuBlood++;
                pikachu.h.updateBlood(pikachu.pikachuBlood);
                buy="Thank you for your shopping.";
                //add the blood up
            }    
            else{
                buy="Your blood is full.";
            }    //nothing will happen if they have full blood

        }    

    }    
}
