package main.VsualInterface;

import main.PassGen;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Visual extends JFrame {
    private JButton button = new JButton("Generate");
    private JTextField inputKey = new JTextField("", 1);
    private JLabel labelKey = new JLabel("Key: ");
    private JTextField inputSite = new JTextField("", 1);
    private JLabel labelSite = new JLabel("Site: ");

    public Visual() {
        super("PassGen");
        this.setBounds(900, 450, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(labelKey);
        container.add(inputKey);
        container.add(labelSite);
        container.add(inputSite);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    public class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String key = inputKey.getText();
            String site = inputSite.getText();
            String result = "";
            try {
                result = PassGen.executeEncrypting(key, site);
                JOptionPane.showMessageDialog(null, "Пароль скопирован в Буффер обмена", "Output", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception exception) {
                error(exception);
            }
            copy(result);
        }

        public  void error(Exception message) {
            JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        private static void copy(String copiedString) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(copiedString);
            clipboard.setContents(stringSelection, null);
        }
    }
}
