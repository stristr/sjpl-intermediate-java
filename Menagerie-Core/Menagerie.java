import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Menagerie extends JFrame {
    ArrayList<JLabel> labels = new ArrayList<>();

    Menagerie() {
        super("Menagerie");
        setSize(800, 600);
        GridLayout grid = new GridLayout(2, 2);
        setLayout(grid);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void addAnimal(Animal animal) {
        // Prevent overflowing our boundaries.
        if (labels.size() == 4) {
            JLabel oldLabel = labels.get(0);
            remove(oldLabel);
            labels.remove(0);
        }

        JLabel label = new JLabel();
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        FlowLayout flow = new FlowLayout();
        label.setLayout(new BorderLayout());
        label.setText(String.format("%s! My name is %s.", animal.getSound(), animal.getName()));
        label.setIcon(new ImageIcon(new ImageIcon(animal.getPicture()).getImage().getScaledInstance(300, -1, Image.SCALE_DEFAULT).getScaledInstance(-1, 200, Image.SCALE_DEFAULT)));

        add(label);
        labels.add(label);
        validate();
    }
}
