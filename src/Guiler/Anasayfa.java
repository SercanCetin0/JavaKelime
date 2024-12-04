package Guiler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Anasayfa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Anasayfa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btn_kelimeB = new JButton("Kelime Bulmaca");
		btn_kelimeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
dispose();
			KelimeBulmaca kb=new KelimeBulmaca();
			kb.setVisible(true);
			
			
			}
			
		});
		
		btn_kelimeB.setBounds(21, 11, 375, 59);
		contentPane.add(btn_kelimeB);
		
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
		
		
	}

}
