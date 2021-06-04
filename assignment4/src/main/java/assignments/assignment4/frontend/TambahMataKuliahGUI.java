package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMataKuliahGUI extends JFrame {

    private JButton addButton;
    private JButton backButton;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public TambahMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel titleLabel = new JLabel("Tambah Mata Kuliah");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField kodeMatkul = new JTextField(20);
        JTextField namaMatkul = new JTextField(20);
        JTextField sksMatkul = new JTextField(20);
        JTextField kapasitasMatkul = new JTextField(20);

        // Initializing Labels and Add Component to Panel
        String[] deskripsi = new String[]{"Kode Mata Kuliah:", "Nama Mata Kuliah:", "SKS:", "Kapasitas:"};
        JTextField[] field = new JTextField[]{kodeMatkul, namaMatkul, sksMatkul, kapasitasMatkul};

        for (int i = 0; i < 4; i++) {
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
        
        this.daftarMataKuliah = daftarMataKuliah;
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                if (kodeMatkul.getText().equals("") || namaMatkul.getText().equals("") || sksMatkul.getText().equals("") || kapasitasMatkul.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field");
                }
                else if (daftarMataKuliah.contains(getMataKuliah(namaMatkul.getText()))) {
                    JOptionPane.showMessageDialog(frame, "Mata Kuliah " + namaMatkul.getText() + " sudah pernah ditambahkan sebelumnya");
                    kodeMatkul.setText("");
                    namaMatkul.setText("");
                    sksMatkul.setText("");
                    kapasitasMatkul.setText("");
                }
                else {
                    daftarMataKuliah.add(new MataKuliah(kodeMatkul.getText(), namaMatkul.getText(), Integer.parseInt(sksMatkul.getText()), Integer.parseInt(kapasitasMatkul.getText())));
                    JOptionPane.showMessageDialog(frame, "Mata Kuliah " + namaMatkul.getText() + " berhasil ditambahkan");
                    kodeMatkul.setText("");
                    namaMatkul.setText("");
                    sksMatkul.setText("");
                    kapasitasMatkul.setText("");
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
    
    private MataKuliah getMataKuliah(String nama) {

        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }
}
