package Guiler;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AdminSayfasi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Uygulamayı başlatır.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminSayfasi frame = new AdminSayfasi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Admin Sayfası penceresini oluşturur.
     */
    public AdminSayfasi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Geri Dön butonu
        JButton btnBack = new JButton("Geri Dön");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Admin sayfasını kapat
                Anasayfa anasayfa = new Anasayfa(); // Ana sayfayı aç
                anasayfa.setVisible(true);
            }
        });
        btnBack.setBounds(150, 100, 150, 40); // Butonun konumlandırılması
        contentPane.add(btnBack);

        // Mesaj Göster butonu
        JButton btnShowMessage = new JButton("Mesaj Göster");
        btnShowMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Admin Sayfasına Hoşgeldiniz!");
            }
        });
        btnShowMessage.setBounds(150, 160, 150, 40); // Butonun konumlandırılması
        contentPane.add(btnShowMessage);
    }
}
