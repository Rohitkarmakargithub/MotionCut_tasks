import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoblinbaneGame extends JFrame implements ActionListener {
    private JButton startButton;
    private JPanel mainPanel;
    private JLabel instructions;
    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JLabel hpLabel;
    private JLabel damageLabel;
    private Player player;
    private JPanel optionsPanel;
    JPanel bottomPanel;
    JPanel newOptionsPanel;
    JPanel GoblinPanel;
    JButton optionA;
    JButton optionB;
    JButton optionC;
    JButton goblinA;
    JButton goblinB;
    JButton goblinC;
    JButton goblinD;
    JButton goblinE;
    JButton back;
    private static final int TOTAL_GOBINS = 5; 

    public GoblinbaneGame() {
        setTitle("Goblinbane Chronicles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        player = new Player(100, 10);
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        instructions = new JLabel("Choose any one of the following options:");
        instructions.setHorizontalAlignment(JLabel.CENTER);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        option1 = new JButton("Training");
        option1.addActionListener(this);

        option2 = new JButton("Save The Princess");
        option2.addActionListener(this);

        option3 = new JButton("Recover Your Hp");
        option3.addActionListener(this);

        option1.setBounds(100, 30, 200, 40);
        option2.setBounds(100, 80, 200, 40);
        option3.setBounds(100, 130, 200, 40);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(null);
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);

        hpLabel = new JLabel("HP: " + player.getHP());
        hpLabel.setHorizontalAlignment(JLabel.CENTER);
        hpLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        damageLabel = new JLabel("Atk: " + player.getAttackDamage());
        damageLabel.setHorizontalAlignment(JLabel.CENTER);
        damageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        bottomPanel.add(damageLabel);
        bottomPanel.add(hpLabel);

        mainPanel.add(instructions, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(startButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            remove(startButton);
            add(mainPanel);
            revalidate();
        } else if (e.getSource() == option1) {
            player.train();
            damageLabel.setText("Atk: " + player.getAttackDamage());
        } else if (e.getSource() == option2) {
            newOptionsPanel = new JPanel();
            newOptionsPanel.setLayout(null);

            optionA = new JButton("Goblins");
            optionB = new JButton("Princess");
            optionC = new JButton("Go back");

            optionA.setBounds(100, 30, 200, 40);
            optionB.setBounds(100, 80, 200, 40);
            optionC.setBounds(100, 130, 200, 40);

            optionA.addActionListener(this);
            optionB.addActionListener(this);
            optionC.addActionListener(this);

            newOptionsPanel.add(optionA);
            newOptionsPanel.add(optionB);
            newOptionsPanel.add(optionC);

            mainPanel.remove(optionsPanel);
            mainPanel.add(newOptionsPanel, BorderLayout.CENTER);

            revalidate();
        } else if (e.getSource() == option3) {
            player.recover();
            hpLabel.setText("HP: " + player.getHP());
        } else if (e.getSource() == optionA) {
            GoblinPanel = new JPanel();
            GoblinPanel.setLayout(null);

            goblinA = new JButton("goblin1");
            goblinB = new JButton("goblin2");
            goblinC = new JButton("goblin3");
            goblinD = new JButton("goblin4");
            goblinE = new JButton("goblin5");
            back = new JButton("Retreat");

            goblinA.addActionListener(this);
            goblinB.addActionListener(this);
            goblinC.addActionListener(this);
            goblinD.addActionListener(this);
            goblinE.addActionListener(this);
            back.addActionListener(this);

            goblinA.setBounds(100, 30, 200, 20);
            goblinB.setBounds(100, 60, 200, 20);
            goblinC.setBounds(100, 90, 200, 20);
            goblinD.setBounds(100, 120, 200, 20);
            goblinE.setBounds(100, 150, 200, 20);
            back.setBounds(10, 5, 100, 20);

            GoblinPanel.add(goblinA);
            GoblinPanel.add(goblinB);
            GoblinPanel.add(goblinC);
            GoblinPanel.add(goblinD);
            GoblinPanel.add(goblinE);
            GoblinPanel.add(back);
            updateGoblinButtons();
            mainPanel.remove(newOptionsPanel);
            mainPanel.add(GoblinPanel, BorderLayout.CENTER);
            revalidate();
        } else if (e.getSource() == goblinA || e.getSource() == goblinB ||
                e.getSource() == goblinC || e.getSource() == goblinD || e.getSource() == goblinE) {
            int goblinDamage = 30;
            player.takeDamage(goblinDamage);
            System.out.println(player.getHP());
            if (player.getAttackDamage() > 40) {
                if (player.getHP() <= 0) {
                    JOptionPane.showMessageDialog(this, "You were defeated by the goblins!");
                } else {
                    player.defeatGoblin();
                    JButton defeatedButton = (JButton) e.getSource();
                    defeatedButton.setEnabled(false);
                    if (player.getDefeatedGoblins() == TOTAL_GOBINS) {
                        JOptionPane.showMessageDialog(this, "You saved the princess! Congratulations!");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "You defeated a goblin!");
                    }
                    hpLabel.setText("HP: " + player.getHP());

                }
            } else {
                JOptionPane.showMessageDialog(this, "You need to train!!");
            }

        } else if (e.getActionCommand().equals("Princess")) {
            JOptionPane.showMessageDialog(this, "You saved the princess! Congratulations!");

        } else if (e.getSource() == optionC) {
            mainPanel.remove(mainPanel.getComponent(1));
            mainPanel.add(optionsPanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
            revalidate();
        } else if (e.getSource() == back) {
            mainPanel.remove(GoblinPanel); // Remove GoblinPanel
            mainPanel.add(optionsPanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
            revalidate();
        }

    }

    private void updateGoblinButtons() {
        int defeatedGoblins = player.getDefeatedGoblins();
        switch (defeatedGoblins) {
            case 1:
                goblinA.setEnabled(false);
                break;
            case 2:
                goblinA.setEnabled(false);
                goblinB.setEnabled(false);
                break;
            case 3:
                goblinA.setEnabled(false);
                goblinB.setEnabled(false);
                goblinC.setEnabled(false);
                break;
            case 4:
                goblinD.setEnabled(false);
                break;
            case 5:
                goblinE.setEnabled(false);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        GoblinbaneGame game = new GoblinbaneGame();
        game.setVisible(true);
    }
}
