import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class UserInterface extends JFrame implements ActionListener, KeyListener {
    private JTextPane display = new JTextPane();
    private HashMap<JButton, CalculatorButton> buttons = new HashMap<>();
    private HashMap<Character, CalculatorButton> characters = new HashMap<>();
    private ExpressionEvaluator evaluator = new ExpressionEvaluator();

    UserInterface() {
        super("Calculator");
        setSize(760, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5, 5);
        setLayout(grid);
        buildDisplay();
        setFocusable(true);
        addKeyListener(this);
        setVisible(true);
    }

    private void buildDisplay() {
        Font font = new Font("Arial", Font.BOLD, 28);
        Dimension buttonDimension = new Dimension(90, 80);
        Dimension zeroButtonDimension = new Dimension(180, 80);

        JPanel activeRow = new JPanel();
        activeRow.setLayout(new FlowLayout(FlowLayout.CENTER));
        activeRow.add(display);
        display.setFont(font);
        display.setEditable(false);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setSpaceAbove(attributes, 16);
        display.setParagraphAttributes(attributes, false);
        display.setPreferredSize(new Dimension(600, 70));
        display.addKeyListener(this);
        add(activeRow);

        for (CalculatorButton button : CalculatorButton.values()) {
            if (button.ordinal() % 5 == 0) {
                activeRow = new JPanel();
                activeRow.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
                add(activeRow);
            }
            JButton jButton = new JButton();
            jButton.setFont(font);
            jButton.setText(Character.toString(button.symbol));
            jButton.addActionListener(this);
            jButton.addKeyListener(this);
            if (button == CalculatorButton.ZERO) {
                jButton.setPreferredSize(zeroButtonDimension);
            } else {
                jButton.setPreferredSize(buttonDimension);
            }
            activeRow.add(jButton);
            buttons.put(jButton, button);
            characters.put(button.symbol, button);
        }
    }

    private void handleButton(CalculatorButton button) {
        evaluator.handleButton(button);
        display.setText(evaluator.getDisplay());
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object source = ev.getSource();
        if (buttons.containsKey(source)) {
            handleButton(buttons.get(source));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (characters.containsKey(keyChar)) {
            handleButton(characters.get(keyChar));
            return;
        }

        // Try char shortcuts.
        switch (keyChar) {
            case 'c':
                handleButton(CalculatorButton.CLEAR);
                return;
            case '*':
            case 'x':
                handleButton(CalculatorButton.TIMES);
                return;
            case '/':
                handleButton(CalculatorButton.DIVIDED_BY);
                return;
        }

        int keyCode = e.getExtendedKeyCode();

        if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_ACCEPT) {
            handleButton(CalculatorButton.EQUALS);
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            handleButton(CalculatorButton.CLEAR);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // See keyTyped() handler.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // See keyTyped() handler.
    }
}
