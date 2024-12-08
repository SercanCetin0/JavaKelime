package Guiler;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Üye İşlemleri");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Uyeİslemleri Uye = new Uyeİslemleri();
				Uye.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(57, 26, 111, 23);
		contentPane.add(btnNewButton);
		
		JButton btnKelimeIlemleri = new JButton("Kelime İşlemleri");
		btnKelimeIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kelimeİslemleri ki=new Kelimeİslemleri();
				dispose();
				ki.setVisible(true);
			
			}
		});
		btnKelimeIlemleri.setBounds(57, 65, 111, 23);
		contentPane.add(btnKelimeIlemleri);
		
		JButton btnkYap = new JButton("Çıkış Yap");
		btnkYap.setForeground(new Color(255, 0, 0));
		btnkYap.setBounds(57, 99, 111, 23);
		contentPane.add(btnkYap);
	}

}
