package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMataKuliahGUI {

    private JButton lookButton = new JButton("Lihat");
    private JButton backButton = new JButton("Kembali");
    private ArrayList<MataKuliah> daftarMataKuliah;

    public RingkasanMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel("Ringkasan Mata Kuliah");
        JLabel matkul = new JLabel("Pilih Nama Matkul");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        matkul.setFont(SistemAkademikGUI.fontGeneral);
        
        // Initializing Drop Down
        this.daftarMataKuliah = daftarMataKuliah;
        JComboBox<String> sortedDaftarMatkul = new JComboBox<>(getSortedMatkul());
        sortedDaftarMatkul.setMaximumSize(sortedDaftarMatkul.getPreferredSize());

        // Setting component alignment
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        matkul.setAlignmentX(Component.CENTER_ALIGNMENT);
        lookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding component to panel
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(matkul);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(sortedDaftarMatkul);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lookButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        lookButton.setBackground(new Color(255,252,191));
        lookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String subject = sortedDaftarMatkul.getItemAt(sortedDaftarMatkul.getSelectedIndex());
                MataKuliah matakul = getMataKuliah(subject);

                if (sortedDaftarMatkul.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field");
                }
                else {
                    panel.setVisible(false);
                    new DetailRingkasanMataKuliahGUI(frame, matakul, daftarMahasiswa, daftarMataKuliah);
                }
            }
        });

        backButton.setBackground(new Color(192,238,255));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.setVisible(false);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
            }
        });

        frame.add(panel);
    }

    private String[] getSortedMatkul() {
        String[] sortedMatkul = new String[daftarMataKuliah.size()];
        String matkul = "";

        for (int m = 0; m < daftarMataKuliah.size(); m++) {
            sortedMatkul[m] = daftarMataKuliah.get(m).getNama();
        }

        // Mengurutkan berdasarkan nama secara alphabetical
        for (int i = 0; i < sortedMatkul.length; i++) {
            for (int j = i+1; j < sortedMatkul.length; j++) {
                if (sortedMatkul[i].compareTo(sortedMatkul[j]) > 0) {
                    matkul = sortedMatkul[i];
                    sortedMatkul[i] = sortedMatkul[j];
                    sortedMatkul[j] = matkul;
                }
            }
        }

        return sortedMatkul;
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
