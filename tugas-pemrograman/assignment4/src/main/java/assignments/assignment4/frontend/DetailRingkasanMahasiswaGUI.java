package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMahasiswaGUI {

    private Mahasiswa mahasiswa;
    private JButton doneButton;
    private Font fontMatkul = new Font("Century Gothic", Font.BOLD, 12);

    public DetailRingkasanMahasiswaGUI(JFrame frame, Mahasiswa mahasiswa, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        this.mahasiswa = mahasiswa;

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel("Detail Ringkasan Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

       // Initializing Labels and Add Component to Panel
        String[] deskripsi = new String[]{"Nama: ", "NPM: ", "Jurusan: ", "Daftar Mata Kuliah:"};
        String[] data = new String[]{mahasiswa.getNama(), mahasiswa.getJurusan(), Long.toString(mahasiswa.getNpm()), ""};

        for (int i = 0; i < 4; i++) {
            JLabel ringkasan = new JLabel(deskripsi[i] + data[i]);
            ringkasan.setFont(SistemAkademikGUI.fontGeneral);
            ringkasan.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            panel.add(ringkasan);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Initializing Labels and Set Alignment
        JLabel totalSKS = new JLabel("Total SKS: " + mahasiswa.getTotalSKS());
        JLabel hasil = new JLabel("Hasil Pengecekan IRS:");
        JLabel aman = new JLabel("IRS tidak bermasalah.");

        totalSKS.setFont(SistemAkademikGUI.fontGeneral);
        hasil.setFont(SistemAkademikGUI.fontGeneral);
        aman.setFont(fontMatkul);

        totalSKS.setAlignmentX(Component.CENTER_ALIGNMENT);
        hasil.setAlignmentX(Component.CENTER_ALIGNMENT);
        aman.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (mahasiswa.getBanyakMatkul() == 0) {
            JLabel kosong = new JLabel("Belum ada mata kuliah yang diambil.");
            kosong.setFont(fontMatkul);
            kosong.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel[] dataMhs = new JLabel[] {kosong, totalSKS, hasil, aman};
            for (int i = 0; i < 4; i++) {
                panel.add(dataMhs[i]);
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        else {
            // Mencetak daftar mata kuliah
            for (int i = 0; i < mahasiswa.getBanyakMatkul(); i++) {
                JLabel matkul = new JLabel((i+1) + ". " + getSortedMatkul()[i]);
                matkul.setFont(fontMatkul);
                matkul.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(matkul);
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            panel.add(totalSKS);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            mahasiswa.cekIRS();
            panel.add(hasil);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            if (mahasiswa.getBanyakMasalahIRS() == 0) {
                panel.add(aman);
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            else {
                // Mencetak daftar masalah IRS
                for (int i = 0; i < mahasiswa.getBanyakMasalahIRS(); i++) {
                    JLabel masalahIRS = new JLabel((i+1) + ". " + mahasiswa.getMasalahIRS()[i]);
                    masalahIRS.setFont(fontMatkul);
                    masalahIRS.setAlignmentX(Component.CENTER_ALIGNMENT);

                    panel.add(masalahIRS);
                    panel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
        }

        doneButton = new JButton("Selesai");
        doneButton.setBackground(new Color(192,238,255));
        doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(doneButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.setVisible(false);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
            }
        });
        
        frame.add(panel);
    }

    private String[] getSortedMatkul() {
        String[] sortedMatkul = new String[mahasiswa.getBanyakMatkul()];
        String matkul = "";

        for (int m = 0; m < mahasiswa.getBanyakMatkul(); m++) {
            sortedMatkul[m] = mahasiswa.getMataKuliah()[m].getNama();
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
}
