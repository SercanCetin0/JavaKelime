package Guiler;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import VeriTabani.Categories;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kategoriİslemi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField categoryNameField;
    private JTable categoryTable;
    private int selectedCategoryId = -1;  // Store the selected category's ID

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kategoriİslemi frame = new Kategoriİslemi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Kategoriİslemi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Panel for input and buttons
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        
        JLabel lblCategoryName = new JLabel("Kategori Adı:");
        panel.add(lblCategoryName);

        categoryNameField = new JTextField();
        panel.add(categoryNameField);
        categoryNameField.setColumns(15);

        JButton btnAdd = new JButton("Ekle");
        panel.add(btnAdd);
        btnAdd.addActionListener(e -> addCategory());

        JButton btnUpdate = new JButton("Güncelle ");
        panel.add(btnUpdate);
        btnUpdate.addActionListener(e -> updateCategory());

        JButton btnDelete = new JButton("Sil ");
        panel.add(btnDelete);
        btnDelete.addActionListener(e -> deleteCategory());

        // Category Table
        categoryTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(categoryTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Display categories in the table
        displayCategories();

        // Add MouseListener to table
        categoryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = categoryTable.getSelectedRow();
                if (row != -1) {
                    selectedCategoryId = Integer.parseInt(categoryTable.getValueAt(row, 0).toString());
                    String categoryName = categoryTable.getValueAt(row, 1).toString();
                    categoryNameField.setText(categoryName);  // Set category name to the text field
                }
            }
        });

        setVisible(true);
    }

    // Method to display categories in the table
    private void displayCategories() {
        List<Categories> categories = new Categories().Select();
        String[][] data = new String[categories.size()][2];
        for (int i = 0; i < categories.size(); i++) {
            Categories category = categories.get(i);
            data[i][0] = String.valueOf(category.getId());
            data[i][1] = category.getCateName();
        }

        String[] columnNames = {"ID", "Category Name"};
        categoryTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // Method to add a new category
    private void addCategory() {
        String categoryName = categoryNameField.getText();
        if (!categoryName.isEmpty()) {
        	Categories ct = new Categories();
        	ct.setCateName(categoryName);
            boolean success = new Categories().Create(ct);
            if (success) {
                JOptionPane.showMessageDialog(this, " Başarılı");
                displayCategories();
            } else {
                JOptionPane.showMessageDialog(this, " Başarısız.");
            }
        }
    }

    // Method to update an existing category
    private void updateCategory() {
        if (selectedCategoryId != -1) {
            String newCategoryName = categoryNameField.getText();
            if (!newCategoryName.isEmpty()) {
            	Categories ct = new Categories();
            	ct.setCateName(newCategoryName);
            	ct.setId(selectedCategoryId);
                boolean success = new Categories().Update(ct);
                if (success) {
                    JOptionPane.showMessageDialog(this, " Başarılı");
                    displayCategories();
                } else {
                    JOptionPane.showMessageDialog(this, " Başarısız");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Kategori Seç");
        }
    }

    // Method to delete a category
    private void deleteCategory() {
        if (selectedCategoryId != -1) {
            boolean success = new Categories().Delete(selectedCategoryId);
            if (success) {
                JOptionPane.showMessageDialog(this, " Başarılı");
                displayCategories();
            } else {
                JOptionPane.showMessageDialog(this, " Başarısız");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Kategori Seç");
        }
    }
}
