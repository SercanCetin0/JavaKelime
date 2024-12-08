package Guiler;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import javax.swing.event.DocumentListener;

import VeriTabani.User;
import VeriTabani.UserSession;
import VeriTabani.Word;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KelimeBulmaca extends JFrame {
	private String enKelime="";
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_tr;
    private JTextField txt_en;
    private JLabel lbl_trr; // Move label declaration here for accessibility
    private JLabel lbl_image; // Moved here for accessibility
    private JButton btnAnasayfa;
    String userId = UserSession.getInstance().getUserId(); 
    String rol = UserSession.getInstance().getUserRol(); 
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                KelimeBulmaca frame = new KelimeBulmaca();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public KelimeBulmaca() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JButton btnNewButton = new JButton("Değiştir");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 updateLabel();
        	}
        });
        btnNewButton.setBounds(202, 206, 89, 44);
        contentPane.add(btnNewButton);

        txt_tr = new JTextField();
        txt_tr.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txt_tr.setBounds(179, 111, 112, 35);
       
        contentPane.add(txt_tr);
        txt_tr.setEnabled(false);
        txt_tr.setColumns(10);

        txt_en = new JTextField();
        txt_en.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txt_en.setColumns(10);
        txt_en.setBounds(179, 157, 112, 35);
        contentPane.add(txt_en);

        lbl_trr = new JLabel("TR"); // Declare label here
        lbl_trr.setBounds(137, 124, 21, 14);
        contentPane.add(lbl_trr);

        JLabel lbl_en = new JLabel("EN");
        lbl_en.setBounds(137, 170, 21, 14);
        contentPane.add(lbl_en);

        txt_en.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	txtEnControl();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	txtEnControl();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	txtEnControl();
            }
        });
        lbl_image = new JLabel(); // Initialize the label for the image
        lbl_image.setBounds(126, 11, 191, 92);
        contentPane.add(lbl_image);
        
        btnAnasayfa = new JButton("Anasayfa");
        btnAnasayfa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		Anasayfa anasayfa=new Anasayfa();
        		anasayfa.setVisible(true);
        	}
        });
        btnAnasayfa.setBounds(335, 227, 89, 23);
        contentPane.add(btnAnasayfa);
    }

    private void updateLabel() {
        Word word = new Word();
        List<Word> liste = word.Select();

        Random random = new Random();
        int randomIndex = random.nextInt(liste.size());

        // Rastgele seçilen kelimeyi alın
        Word kelime = liste.get(randomIndex);
        txt_tr.setText(kelime.getTr());

        
        enKelime = kelime.getEn();
        loadImage("image/"+kelime.getWordImage());
    }
    private void loadImage(String imagePath) {
        try {
            ImageIcon resimIcon = new ImageIcon(imagePath);
            Image img = resimIcon.getImage();
            Image scaledImg = img.getScaledInstance(191, 92, Image.SCALE_SMOOTH);
            lbl_image.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            System.err.println("Image not found: " + imagePath);
        }
    }
    private void txtEnControl()
    {
    	if(txt_en.getText().equalsIgnoreCase(enKelime))
    	{
    		  SwingUtilities.invokeLater(() -> {
    	            updateLabel();
    	            txt_en.setText("");
    	        });
    		
    	}
    }

}
