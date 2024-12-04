package Guiler;

import VeriTabani.Word;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoktanSecmeli extends JFrame {
    private JLabel lblKelime;
    private JButton btnSecenek1, btnSecenek2, btnSecenek3, btnAnasayfa, btnYenile;
    private String dogruKelime; // İngilizce kelime
    private String dogruCevap; // Türkçe karşılık

    public CoktanSecmeli() {
        setTitle("Çoktan Seçmeli Kelime Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Ana düzen için GridBagLayout kullanılıyor
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Kelimeyi göstermek için label
        lblKelime = new JLabel();
        lblKelime.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblKelime, gbc);

        // Seçenek butonları alt alta yerleştiriliyor
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;

        btnSecenek1 = new JButton();
        btnSecenek1.setPreferredSize(new Dimension(300, 40));
        add(btnSecenek1, gbc);

        gbc.gridy = 2;
        btnSecenek2 = new JButton();
        btnSecenek2.setPreferredSize(new Dimension(300, 40));
        add(btnSecenek2, gbc);

        gbc.gridy = 3;
        btnSecenek3 = new JButton();
        btnSecenek3.setPreferredSize(new Dimension(300, 40));
        add(btnSecenek3, gbc);

        // Anasayfa ve Yenile butonları en alta yerleştiriliyor
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());

        btnYenile = new JButton("Yenile");
        buttonPanel.add(btnYenile);

        btnAnasayfa = new JButton("Anasayfa");
        buttonPanel.add(btnAnasayfa);

        gbc.gridy = 5;
        add(buttonPanel, gbc);

        // Oyun başlangıcı
        updateQuestion();

        // Yenile butonuna tıklanıldığında
        btnYenile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuestion();
            }
        });

        // Anasayfa butonuna tıklanıldığında
        btnAnasayfa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);
            }
        });

        // Şıklar için eylemler
        ActionListener secenekDinleyici = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btnSecilen = (JButton) e.getSource();
                if (btnSecilen.getText().equals(dogruCevap)) {
                    JOptionPane.showMessageDialog(null, "Doğru!", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
                    updateQuestion(); // Yeni soru yükle
                } else {
                    JOptionPane.showMessageDialog(null, "Yanlış, tekrar deneyin!", "Sonuç", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        btnSecenek1.addActionListener(secenekDinleyici);
        btnSecenek2.addActionListener(secenekDinleyici);
        btnSecenek3.addActionListener(secenekDinleyici);
    }

    private List<Word> fetchWordsFromDatabase() {
        Word word = new Word();
        return word.Select(0); // Veritabanından kelimeleri çek
    }

    private void updateQuestion() {
        List<Word> wordsList = fetchWordsFromDatabase();
        if (wordsList == null || wordsList.size() < 3) {
            lblKelime.setText("Yeterli kelime yok!");
            return;
        }

        Random random = new Random();
        Word selectedWord = wordsList.get(random.nextInt(wordsList.size()));

        dogruKelime = selectedWord.getEn();
        dogruCevap = selectedWord.getTr();

        lblKelime.setText(dogruKelime);

        // Yanlış seçenekleri seç
        List<String> secenekler = new ArrayList<>();
        secenekler.add(dogruCevap);

        while (secenekler.size() < 3) {
            String yanlisCevap = wordsList.get(random.nextInt(wordsList.size())).getTr();
            if (!secenekler.contains(yanlisCevap)) {
                secenekler.add(yanlisCevap);
            }
        }

        // Şıkları karıştır
        java.util.Collections.shuffle(secenekler);

        // Butonlara şıkları yerleştir
        btnSecenek1.setText(secenekler.get(0));
        btnSecenek2.setText(secenekler.get(1));
        btnSecenek3.setText(secenekler.get(2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoktanSecmeli frame = new CoktanSecmeli();
            frame.setVisible(true);
        });
    }
}
