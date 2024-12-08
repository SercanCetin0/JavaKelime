package Guiler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import VeriTabani.User;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_ad;
    private JTextField txt_email;
    private JPasswordField txt_pass;
    private JPasswordField txt_pass1;
    private JLabel lbl_Hata; // Corrected label declaration

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Register frame = new Register();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Ad-Soyad");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(86, 32, 74, 14);
        contentPane.add(lblNewLabel);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(121, 67, 39, 14);
        contentPane.add(lblEmail);

        JLabel lblifre = new JLabel("Şifre");
        lblifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblifre.setBounds(128, 110, 32, 14);
        contentPane.add(lblifre);

        JLabel lblifreOnay = new JLabel("Şifre Onayı");
        lblifreOnay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblifreOnay.setBounds(81, 148, 89, 19);
        contentPane.add(lblifreOnay);

        txt_ad = new JTextField();
        txt_ad.setBounds(170, 31, 111, 20);
        contentPane.add(txt_ad);
        txt_ad.setColumns(10);

        txt_email = new JTextField();
        txt_email.setColumns(10);
        txt_email.setBounds(170, 66, 111, 20);
        contentPane.add(txt_email);

        txt_pass = new JPasswordField();
        txt_pass.setBounds(170, 109, 111, 20);
        contentPane.add(txt_pass);

        txt_pass1 = new JPasswordField();
        txt_pass1.setBounds(170, 147, 111, 20);
        contentPane.add(txt_pass1);

        JButton btnNewButton = new JButton("Kayıt Ol");
        btnNewButton.addActionListener(e -> RegisterControl());
        btnNewButton.setBounds(192, 186, 89, 23);
        contentPane.add(btnNewButton);

        JLabel lbl_LoginConsol = new JLabel("Giriş İçin Tıklayınız");
        lbl_LoginConsol.setForeground(new Color(0, 64, 128));
        lbl_LoginConsol.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl_LoginConsol.setBounds(95, 190, 89, 14);
        contentPane.add(lbl_LoginConsol);

        lbl_Hata = new JLabel(""); // Initialize error label
        lbl_Hata.setBounds(86, 7, 195, 14);
        contentPane.add(lbl_Hata);

        lbl_LoginConsol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the login form
                Login loginForm = new Login();
                loginForm.setVisible(true);
            }
        });
    }

    private void RegisterControl() {
        try {
            // Check if passwords match
            if (new String(txt_pass.getPassword()).equals(new String(txt_pass1.getPassword()))) {
                User user = new User();
                user.setEmail(txt_email.getText());
                user.setPassword(new String(txt_pass.getPassword()));
                user.setFirstNameLastName(txt_ad.getText());
                user.setActivated(true);
                user.setStatus("USER");
                
                // Create the user and handle the result
                Boolean result = user.Create(user);
                
                if (result) {
            		lbl_Hata.setForeground(new Color(0, 255, 0));
                    lbl_Hata.setText("Başarılı");
                    Thread.sleep(2500);
                    dispose();
                    Login login=new Login();
                    login.setVisible(true);
                } else {
            		lbl_Hata.setForeground(new Color(255, 0, 0));

                    lbl_Hata.setText("Kayıt sırasında bir hata oluştu.");
                }
            } else {
        		lbl_Hata.setForeground(new Color(255, 0, 0));

                lbl_Hata.setText("Parolalar eşleşmiyor.");
            }
        } catch (Exception e) {
            lbl_Hata.setText("Bir hata oluştu: " + e.getMessage());
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}
