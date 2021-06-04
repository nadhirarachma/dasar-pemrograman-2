package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HapusIRSGUI {

    private JButton deleteButton;
    private JButton backButton;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public HapusIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){

        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 197, 254));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Hapus IRS");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Initializing Labels
        JLabel npm = new JLabel("Pilih NPM");
        npm.setFont(SistemAkademikGUI.fontGeneral);
        npm.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel matkul = new JLabel("Pilih Nama Matkul");
        matkul.setFont(SistemAkademikGUI.fontGeneral);
        matkul.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initializing Drop Down
        JComboBox<String> daftarNPM = new JComboBox<>(getSortedNPM());
        daftarNPM.setMaximumSize(daftarNPM.getPreferredSize());
        JComboBox<String> sortedDaftarMatkul = new JComboBox<>(getSortedMatkul());
        sortedDaftarMatkul.setMaximumSize(sortedDaftarMatkul.getPreferredSize());

        // Adding component to panel
        panel.add(npm);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(daftarNPM);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(matkul);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(sortedDaftarMatkul);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Initializing and Setting Buttons
        deleteButton = new JButton("Hapus");
        deleteButton.setBackground(new Color(255,223,239));
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(deleteButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        backButton = new JButton("Kembali");
        backButton.setBackground(new Color(192,238,255));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String num = daftarNPM.getItemAt(daftarNPM.getSelectedIndex());
                String subject = sortedDaftarMatkul.getItemAt(sortedDaftarMatkul.getSelectedIndex());
                Mahasiswa mhs = getMahasiswa(Long.parseLong(num));
                MataKuliah matakul = getMataKuliah(subject);

                if (daftarNPM.getItemCount() == 0 || sortedDaftarMatkul.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "Mohon isi seluruh field");
                }
                else {
                    JOptionPane.showMessageDialog(frame, mhs.dropMatkul(matakul));
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

    private Mahasiswa getMahasiswa(long npm) {

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }
}
