package Guiler;

import VeriTabani.Word; // Word sınıfını içe aktarıyoruz
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class BoslukDoldurma extends JFrame {
    private JLabel lblKelime;
    private JTextField txtInput;
    private JButton btnAnasayfa, btnIpuç, btnYenile;
    private String enKelime; // İngilizce kelime
    private String trKelime; // Türkçe kelime
    private int[] emptyIndices; // Boşlukların indeksleri

    public BoslukDoldurma() {
        setTitle("Boşluk Doldurma Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // GridBagLayout ile düzen ayarlaması
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        lblKelime = new JLabel();
        lblKelime.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblKelime, gbc);

        txtInput = new JTextField(15);
        gbc.gridy = 1;
        add(txtInput, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnYenile = new JButton("Yenile");
        buttonPanel.add(btnYenile);

        btnIpuç = new JButton("İpucu");
        buttonPanel.add(btnIpuç);

        btnAnasayfa = new JButton("Anasayfa");
        buttonPanel.add(btnAnasayfa);

        gbc.gridy = 2;
        add(buttonPanel, gbc);

        // Oyun başlangıcı
        updateLabel();

        // Metin kutusunda doğru kelime girildiğinde kontrol et
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String inputKelime = txtInput.getText();
                if (inputKelime.equalsIgnoreCase(enKelime)) {
                    JOptionPane.showMessageDialog(null, "Doğru!", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
                    txtInput.setText(""); // Metin kutusunu temizle
                    updateLabel(); // Yeni kelime yükle
                }
            }
        });

        // İpucu butonuna tıklanıldığında
        btnIpuç.addActionListener(e -> JOptionPane.showMessageDialog(null, "İpucu: " + trKelime, "İpucu", JOptionPane.INFORMATION_MESSAGE));

        // Yenile butonuna tıklanıldığında
        btnYenile.addActionListener(e -> updateLabel());

        // Anasayfa butonuna tıklanıldığında
        btnAnasayfa.addActionListener(e -> {
            dispose();
            Anasayfa anasayfa = new Anasayfa();
            anasayfa.setVisible(true);
        });
    }

    private List<Word> fetchWordsFromDatabase() {
        Word word = new Word();
        return word.Select(0); // Veritabanından kelimeleri çek
    }

    private void updateLabel() {
        List<Word> wordsList = fetchWordsFromDatabase();
        if (wordsList == null || wordsList.isEmpty()) {
            lblKelime.setText("Kelime bulunamadı!");
            return;
        }

        Random random = new Random();
        Word randomWord = wordsList.get(random.nextInt(wordsList.size()));

        enKelime = randomWord.getEn();
        trKelime = randomWord.getTr();

        emptyIndices = new int[2];
        emptyIndices[0] = random.nextInt(enKelime.length());
        emptyIndices[1] = emptyIndices[0];
        while (emptyIndices[1] == emptyIndices[0]) {
            emptyIndices[1] = random.nextInt(enKelime.length());
        }

        StringBuilder maskedWord = new StringBuilder(enKelime);
        maskedWord.setCharAt(emptyIndices[0], '_');
        maskedWord.setCharAt(emptyIndices[1], '_');

        lblKelime.setText(maskedWord.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoslukDoldurma frame = new BoslukDoldurma();
            frame.setVisible(true);
        });
    }
}
