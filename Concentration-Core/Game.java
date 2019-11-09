import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends javax.swing.JFrame {
    public static ArrayList<JButton> buttons;
    public Model model;

    public Game(Model model) {
        this.model = model;
        initialize();
    }

    private void initialize() {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton reset;
        reset = new JButton("Reset");
        buttons = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            buttons.add(new JButton());
            buttons.get(i).setPreferredSize(new Dimension(100, 100));
            buttons.get(i).setName(String.valueOf(i));
            buttons.get(i).setBackground(Color.WHITE);
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setOpaque(true);
        }

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Concentration Game");
        JPanel panel = new JPanel();
        for (JButton but : buttons) {
            panel.add(but);
        }
        bottomPanel.add(reset, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //reset Button will reset the screen to default
        reset.addActionListener(e -> {
            model.reset();
            for (JButton btn : buttons) {
                btn.setSelected(false);
                btn.setBackground(Color.WHITE);
            }
        });

        for (JButton btn : buttons) {

            btn.addActionListener(e -> {
                model.selectCard(buttons.indexOf(btn));
                btn.setBackground(model.getCards().get(buttons.indexOf(btn)).getColor());
                for (int i = 0; i < 16; i++) {
                    Card card = model.getCards().get(i);
                    if (!card.isFaceUp()) {
                        buttons.get(i).setBackground(Color.WHITE);
                    }
                }
            });
        }
    }

}
