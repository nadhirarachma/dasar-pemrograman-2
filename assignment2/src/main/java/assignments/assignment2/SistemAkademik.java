package assignments.assignment2;

import java.util.Scanner;

public class SistemAkademik {
    private static final int ADD_MATKUL = 1;
    private static final int DROP_MATKUL = 2;
    private static final int RINGKASAN_MAHASISWA = 3;
    private static final int RINGKASAN_MATAKULIAH = 4;
    private static final int KELUAR = 5;
    private static Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];
    
    private Scanner input = new Scanner(System.in);

    // Mengembalikan nama mahasiswa berdasarkan npmnya
    private Mahasiswa getMahasiswa(long npm) {
        Mahasiswa namaMhs = null;

        for (int x = 0; x < daftarMahasiswa.length; x++) {
            if (daftarMahasiswa[x].getNPM() == npm) {
                namaMhs = daftarMahasiswa[x];
                break;
            }
        }
    
        return namaMhs;
    }

    // Mengembalikan nama mata kuliah berdasarkan namanya
    private MataKuliah getMataKuliah(String namaMataKuliah) {
        MataKuliah namaMatkul = null;

        for (int y = 0; y < daftarMataKuliah.length; y++) {
            if (daftarMataKuliah[y].getNama().equals(namaMataKuliah)) {
                namaMatkul = daftarMataKuliah[y];
                break;
            }
        }   

        return namaMatkul;
    }

    // Menambahkan mata kuliah yang diambil seorang mahasiswa
    private void addMatkul(){
        System.out.println("\n--------------------------ADD MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan ADD MATKUL : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa namaMhs = getMahasiswa(npm); // Membuat objek namaMhs yang mengembalikan nama mahasiswa berdasarkan npmnya

        for (int m = 0; m < namaMhs.getMatKul().length; m++) {
            if (namaMhs.getNPM() == npm) {
                    System.out.print("Banyaknya Matkul yang Ditambah: ");
                    int banyakMatkul = Integer.parseInt(input.nextLine());
                    System.out.println("Masukkan nama matkul yang ditambah");
                    for(int i=0; i < banyakMatkul; i++){
                        System.out.print("Nama matakuliah " + i+1 + " : ");
                        String namaMataKuliah = input.nextLine();
                        namaMhs.addMatkul(getMataKuliah(namaMataKuliah)); // Menambahkan mata kuliah yang diambil seorang mahasiswa
                    }
                System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
                break;
            }
        }
    }

    // Drop mata kuliah yang diambil seorang mahasiswa
    private void dropMatkul(){
        System.out.println("\n--------------------------DROP MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan DROP MATKUL : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa namaMhs = getMahasiswa(npm); // Membuat objek yang mengembalikan nama mahasiswa

        for (int n = 0; n < namaMhs.getMatKul().length; n++) {
            if (namaMhs.getNPM() == npm) {
                if (namaMhs.getJumlahMatkul() == 0) {
                    System.out.println("[DITOLAK] Belum ada mata kuliah yang diambil.");
                    break;
                }
                else {
                    System.out.print("Banyaknya Matkul yang Di-drop: ");
                    int banyakMatkul = Integer.parseInt(input.nextLine());
                    System.out.println("Masukkan nama matkul yang di-drop:");
                    for(int i=0; i<banyakMatkul; i++){
                        System.out.print("Nama matakuliah " + i+1 + " : ");
                        String namaMataKuliah = input.nextLine();
                        namaMhs.dropMatkul(getMataKuliah(namaMataKuliah)); // Drop mata kuliah yang diambil seorang mahasiswa
                        namaMhs.setTotalSKS(getMataKuliah(namaMataKuliah)); // Mengurangi total sks seorang mahasiswa
                    }
                    System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
                }
                break;
            }
        }  
    }

    // Menampilkan ringkasan seorang mahasiswa
    private void ringkasanMahasiswa(){
        System.out.print("Masukkan npm mahasiswa yang akan ditunjukkan ringkasannya : ");
        long npm = Long.parseLong(input.nextLine());
        Mahasiswa namaMhs = getMahasiswa(npm); // Membuat objek yang mengembalikan nama mahasiswa

        for (int a = 0; a < daftarMahasiswa.length; a++) {
            if (namaMhs.getNPM() == npm) {
                System.out.println("\n--------------------------RINGKASAN--------------------------\n");
                System.out.println("Nama: " + this.getMahasiswa(npm));
                System.out.println("NPM: " + npm);
                System.out.println("Jurusan: " + namaMhs.getJurusan());
                System.out.println("Daftar Mata Kuliah: ");

                if (namaMhs.getJumlahMatkul() == 0) {
                    System.out.println("Belum ada mata kuliah yang diambil");
                    System.out.println("Total SKS: " + namaMhs.getTotalSKS());
                    System.out.println("Hasil Pengecekan IRS:\nIRS tidak bermasalah.");
                    break;
                }

                else {

                    // Mencetak daftar matakuliah yang diambil seorang mahasiswa
                    for (int p = 0; p < namaMhs.getJumlahMatkul(); p++) {
                        System.out.println((p+1) + ". " + namaMhs.getMatKul()[p]);
                    }

                    System.out.println("Total SKS: " + namaMhs.getTotalSKS());
                    
                    // Mengembalikan masalah IRS
                    namaMhs.cekIRS();
                    System.out.println("Hasil Pengecekan IRS:");
                    if (namaMhs.getJumlahMasalahIRS() == 0) {
                        System.out.println("IRS tidak bermasalah.");
                        break;
                    }
                    else {

                        // Mencetak daftar masalah IRS
                        for (int p = 0; p < namaMhs.getJumlahMasalahIRS(); p++) {
                            System.out.println((p+1) + ". " + namaMhs.getMasalahIRS()[p]);
                        }
                        break;
                    }
                }
            }
        }
    }

    // Menampilkan ringkasan suatu mata kuliah
    private void ringkasanMataKuliah(){
        System.out.print("Masukkan nama mata kuliah yang akan ditunjukkan ringkasannya : ");
        String namaMataKuliah = input.nextLine();
        MataKuliah namaMatkul = getMataKuliah(namaMataKuliah); // Membuat objek yang mengembalikan nama mata kuliah

        for (int b = 0; b < daftarMataKuliah.length; b++) {
            if (namaMatkul.getNama().equals(namaMataKuliah)) {
                System.out.println("\n--------------------------RINGKASAN--------------------------\n");
                System.out.println("Nama mata kuliah: " + namaMataKuliah);
                System.out.println("Kode: " + namaMatkul.getKode());
                System.out.println("SKS: " + namaMatkul.getSKS());
                System.out.println("Jumlah mahasiswa: " + namaMatkul.getJumlahMahasiswa());
                System.out.println("Kapasitas: " + namaMatkul.getKapasitas());
                System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini: ");

                if (namaMatkul.getJumlahMahasiswa() == 0) {
                    System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
                    break;
                }
                else {

                    // Mencetak daftar mahasiswa yang mengambil suatu mata kuliah
                    for (int p = 0; p < namaMatkul.getJumlahMahasiswa(); p++) {
                    System.out.println((p+1) + ". " + namaMatkul.getDaftarMahasiswa()[p]);
                    }
                    break;
                }
            }
        }
    }

    private void daftarMenu(){
        int pilihan = 0;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------------MENU------------------------------\n");
            System.out.println("Silakan pilih menu:");
            System.out.println("1. Add Matkul");
            System.out.println("2. Drop Matkul");
            System.out.println("3. Ringkasan Mahasiswa");
            System.out.println("4. Ringkasan Mata Kuliah");
            System.out.println("5. Keluar");
            System.out.print("\nPilih: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (pilihan == ADD_MATKUL) {
                addMatkul();
            } else if (pilihan == DROP_MATKUL) {
                dropMatkul();
            } else if (pilihan == RINGKASAN_MAHASISWA) {
                ringkasanMahasiswa();
            } else if (pilihan == RINGKASAN_MATAKULIAH) {
                ringkasanMataKuliah();
            } else if (pilihan == KELUAR) {
                System.out.println("Sampai jumpa!");
                exit = true;
            }
        }

    }


    private void run() {
        System.out.println("====================== Sistem Akademik =======================\n");
        System.out.println("Selamat datang di Sistem Akademik Fasilkom!");
        
        System.out.print("Banyaknya Matkul di Fasilkom: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan matkul yang ditambah");
        System.out.println("format: [Kode Matkul] [Nama Matkul] [SKS] [Kapasitas]");

        for(int i = 0; i < banyakMatkul; i++){
            String[] dataMatkul = input.nextLine().split(" ", 4);
            int sks = Integer.parseInt(dataMatkul[2]);
            int kapasitas = Integer.parseInt(dataMatkul[3]);

            // Membuat instance MataKuliah dan dimasukkan ke dalam Array 
            MataKuliah daftarMatkul = new MataKuliah(dataMatkul[0], dataMatkul[1], sks, kapasitas);
            daftarMataKuliah[i] = daftarMatkul;
        }

        System.out.print("Banyaknya Mahasiswa di Fasilkom: ");
        int banyakMahasiswa = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan data mahasiswa");
        System.out.println("format: [Nama] [NPM]");

        for(int i=0; i<banyakMahasiswa; i++){
            String[] dataMahasiswa = input.nextLine().split(" ", 2);
            long npm = Long.parseLong(dataMahasiswa[1]);
            
            // Membuat instance Mahasiswa dan dimasukkan ke dalam Array 
            Mahasiswa daftarMhs = new Mahasiswa(dataMahasiswa[0], npm);
            daftarMahasiswa[i] = daftarMhs;
        }

        daftarMenu();
        input.close();
    }

    public static void main(String[] args) {
        SistemAkademik program = new SistemAkademik();
        program.run();
    } 
}
