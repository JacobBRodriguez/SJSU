import java.util.ArrayList;
import java.util.List;


public abstract class GumballMachine {

    protected int num_gumballs;
    protected boolean has_amount;
    protected int threshold_amount;
    protected List coins;

    public GumballMachine(int amount, int size) {
        // initialise instance variables
        this.threshold_amount = amount;
        this.num_gumballs = size;
        this.has_amount = false;
        coins = new ArrayList<Integer>();
    }

    public abstract void insert(int coin);

    public void turnCrank()
    {
        if ( this.has_amount )
        {
            if ( this.num_gumballs > 0 )
            {
                this.num_gumballs-- ;
                this.has_amount = false ;
                this.coins.clear();
                System.out.println( "Thanks for your money.  Gumball Ejected!" ) ;
            }
            else
            {
                System.out.println( "No More Gumballs!  Sorry, can't return your change." ) ;
            }
        }
        else
        {
            System.out.println( "Please insert the correct amount" ) ;
        }
    } // end turnCrank()

    public boolean checkAmount() {
        int running_total = 0;
        for(Object coin : coins)
        {
            running_total += (int)coin;
        }
        if(running_total == this.threshold_amount)
            return true;
        else
            return false;
    } // end checkAmount()

} // end GumballMachine


class Gumball25CentMachine extends GumballMachine
{
    public Gumball25CentMachine( int size )
    {
        super(25, size);
    }

    public void insert(int coin)
    {
        if ( coin ==  this.threshold_amount)
            this.has_amount = true ;
        else
            this.has_amount = false ;
    }

} // end Gumball25CentMachine

class Gumball50CentMachine extends GumballMachine
{

    public Gumball50CentMachine( int size )
    {
        super(50, size);
    }

    public void insert(int coin)
    {
        if (coins.size() > 0 && (int)coins.get(0) == 25 && coin == 25) {
            this.coins.add(coin);
            this.has_amount = this.checkAmount();
        }
        else if(coin == 25)
            this.coins.add(coin);
        else
            this.has_amount = false ;
    }

} // end Gumball50CentMachine

class Gumball50CentAllCoinMachine extends GumballMachine
{

    public Gumball50CentAllCoinMachine( int size )
    {
        super(50, size);
    }

    public void insert(int coin)
    {
        if ( coin >= 5) // cannot accept pennies
        {
            this.coins.add(coin);
            this.has_amount = this.checkAmount();
        }
        else
            this.has_amount = false ;
    }

} // end Gumball50CentAllCoinMachine

