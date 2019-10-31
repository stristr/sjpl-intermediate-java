import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.*;

public class GViewControl extends javax.swing.JFrame  
{
    public ConcentrationModel concentration;
    public static GViewControl gViewControl;
    
    public static ArrayList<JButton> buttons;
    public GViewControl(ConcentrationModel model)
    {
        this.concentration = model;
    }
    public static void main(String[] args) {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        gViewControl = new GViewControl(new ConcentrationModel());
        JButton reset;
        reset = new JButton("Reset");
        buttons = new ArrayList<JButton>();
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
        for(JButton but : buttons){
            panel.add(but);
        }
        bottomPanel.add(reset,BorderLayout.EAST);
        frame.add(topPanel,BorderLayout.NORTH);
        frame.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //reset Button will reset the screen to default
        reset.addActionListener(e-> {
            gViewControl.concentration.reset();
            for (JButton btn: buttons) {
                btn.setSelected(false);
                btn.setBackground(Color.WHITE);
            }
        });
        
        for(JButton btn : buttons){

            btn.addActionListener(e->{
                gViewControl.concentration.selectCard(buttons.indexOf(btn));
                btn.setBackground(gViewControl.concentration.getCards().get(buttons.indexOf(btn)).getColor());
                for (int i = 0; i < 16; i++) {
                    Card card = gViewControl.concentration.getCards().get(i);
                    if (!card.isFaceUp()) {
                        buttons.get(i).setBackground(Color.WHITE);
                    }
                }
            });
        }
    }

    }
