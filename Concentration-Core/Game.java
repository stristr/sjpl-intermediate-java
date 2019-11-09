import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Game extends javax.swing.JFrame {
    private static ArrayList<JButton> buttons;
    private Model model;
    private JTextField victory = new JTextField("              ");

    Game(Model model) {
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
            buttons.get(i).setBorderPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setOpaque(true);
        }

        redraw();

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Concentration Game");
        JPanel panel = new JPanel();
        for (JButton but : buttons) {
            panel.add(but);
        }
        bottomPanel.add(reset, BorderLayout.EAST);
        victory.setOpaque(true);
        victory.setEditable(false);
        victory.setBorder(null);
        victory.setBackground(null);
        bottomPanel.add(victory);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Reset button will reset the screen to default.
        reset.addActionListener(e -> {
            model.reset();
            victory.setText("");
            for (JButton btn : buttons) {
                btn.setSelected(false);
                btn.setBackground(Color.WHITE);
            }
        });

        for (JButton btn : buttons) {
            btn.addActionListener(e -> {
                model.selectCard(buttons.indexOf(btn));
                redraw();
            });
        }
    }

    void redraw() {
        int matches = 0;
        for (int i = 0; i < buttons.size(); i++) {
            Card card = model.cards.get(i);
            JButton btn = buttons.get(i);
            if (card.isFaceUp) {
                btn.setBackground(card.color);
                matches++;
            } else {
                btn.setBackground(Color.WHITE);
            }
        }
        if (matches == 16) {
            victory.setText("You win!");
        }
    }
}
