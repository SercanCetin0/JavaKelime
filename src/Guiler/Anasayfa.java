package Guiler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import VeriTabani.UserSession;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Anasayfa extends JFrame {
    // Kullanıcı bilgilerini almak için UserSession kullanılıyor
    String userId = UserSession.getInstance().getUserId(); 
    String rol = UserSession.getInstance().getUserRol(); 

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Uygulamayı başlatır.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Anasayfa frame = new Anasayfa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Ana pencereyi oluşturur.
     */
    public Anasayfa() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Kelime Bulmaca butonu
        JButton btn_kelimeB = new JButton("Kelime Bulmaca");
        btn_kelimeB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                KelimeBulmaca kb = new KelimeBulmaca();
                kb.setVisible(true);
            }
        });
        btn_kelimeB.setBounds(21, 11, 375, 59);
        contentPane.add(btn_kelimeB);

        // Çoktan Seçmeli butonu
        JButton btnNewButton_1 = new JButton("Çoktan Seçmeli");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CoktanSecmeli ck = new CoktanSecmeli();
                ck.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(21, 97, 375, 59);
        contentPane.add(btnNewButton_1);

        // Boşluk Doldurma butonu
        JButton btnNewButton_2 = new JButton("Boşluk Doldurma");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BoslukDoldurma bd = new BoslukDoldurma();
                bd.setVisible(true);
            }
        });
        btnNewButton_2.setBounds(21, 191, 375, 59);
        contentPane.add(btnNewButton_2);
 
        // Eğer kullanıcı Admin ise, Admin Sayfası butonunu ekle
        if (rol.equals("Admin")) {
            JButton btnNewButton_3 = new JButton("Admin Sayfası");
            btnNewButton_3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Admin admin = new Admin();
                    admin.setVisible(true);
                }
            });
            btnNewButton_3.setBounds(21, 281, 375, 59); // Doğru konumlandırma
            contentPane.add(btnNewButton_3);
        }
    }
}
