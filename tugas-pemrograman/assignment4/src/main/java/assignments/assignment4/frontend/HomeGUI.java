package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HomeGUI implements ActionListener {

    private JButton addMhsButton = new JButton("Tambah Mahasiswa");
    private JButton addMatkulButton = new JButton("Tambah Mata Kuliah");
    private JButton addIRSButton = new JButton("Tambah IRS");
    private JButton dropIRSButton = new JButton("Hapus IRS");
    private JButton ringkasanMhsButton = new JButton("Lihat Ringkasan Mahasiswa");
    private JButton ringkasanMatkulButton = new JButton("Lihat Ringkasan Mata Kuliah");

    private JPanel panel;
    private JFrame frame;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah) {

        // Initializing panel and setting BoxLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(154,203,255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Selamat datang di Sistem Akademik");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adding buttons to panel
        JButton[] buttons = new JButton[]{addMhsButton, addMatkulButton, addIRSButton, dropIRSButton, ringkasanMhsButton, ringkasanMatkulButton};
        for (int i = 0; i < 6; i++) {
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons[i].setBackground(new Color(225,232,255));
            
            panel.add(buttons[i]);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            buttons[i].addActionListener(this);
        }

        this.panel = panel;
        this.frame = frame;
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        frame.add(panel);
    }

    @Override /* Implement actionPerformed */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMhsButton) {
            panel.setVisible(false);
            new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
        else if (e.getSource() == addMatkulButton) {
            panel.setVisible(false);
            new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
        else if (e.getSource() == addIRSButton) {
            panel.setVisible(false);
            new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
        else if (e.getSource() == dropIRSButton) {
            panel.setVisible(false);
            new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
        else if (e.getSource() == ringkasanMhsButton) {
            panel.setVisible(false);
            new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
        else if (e.getSource() == ringkasanMatkulButton) {
            panel.setVisible(false);
            new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
        }
    }
}
