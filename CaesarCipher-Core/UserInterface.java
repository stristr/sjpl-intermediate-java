import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserInterface extends JFrame implements ActionListener {
    private SpinnerModel offsetModel = new SpinnerNumberModel(0, 0, 25, 1);
    private JSpinner offset = new JSpinner(offsetModel);
    private JTextArea input = new JTextArea();
    private JButton submit = new JButton();
    private JTextArea output = new JTextArea();


    UserInterface() {
        super("Caesar Cipher");
        setSize(800, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(2, 1);
        setLayout(grid);
        buildDisplay();
        setFocusable(true);
        setVisible(true);
    }

    private void buildDisplay() {
        Font font = new Font("Arial", Font.BOLD, 28);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        Insets margin = new Insets(10, 10, 10, 10);

        input.setPreferredSize(new Dimension(400, 180));
        input.setFont(font);
        input.setMargin(margin);
        input.setLineWrap(true);
        offset.setPreferredSize(new Dimension(100, 40));
        submit.setPreferredSize(new Dimension(100, 40));
        submit.setFont(font);
        submit.setText("Go");
        submit.addActionListener(this);
        output.setPreferredSize(new Dimension(600, 180));
        output.setFont(font);
        output.setMargin(margin);
        input.setLineWrap(true);
        output.setEditable(false);

        JPanel topRow = new JPanel();
        topRow.setLayout(layout);
        topRow.add(input);
        topRow.add(offset);
        topRow.add(submit);
        add(topRow);

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(layout);
        bottomRow.add(output);
        add(bottomRow);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object source = ev.getSource();
        if (source == submit) {
            char[] message = input.getText().toUpperCase().toCharArray();
            char[] result = Decrypter.decrypt(message, (int) offset.getValue());
            output.setText(String.copyValueOf(result));
        }
    }
}
