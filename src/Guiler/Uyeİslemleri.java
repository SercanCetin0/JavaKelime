package Guiler;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import VeriTabani.User;
import java.awt.Color;

public class Uyeİslemleri extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtId, txtName, txtEmail, txtStatus;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Uyeİslemleri frame = new Uyeİslemleri();
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
    public Uyeİslemleri() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 919, 582);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tablo için model oluştur
        String[] columnNames = { "User ID", "Name", "Email", "Status" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // JTable ve JScrollPane ekleme
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 80, 820, 300);
        contentPane.add(scrollPane);

        // Tablo seçim dinleyicisi
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Seçim işlemi tamamlandıysa
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Seçilen satırdaki verileri al
                        Object userId = table.getValueAt(selectedRow, 0);
                        Object name = table.getValueAt(selectedRow, 1);
                        Object email = table.getValueAt(selectedRow, 2);
                        Object status = table.getValueAt(selectedRow, 3);

                        // Metin kutularına yaz
                        txtId.setText(userId.toString());
                        txtName.setText(name.toString());
                        txtEmail.setText(email.toString());
                        txtStatus.setText(status.toString());
                    }
                }
            }
        });

        // Metin kutuları ekleme
        txtId = new JTextField();
        txtId.setBounds(10, 400, 150, 30);
        txtId.setEnabled(false);
        contentPane.add(txtId);

        txtName = new JTextField();
        txtName.setBounds(170, 400, 200, 30);
        contentPane.add(txtName);

        txtEmail = new JTextField();
        txtEmail.setBounds(380, 400, 250, 30);
        contentPane.add(txtEmail);

        txtStatus = new JTextField();
        txtStatus.setBounds(640, 400, 150, 30);
        contentPane.add(txtStatus);

        // Veri getirme butonu
        JButton btnNewButton = new JButton("Verileri Getir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VeriCek(tableModel);
            }
        });
        btnNewButton.setBounds(701, 30, 129, 45);
        contentPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("Güncelle");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		        // Form alanlarından verileri al
        		        String idText = txtId.getText();
        		        String name = txtName.getText();
        		        String email = txtEmail.getText();
        		        String status = txtStatus.getText();

        		        // ID'nin boş olmaması ve bir sayı olması gerektiğini kontrol et
        		        if (idText.isEmpty() || name.isEmpty() || email.isEmpty() || status.isEmpty()) {
        		            System.out.println("Lütfen tüm alanları doldurun.");
        		            return;
        		        }
        		        status=status.toUpperCase();

        		        try {
        		            int id = Integer.parseInt(idText);

        		            // User nesnesi oluştur
        		            User userToUpdate = new User();
        		            userToUpdate.setId(id);
        		            userToUpdate.setFirstNameLastName(name);
        		            userToUpdate.setEmail(email);
        		            userToUpdate.setStatus(status);

        		            // User nesnesini güncelle
        		            boolean isUpdated = userToUpdate.Update(userToUpdate);
        		            if (isUpdated) {
        		                // Tabloyu güncelle
        		                VeriCek((DefaultTableModel) table.getModel());
        		            } else {
        		                System.out.println("Kullanıcı güncellenemedi.");
        		            }
        		        } catch (NumberFormatException ex) {
        		            System.out.println("ID bir sayı olmalıdır.");
        		        }
        		    }
        		

        
        });
        btnNewButton_1.setBounds(701, 441, 89, 23);
        contentPane.add(btnNewButton_1);
        
        btnNewButton_2 = new JButton("Sil");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  int selectedRow = table.getSelectedRow();
        	        
        	        if (selectedRow != -1) {
        	            // Seçilen satırdaki ID'yi al
        	            Object userId = table.getValueAt(selectedRow, 0);
        	            
        	            // Silme işlemi için ID'yi kontrol et
        	            if (userId != null) {
        	                try {
        	                    int id = Integer.parseInt(userId.toString());
        	                    
        	                    // User nesnesi oluştur
        	                    User userToDelete = new User();
        	                   
        	                    
        	                    // User nesnesini sil
        	                    boolean isDeleted = userToDelete.Delete( id);
        	                    if (isDeleted) {
        	                        System.out.println("Kullanıcı başarıyla silindi.");
        	                        // Tabloyu güncelle
        	                        VeriCek((DefaultTableModel) table.getModel());
        	                    } else {
        	                        System.out.println("Kullanıcı silinemedi.");
        	                    }
        	                } catch (NumberFormatException ex) {
        	                    System.out.println("Geçersiz kullanıcı ID.");
        	                }
        	            }
        	        } else {
        	            System.out.println("Lütfen bir kullanıcı seçin.");
        	        }
        	    }
        	
        });
        btnNewButton_2.setForeground(new Color(255, 0, 0));
        btnNewButton_2.setBounds(701, 475, 89, 23);
        contentPane.add(btnNewButton_2);
        
        btnNewButton_3 = new JButton("Geri");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 dispose();
        		Admin adm=new Admin();
        		adm.setVisible(true);
        		
        	}
        });
        btnNewButton_3.setBounds(10, 447, 89, 51);
        contentPane.add(btnNewButton_3);
    }

    /**
     * Verileri çek ve tabloya ekle
     */
    private void VeriCek(DefaultTableModel tableModel) {
        // Tablodaki eski verileri temizle
        tableModel.setRowCount(0);

        // Veritabanından kullanıcıları al
        User userBilgi = new User();
        List<User> users = userBilgi.Select();

        // Kullanıcıları tabloya ekle
        for (User user : users) {
            Object[] row = { user.getId(), user.getFirstNameLastName(), user.getEmail(), user.getStatus() };
            tableModel.addRow(row);
        }
    }
}
