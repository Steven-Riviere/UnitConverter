import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class UnitConverterGUI extends JFrame {
    private JComboBox<String> fromUnitBox, toUnitBox;
    private JTextField inputField, labelField;
    private JPanel historyPanel;
    private boolean isDarkMode = false;
    private JButton darkModeButton;
    private JPanel mainPanel;
    private final String HISTORIQUE_FICHIER = "historique.txt";

    public UnitConverterGUI() {
        setTitle("Convertisseur d'Unités");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Liste des unités
        String[] units = {
                "Kilogrammes", "Livres", "Celsius", "Fahrenheit",
                "Kilomètres", "Miles", "Litres", "Millilitres", "Centilitres",
                "Cuillères à soupe", "Cuillères à café"
        };

        fromUnitBox = new JComboBox<>(units);
        toUnitBox = new JComboBox<>(units);

        // Champ pour l'intitulé
        labelField = new JTextField();
        labelField.setToolTipText("Nommer cette conversion (ex: Lait, Sucre...)");

        // Champ de saisie
        inputField = new JTextField();
        JButton convertButton = new JButton("Convertir");
        JButton clearButton = new JButton("🗑 Effacer tout");
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);

        // Zone d'affichage de l'historique
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(historyPanel);

        // Mode sombre / clair avec icône
        darkModeButton = new JButton("🌙"); // Lune
        darkModeButton.setPreferredSize(new Dimension(40, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(darkModeButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Ajout des éléments au panel principal
        mainPanel.add(new JLabel("Intitulé :"));
        mainPanel.add(labelField);
        mainPanel.add(new JLabel("Valeur :"));
        mainPanel.add(inputField);
        mainPanel.add(new JLabel("De :"));
        mainPanel.add(fromUnitBox);
        mainPanel.add(new JLabel("Vers :"));
        mainPanel.add(toUnitBox);
        mainPanel.add(convertButton);
        mainPanel.add(clearButton);

        // Ajout des composants à la fenêtre
        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Charger l'historique existant
        chargerHistorique();

        // Actions
        convertButton.addActionListener(e -> convertir());
        clearButton.addActionListener(e -> effacerHistorique());
        darkModeButton.addActionListener(e -> toggleDarkMode());

        setVisible(true);
    }

    private void ajouterHistorique(String text) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        JLabel entryLabel = new JLabel(text);
        JButton deleteButton = new JButton("❌");

        deleteButton.addActionListener(e -> {
            historyPanel.remove(entryPanel);
            historyPanel.revalidate();
            historyPanel.repaint();
            supprimerLigneHistorique(text);
            pack();
        });

        entryPanel.add(entryLabel, BorderLayout.CENTER);
        entryPanel.add(deleteButton, BorderLayout.EAST);
        historyPanel.add(entryPanel);

        historyPanel.revalidate();
        historyPanel.repaint();
        pack();
    }

    private void convertir() {
        String label = labelField.getText().trim();
        String fromUnit = (String) fromUnitBox.getSelectedItem();
        String toUnit = (String) toUnitBox.getSelectedItem();
        String inputText = inputField.getText();

        try {
            double value = Double.parseDouble(inputText);
            double result = Converter.convert(value, fromUnit, toUnit);

            String entry = label.isEmpty() ? "" : label + " : ";
            String historiqueText = entry + value + " " + fromUnit + " → " + result + " " + toUnit;

            ajouterHistorique(historiqueText);
            sauvegarderHistorique(historiqueText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chargerHistorique() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORIQUE_FICHIER))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ajouterHistorique(ligne);
            }
        } catch (IOException e) {
            System.out.println("Aucun historique existant.");
        }
    }

    private void sauvegarderHistorique(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORIQUE_FICHIER, true))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void supprimerLigneHistorique(String text) {
        try {
            File fichier = new File(HISTORIQUE_FICHIER);
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fichier));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.equals(text)) {
                    writer.write(ligne);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
            fichier.delete();
            tempFile.renameTo(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void effacerHistorique() {
        historyPanel.removeAll();
        historyPanel.revalidate();
        historyPanel.repaint();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORIQUE_FICHIER))) {
            writer.write(""); // Effacer tout le contenu du fichier
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        Color bgColor = isDarkMode ? Color.DARK_GRAY : Color.WHITE;
        Color fgColor = isDarkMode ? Color.WHITE : Color.BLACK;

        getContentPane().setBackground(bgColor);
        mainPanel.setBackground(bgColor);
        historyPanel.setBackground(bgColor);
        labelField.setBackground(Color.WHITE);
        labelField.setForeground(Color.BLACK);
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);

        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(fgColor);
            }
        }

        darkModeButton.setText(isDarkMode ? "☀️" : "🌙");
    }

    public static void main(String[] args) {
        new UnitConverterGUI();
    }
}
