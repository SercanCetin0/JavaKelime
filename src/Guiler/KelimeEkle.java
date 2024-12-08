package Guiler;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import VeriTabani.Categories;
import VeriTabani.Word;

public class KelimeEkle extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTr, txtEn, txtImage;
    private JButton btnSubmit, btnChooseImage;
    private File selectedFile;  // Define selectedFile here to be accessible throughout the class

    // Categories list
    private List<Categories> categoriesList;

    // ComboBox for Category
    private JComboBox<String> comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KelimeEkle frame = new KelimeEkle();
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
    public KelimeEkle() {
        // Initialize categories list
        categoriesList = fetchCategoriesFromDatabase();  // Fetch categories from database or any other source

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 486, 312);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Türkçe Kelime Label ve TextField
        JLabel lblTr = new JLabel("Türkçe Kelime:");
        lblTr.setBounds(20, 30, 120, 20);
        contentPane.add(lblTr);

        txtTr = new JTextField();
        txtTr.setBounds(150, 30, 200, 20);
        contentPane.add(txtTr);

        // İngilizce Kelime Label ve TextField
        JLabel lblEn = new JLabel("İngilizce Kelime:");
        lblEn.setBounds(20, 70, 120, 20);
        contentPane.add(lblEn);

        txtEn = new JTextField();
        txtEn.setBounds(150, 70, 200, 20);
        contentPane.add(txtEn);

        // Kelime Resmi Label ve TextField
        JLabel lblImage = new JLabel("Kelime Resmi:");
        lblImage.setBounds(20, 110, 120, 20);
        contentPane.add(lblImage);

        txtImage = new JTextField();
        txtImage.setBounds(150, 110, 200, 20);
        txtImage.setEnabled(false);
        contentPane.add(txtImage);

        // Resim Seçme Butonu
        btnChooseImage = new JButton("Resim Seç");
        btnChooseImage.setBounds(360, 105, 100, 30);
        contentPane.add(btnChooseImage);

        // Ekleme Butonu
        btnSubmit = new JButton("Ekle");
        btnSubmit.setBounds(250, 201, 100, 30);
        contentPane.add(btnSubmit);

        // ComboBox for Categories
        comboBox = new JComboBox<>();
        comboBox.setBounds(150, 141, 200, 22);
        contentPane.add(comboBox);

        JLabel lblKelimeKategori = new JLabel("Kelime Kategori:");
        lblKelimeKategori.setBounds(20, 141, 120, 20);
        contentPane.add(lblKelimeKategori);

        // Fill ComboBox with categories
        populateCategoriesComboBox();

        // Resim Seçme Butonuna Tıklanınca Dosya Seçme Penceresi Açılacak
        btnChooseImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Resim Seçin");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    txtImage.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Submit butonuna tıklanınca kelime ekleme işlemi yapılacak
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tr = txtTr.getText();
                String en = txtEn.getText();
                String imagePath = txtImage.getText();

                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(null, "Lütfen bir resim seçin.");
                    return;
                }

                // Dosya adı alınır
                String fileName = new File(imagePath).getName();

                // Kategori ID'sini al
                String selectedCategoryName = (String) comboBox.getSelectedItem();
                int categoryId = getCategoryIdByName(categoriesList, selectedCategoryName);

                if (categoryId == -1) {
                    JOptionPane.showMessageDialog(null, "Kategori seçilmedi!");
                    return;
                }

                // Hedef klasör yolu (proje içindeki bir klasör)
                String targetFolder = "image/";  
                File targetDirectory = new File(targetFolder);
                if (!targetDirectory.exists()) {
                    targetDirectory.mkdir();  // Eğer klasör yoksa oluşturuluyor
                }

                // Yeni dosya yolu
                File targetFile = new File(targetFolder + fileName);

                // Resmi hedef klasöre kopyalama işlemi
                try {
                    Path sourcePath = selectedFile.toPath();
                    Path targetPath = targetFile.toPath();
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);  // Dosya kopyalanıyor
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Resim dosyası kaydedilemedi: " + ex.getMessage());
                    return;
                }

                // Word objesi oluşturuluyor
                Word word = new Word();
                word.setTr(tr);
                word.setEn(en);
                word.setWordImage(fileName); // Resim dosyasının adı veritabanına kaydedilir
                word.setCategoryId(categoryId); // Kategori ID'si ekleniyor

                // Kelime ekleme işlemi
                if (word.Create(word)) {
                    JOptionPane.showMessageDialog(null, "Kelime başarıyla eklendi!");
                } else {
                    JOptionPane.showMessageDialog(null, "Kelime eklenirken bir hata oluştu.");
                }
            }
        });
    }

    // Kategorileri ComboBox'a ekleyin
    private void populateCategoriesComboBox() {
        for (Categories category : categoriesList) {
            comboBox.addItem(category.getCateName());
        }
    }

    // Kategorinin ID'sini adıyla bul
    private static int getCategoryIdByName(List<Categories> categoriesList, String categoryName) {
        for (Categories category : categoriesList) {
            if (category.getCateName().equals(categoryName)) {
                return category.getId();
            }
        }
        return -1; // Kategori bulunamazsa -1 döndür
    }

    // Kategorileri veritabanından veya başka bir kaynaktan almak için kullanılacak metot
    private List<Categories> fetchCategoriesFromDatabase() {
        // Kategorileri veritabanından almanız gerektiğinde burada gerekli kodu yazabilirsiniz
        // Örnek olarak statik bir liste dönebiliriz:
    	Categories cat=new Categories();
    	List<Categories> data=cat.Select();
    	return data;
       
    }
}
