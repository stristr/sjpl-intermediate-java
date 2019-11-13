
/**
 * This creates an animal with s set max amount of energy. Animal object can move and
 * eat.
 *
 * @author Xinru Lin
 * @version 11/29/2017
 */
public class ImprovedAnimal extends Animal
{
    private int max;
    /**
     * this creates an animal object
     * @param maxEnergy is the the maximum energy animal can have
     */
    public ImprovedAnimal(int maxEnergy)
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
}
