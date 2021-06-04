package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMataKuliahGUI {

    private MataKuliah mataKuliah;
    private JButton doneButton;
    private Font fontMatkul = new Font("Century Gothic", Font.BOLD, 12);

    public DetailRingkasanMataKuliahGUI(JFrame frame, MataKuliah mataKuliah, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        
        this.mataKuliah = mataKuliah;

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel("Detail Ringkasan Mata Kuliah");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Initializing Labels and Add Component to Panel
        String[] deskripsi = new String[]{"Nama mata kuliah: ", "Kode: ", "SKS: ", "Jumlah mahasiswa: ", "Kapasitas: ", "Daftar Mahasiswa:"};
        String[] data = new String[]{mataKuliah.getNama(), mataKuliah.getKode(), Integer.toString(mataKuliah.getSKS()), Integer.toString(mataKuliah.getJumlahMahasiswa()), Integer.toString(mataKuliah.getKapasitas()), ""};
        
        for (int i = 0; i < 6; i++) {
            JLabel ringkasan = new JLabel(deskripsi[i] + data[i]);
            ringkasan.setFont(SistemAkademikGUI.fontGeneral);
            ringkasan.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(ringkasan);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        if (mataKuliah.getJumlahMahasiswa() == 0) {
            JLabel mhs = new JLabel("Belum ada mahasiswa yang mengambil mata kuliah ini.");
            mhs.setFont(fontMatkul);
            mhs.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(mhs);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        else {
            // Mencetak daftar mahasiswa
            for (int i = 0; i < mataKuliah.getJumlahMahasiswa(); i++) {
                JLabel mhs = new JLabel((i+1) + ". " + getSortedMhs()[i]);
                mhs.setFont(fontMatkul);
                mhs.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                panel.add(mhs);
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
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

    private String[] getSortedMhs() {
        String[] sortedMhs = new String[mataKuliah.getJumlahMahasiswa()];
        String data = "";

        for (int m = 0; m < mataKuliah.getJumlahMahasiswa(); m++) {
            sortedMhs[m] = mataKuliah.getDaftarMahasiswa()[m].getNama();
        }

        for (int i = 0; i < sortedMhs.length; i++) {
            for (int j = i+1; j < sortedMhs.length; j++) {
                if (sortedMhs[i].compareTo(sortedMhs[j]) > 0) {
                    data = sortedMhs[i];
                    sortedMhs[i] = sortedMhs[j];
                    sortedMhs[j] = data;
                }
            }
        }
        
        return sortedMhs;
    }   
}
