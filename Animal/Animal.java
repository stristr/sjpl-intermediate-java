
/**
 * This creates an animal object that can move and eat.
 * 
 * @author Xinru Lin
 * @version 11/29/2017
 */
public class Animal
{
    private int energy;
    /**
     * this constructs an animal object and set its energy at 0
     */
    public Animal()
    {
        energy = 0;
    }

    /**
     * this increases the energy of animal
     * @param amount is that amount of energy gained
     */
    public void eat(int amount)
    {
        if(amount > 0)
        {
            energy = energy + amount;
        }
    }

    /**
     * this decreases the energy of animal since it moves
     * it only decrease if there is enough energy for the given smountof movement
     * @param amount is how much you need to move
     */
    public void move(int amount)
    {
        if (amount > 0 && energy > amount)
        {
            energy = energy - amount;
        }
        if(amount > energy)
        {
            energy = 0;
        }
    }

    /**
     * this gives you the energy amount
     * @return give the energy of the animal object
     */
    public int getEnergy()
    {
        return energy;
    }
}
