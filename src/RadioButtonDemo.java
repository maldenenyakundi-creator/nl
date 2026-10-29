import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class RadioButtonDemo extends JPanel implements ActionListener {

    private static final String[] PETS = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
    private final JLabel picture = new JLabel("", SwingConstants.CENTER);

    public RadioButtonDemo() {
        super(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel radioPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        ButtonGroup group = new ButtonGroup();

        for (String pet : PETS) {
            JRadioButton rb = new JRadioButton(pet);
            rb.setActionCommand(pet);
            rb.addActionListener(this);
            group.add(rb);
            radioPanel.add(rb);
            if (pet.equals("Pig")) {          // Pig selected by default
                rb.setSelected(true);
            }
        }

        picture.setPreferredSize(new Dimension(200, 180));
        showPet("Pig");

        add(radioPanel, BorderLayout.LINE_START);
        add(picture, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pet = e.getActionCommand();
        showPet(pet);
        JOptionPane.showMessageDialog(this,
                "You selected: " + pet,
                "Your Selection",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Loads images/<Pet>.gif (or .jpg/.png); falls back to text if no image exists
    private void showPet(String pet) {
        String[] exts = {".gif", ".jpg", ".png"};
        for (String ext : exts) {
            File f = new File("images" + File.separator + pet + ext);
            if (f.exists()) {
                picture.setIcon(new ImageIcon(f.getPath()));
                picture.setText("");
                return;
            }
        }
        picture.setIcon(null);
        picture.setFont(new Font("SansSerif", Font.BOLD, 36));
        picture.setText(pet);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("RadioButtonDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new RadioButtonDemo());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
