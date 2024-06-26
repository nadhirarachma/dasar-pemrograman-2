package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMahasiswaGUI{
    
    private JButton addButton;
    private JButton backButton;
    private ArrayList<Mahasiswa> daftarMahasiswa;

    public TambahMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        
        this.daftarMahasiswa = daftarMahasiswa;
        
        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel("Tambah Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField namaMhs = new JTextField(20);
        JTextField npmMhs = new JTextField(20);
        
        // Initializing Labels and Add Component to Panel
        String[] deskripsi = new String[]{"Nama:", "NPM:"};
        JTextField[] field = new JTextField[]{namaMhs, npmMhs};

        for (int i = 0; i < 2; i++) {
            JLabel data = new JLabel(deskripsi[i]);
            data.setFont(SistemAkademikGUI.fontGeneral);
            data.setAlignmentX(Component.CENTER_ALIGNMENT);
            field[i].setMaximumSize(field[i].getPreferredSize());

            panel.add(data);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(field[i]);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Initializing and Setting Buttons
        addButton = new JButton("Tambahkan");
        addButton.setBackground(new Color(187,255,181));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        backButton = new JButton("Kembali");
        backButton.setBackground(new Color(192,238,255));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                if (namaMhs.getText().equals("") || npmMhs.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field");
                }
                else if (daftarMahasiswa.contains(getMahasiswa(Long.parseLong(npmMhs.getText())))) {
                    JOptionPane.showMessageDialog(frame, "NPM " + npmMhs.getText() + " sudah pernah ditambahkan sebelumnya");
                    namaMhs.setText("");
                    npmMhs.setText("");
                }
                else {
                    daftarMahasiswa.add(new Mahasiswa(namaMhs.getText(), Long.parseLong(npmMhs.getText())));
                    JOptionPane.showMessageDialog(frame, "Mahasiswa " + npmMhs.getText() + "-" + namaMhs.getText() + " berhasil ditambahkan");
                    namaMhs.setText("");
                    npmMhs.setText("");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.setVisible(false);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
            }
        });

        frame.add(panel);
    }  

    private Mahasiswa getMahasiswa(long npm) {

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }
}
