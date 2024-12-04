package Guiler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VeriTabani.User;
import VeriTabani.UserLoginResult;
import VeriTabani.UserSession;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Email;
	private JPasswordField txt_Password;
	private JLabel lbl_Hata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);//Birleşenleri istediğimiz yere yerleştirmek için
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Giriş Yap");
		
		//Butona Tıklandığında Çalışır
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
				LoginControl();
			
			}
		});
		
		
		
		btnNewButton.setBounds(225, 144, 89, 23);
		contentPane.add(btnNewButton);
		txt_Email = new JTextField();
		txt_Email.setBounds(179, 74, 135, 20);
		contentPane.add(txt_Email);
		txt_Email.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("E-Mail :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(115, 75, 54, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblifre = new JLabel("Şifre :");
		lblifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblifre.setBounds(126, 114, 43, 14);
		contentPane.add(lblifre);
		
		txt_Password = new JPasswordField();
		txt_Password.setBounds(179, 113, 135, 20);
		contentPane.add(txt_Password);
		
		lbl_Hata = new JLabel("Hata Mesajı");
		lbl_Hata.setForeground(new Color(255, 0, 0));
		lbl_Hata.setBounds(115, 50, 199, 14);
		lbl_Hata.setVisible(false);
		contentPane.add(lbl_Hata);
		
		JLabel lbl_LoginConsol = new JLabel("Kayıt İçin Tıklayınız");
		lbl_LoginConsol.setForeground(new Color(0, 64, 128));
		lbl_LoginConsol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_LoginConsol.setBounds(126, 148, 89, 14);
		contentPane.add(lbl_LoginConsol);
		lbl_LoginConsol.addMouseListener(new MouseAdapter() 
		 {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Login formunu aç
	                Register loginForm = new Register();
	                loginForm.setVisible(true);
	            }
	        });
	}
	
	private void LoginControl()
	{
	try {
		String Email=txt_Email.getText();
		String Password =new String(txt_Password.getPassword());
		
		User user=new User();
		
		user.setEmail(Email);
		user.setPassword(Password);
		UserLoginResult userData=user.UserLoginControl();
		String userId = String.valueOf( userData.getId());
		String Rol=userData.getStatus();
		lbl_Hata.setVisible(true);
		if(userId!=null)
		{
			UserSession.getInstance().setUserRol(Rol);
			UserSession.getInstance().setUserId(userId);
			lbl_Hata.setForeground(new Color(0, 255, 0));
			Thread.sleep(1000);
			lbl_Hata.setText("Başarılı! Yönlendiriliyorsunuz.");
			dispose();//Ekranı kapama
			Anasayfa kb=new Anasayfa();
			kb.setVisible(true);
		}
		else {
			
			lbl_Hata.setForeground(new Color(255, 0, 0));
			lbl_Hata.setText("Giriş Başarısız. Tekrar Deneyiniz!");
		}
	}
	catch (Exception e) {
		// TODO: handle exception
	}
		
	}
	
}
