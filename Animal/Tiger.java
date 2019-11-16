import java.util.Random;
/**
 * This creates an animal with s set max amount of energy. Animal object can move and
 * eat.
 *
 * @author Xinru Lin
 * @version 11/29/2017
 */


public class Tiger extends Animal
{
    private int max;
    /**
     * this creates an animal object
     * @param maxEnergy is the the maximum energy animal can have
     */
    public Tiger(int maxEnergy)
    {
        super();
        max = maxEnergy;
    }

    /**
     * this increases the energy of animal
     * @param amount is that amount of energy gained
     */
    public void eat(int amount)
    {
        if( this.getEnergy() < max && amount < max)
        {
            super.eat(amount);
        }
        if(amount >  max)
        {
            super.eat(max - this.getEnergy());
        }
    }
    
       /**
     * this increases the energy of animal
     * @param amount is that amount of energy gained
     */
    public void speak(int length){
       String[] words  = {"rawr!!!", "Give me honey!!", "I want fish"};
       int i = 0;
       Random rand = new Random();
       int randomNumber = rand.nextInt(3);
       while(i < length){
           System.out.println(words[randomNumber]);
        }
    }
}
