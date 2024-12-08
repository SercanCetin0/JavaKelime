package Guiler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout; // For horizontally aligned components
import VeriTabani.Word;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Kelimeİslemleri extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idTextField;
    private JTextField turkishTextField;
    private JTextField englishTextField;
    private JTextField wordImageTextField;
    private JLabel imageLabel; // Label to display the image

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kelimeİslemleri frame = new Kelimeİslemleri();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Kelimeİslemleri() {
        // Set up frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600); // Adjusted frame size for better visibility
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Absolute layout for the table

        // Sample list of words (this should come from the Select() method)
        Word wordListe = new Word(); // Replace with your actual Select() call
        List<Word> wordList = wordListe.Select();
        
        // Check if the wordList is not null
        if (wordList != null) {
            // Table column names
            String[] columnNames = {"ID", "Turkish", "English", "Word Image"};

            // Create a DefaultTableModel and add rows to it
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            for (Word word : wordList) {
                Object[] row = {word.getId(), word.getTr(), word.getEn(), word.getWordImage()};
                model.addRow(row);
            }

            // Create JTable with the table model
            JTable table = new JTable(model);

            // Add a JScrollPane around the JTable
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 10, 560, 200); // Set scroll pane position and size
            contentPane.add(scrollPane);

            // Panel to hold text fields horizontally
            JPanel textFieldPanel = new JPanel();
            textFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Horizontally aligned with 10px gap
            textFieldPanel.setBounds(10, 220, 560, 60); // Positioning the text fields panel
            contentPane.add(textFieldPanel);

            // Create text fields to display selected row data
            idTextField = new JTextField();
            idTextField.setColumns(10);
            idTextField.setEnabled(false);
            textFieldPanel.add(idTextField);

            turkishTextField = new JTextField();
            turkishTextField.setColumns(10);
            textFieldPanel.add(turkishTextField);

            englishTextField = new JTextField();
            englishTextField.setColumns(10);
            textFieldPanel.add(englishTextField);

            wordImageTextField = new JTextField();
            wordImageTextField.setColumns(10);
            textFieldPanel.add(wordImageTextField);

            // Create a JLabel to display the image
            imageLabel = new JLabel();
            imageLabel.setBounds(10, 290, 200, 200); // Set the position and size of the image label
            contentPane.add(imageLabel);

            // Add a ListSelectionListener to update text fields and image when a row is selected
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            // Get data from the selected row and set to text fields
                            idTextField.setText(table.getValueAt(selectedRow, 0).toString());
                            turkishTextField.setText(table.getValueAt(selectedRow, 1).toString());
                            englishTextField.setText(table.getValueAt(selectedRow, 2).toString());
                            wordImageTextField.setText(table.getValueAt(selectedRow, 3).toString());

                            // Load the image based on the file path stored in "Word Image" column
                            String imagePath = (String) table.getValueAt(selectedRow, 3);
                            ImageIcon imageIcon = new ImageIcon("image/" + imagePath); // Assuming images are in the "image" folder
                            Image img = imageIcon.getImage(); // Transform it
                            Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Resize the image
                            imageIcon = new ImageIcon(newImg); // Set the new image
                            imageLabel.setIcon(imageIcon); // Set the image icon to the label
                        }
                    }
                }
            });

            // "Güncelleme" (Update) Button to save changes
            JButton updateButton = new JButton("Güncelleme");
            updateButton.setBounds(400, 300, 120, 30);
            contentPane.add(updateButton);

            // Update Button ActionListener
            updateButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the new data from text fields
                    String id = idTextField.getText();
                    String turkish = turkishTextField.getText();
                    String english = englishTextField.getText();
                    String wordImage = wordImageTextField.getText();

                    
                    table.setValueAt(id, selectedRow, 0);
                    table.setValueAt(turkish, selectedRow, 1);
                    table.setValueAt(english, selectedRow, 2);
                    table.setValueAt(wordImage, selectedRow, 3);

                    JOptionPane.showMessageDialog(this, "Kelime başarıyla güncellendi!");

                    Word updatedWord = wordList.get(selectedRow);
                    updatedWord.setId(Integer.parseInt(id));
                    updatedWord.setTr(turkish);
                    updatedWord.setEn(english);
                    updatedWord.setWordImage(wordImage);

                    // Update the word in the database (this would depend on your database logic)
                    wordListe.Update(updatedWord);
                } else {
                    JOptionPane.showMessageDialog(this, "Lütfen bir kelime seçin.");
                }
            });
            
            JButton btn_kelimeB = new JButton("EKle");
            btn_kelimeB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    KelimeEkle kb = new KelimeEkle();
                    kb.setVisible(true);
                }
            });
            btn_kelimeB.setBounds(400, 380, 120, 30);
            contentPane.add(btn_kelimeB);
            
            
            JButton deleteButton = new JButton("Sil");
            deleteButton.setBounds(400, 340, 120, 30); // Positioning the button
            contentPane.add(deleteButton);

            // Delete Button ActionListener
            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the ID of the selected word
                    int id = (int) table.getValueAt(selectedRow, 0);

                    // Call the Delete(id) method
                    Word wordToDelete = wordList.get(selectedRow); // Get the word object from the list
                    wordListe.Delete(wordToDelete.getId()); // Call the Delete method (assuming it's implemented in your Word class)

                    // Show a confirmation message
                    JOptionPane.showMessageDialog(this, "Kelime başarıyla silindi!");

                    // Re-fetch the updated list of words
                    List<Word> updatedWordList = wordListe.Select();

                    // If the updated word list is not null
                    if (updatedWordList != null) {
                        // Clear the current table model
                        model.setRowCount(0);

                        // Add updated rows to the table model
                        for (Word word : updatedWordList) {
                            Object[] row = {word.getId(), word.getTr(), word.getEn(), word.getWordImage()};
                            model.addRow(row);
                        }
                    } else {
                        System.out.println("No words found in the database.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lütfen silmek için bir kelime seçin.");
                }
            });
        } else {
            System.out.println("No words found in the database.");
        }
        
    }
}
