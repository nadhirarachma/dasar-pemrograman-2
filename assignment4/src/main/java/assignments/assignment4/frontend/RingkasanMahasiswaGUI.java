package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMahasiswaGUI {

    private JButton lookButton = new JButton("Lihat");
    private JButton backButton = new JButton("Kembali");
    private ArrayList<Mahasiswa> daftarMahasiswa;

    public RingkasanMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel("Ringkasan Mahasiswa");
        JLabel npm = new JLabel("Pilih NPM");
        titleLabel.setFont(SistemAkademikGUI.fontTitle); 
        npm.setFont(SistemAkademikGUI.fontGeneral);
        
        // Initializing Drop Down
        this.daftarMahasiswa = daftarMahasiswa;
        JComboBox<String> daftarNPM = new JComboBox<>(getSortedNPM());
        daftarNPM.setMaximumSize(daftarNPM.getPreferredSize());

        // Setting component alignment
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        npm.setAlignmentX(Component.CENTER_ALIGNMENT);
        lookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding component to panel
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(npm);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(daftarNPM);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lookButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        lookButton.setBackground(new Color(255,252,191));
        lookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String num = daftarNPM.getItemAt(daftarNPM.getSelectedIndex());
                Mahasiswa mhs = getMahasiswa(Long.parseLong(num));

                if (daftarNPM.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field");
                }
                else {
                    panel.setVisible(false);
                    new DetailRingkasanMahasiswaGUI(frame, mhs, daftarMahasiswa, daftarMataKuliah);
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

    private String[] getSortedNPM() {
        String[] sortedNPM = new String[daftarMahasiswa.size()];
        String npm = "";

        for (int m = 0; m < daftarMahasiswa.size(); m++) {
            sortedNPM[m] = Long.toString(daftarMahasiswa.get(m).getNpm());
        }

        // Mengurutkan berdasarkan nama secara alphabetical
        for (int i = 0; i < sortedNPM.length; i++) {
            for (int j = i+1; j < sortedNPM.length; j++) {
                if (Long.parseLong(sortedNPM[i]) > Long.parseLong(sortedNPM[j])) {
                    npm = sortedNPM[i];
                    sortedNPM[i] = sortedNPM[j];
                    sortedNPM[j] = npm;
                }
            }
        }

        return sortedNPM;
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
