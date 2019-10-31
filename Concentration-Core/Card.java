import java.awt.*;

public class Card
{
    private final Color color;
    private boolean isFaceUp;
    private boolean isMatched;
    
    public Card(Color color)
    {
        this.color = color;
        this.isFaceUp = false;
        this.isMatched = false;
    }
    
    public boolean isFaceUp() {
        return this.isFaceUp;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public boolean isMatched() {
        return this.isMatched;
    }
    
    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }
    
    public void flip() {
        this.isFaceUp = !this.isFaceUp;
    }
}
