package assignments.assignment2;

public class Mahasiswa {
    private MataKuliah[] mataKuliah = new MataKuliah[10];
    private String[] masalahIRS = new String[20];
    private int totalSKS;
    private String nama;
    private String jurusan;
    private long npm;

    // Constructor untuk inisiasi attribut Mahasiswa
    public Mahasiswa(String nama, long npm){
        this.nama = nama;
        this.npm = npm;
    }

    // Mengembalikan total sks yang sudah dikurangi saat drop mata kuliah
    public int setTotalSKS(MataKuliah namaMatkul) {
        this.totalSKS -= namaMatkul.getSKS();
        return this.totalSKS;
    }

    // Mengembalikan nama jurusan sesuai npm dan kode jurusan
    public String setJurusan() {
        String kodeJurusan = Long.toString(this.npm).substring(2,4);
        if (kodeJurusan.equals("01")) {
            this.jurusan = "Ilmu Komputer";
        }
        else {
            this.jurusan = "Sistem Informasi";
        }

        return this.jurusan;
    }


    // Getter untuk attribut mahasiswa yang mengembalikan nilai dari attribut 
    public MataKuliah[] getMatKul() {
        return this.mataKuliah;
    }

    public String[] getMasalahIRS() {
        return this.masalahIRS;
    }

    public int getTotalSKS() {
        return this.totalSKS;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJurusan() {
        return setJurusan();
    }

    public long getNPM() {
        return this.npm;
    }

    public int getJumlahMatkul() {
        int jumlah = 0;
        for (int i = 0; i < this.mataKuliah.length; i++) {
            if (this.mataKuliah[i] != null) {
                jumlah += 1;
            }
        }

        return jumlah;
    }

    public int getJumlahMasalahIRS() {
        int jumlah = 0;
        for (int j = 0; j < this.masalahIRS.length; j++) {
            if (this.masalahIRS[j] != null) {
                jumlah += 1;
            }
        }

        return jumlah;
    }

    // Menambahkan mata kuliah ke daftar mata kuliah yang diambil saat ini
    public void addMatkul(MataKuliah mataKuliah){
        for (int i = 0; i < this.mataKuliah.length; i++) {
            if (this.mataKuliah[i] == null) {
                if (mataKuliah.getKapasitas() <= mataKuliah.getJumlahMahasiswa()) {
                    System.out.println("[DITOLAK] " + mataKuliah + " telah penuh kapasitasnya.");
                    break;
                }
                
                // Menambahkan mata kuliah ke daftar mata kuliah yang diambil saat ini dan menambahkan mahasiswa ke daftar mahasiswa yang mengambil mata kuliah tersebut
                else {
                    this.mataKuliah[i] = mataKuliah;
                    this.totalSKS += this.mataKuliah[i].getSKS();
                    this.mataKuliah[i].addMahasiswa(this);
                    break;
                }
            }
            else {
                if (i != this.mataKuliah.length - 1) {
                    if (this.mataKuliah[i].getNama().equals(mataKuliah.getNama())) {
                        System.out.println("[DITOLAK] " + mataKuliah + " telah diambil sebelumnya.");
                        break;
                    }
                }

                // Apabila hingga akhir loop tidak ada matakuliah yang sudah diambil dan jumlah mata kuliah lebih dari 10
                else {
                    if (this.getJumlahMatkul() >= 10) {
                        System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
                        break;
                    }
                }
            }
        }
    }

    // Drop mata kuliah dari mata kuliah yang diambil
    public void dropMatkul(MataKuliah mataKuliah){
        int indeks = 0;
        boolean ada = false;

        // Mencari indeks mata kuliah yang akan di drop
        for (int j = 0; j < this.mataKuliah.length; j++) {
            if (this.mataKuliah[j] != null) {
                if (this.mataKuliah[j].equals(mataKuliah)) {
                    indeks = j;
                    ada = true;
                    break;
                }
            }
        }

        MataKuliah[] daftarMatkul = new MataKuliah[this.mataKuliah.length];
        if (ada == false) {
            System.out.println("[DITOLAK] " + mataKuliah + " belum pernah diambil.");
        }
        else {

            // Menambahkan mata kuliah pada array baru jika mata kuliah tersebut bukan merupakan mata kuliah yang akan di drop dan melakukan drop mahasiswa
            for (int k = 0; k < this.mataKuliah.length; k++) {
                if (this.mataKuliah[k] != null) {
                    this.mataKuliah[k].dropMahasiswa(this);
                    if (k != indeks) {
                        daftarMatkul[k] = this.mataKuliah[k];
                        break;
                    }
                }
            }
            this.mataKuliah = daftarMatkul;
        }
    }

    // Mengecek IRS mahasiswa
    public void cekIRS(){
        for (int m = 0; m < this.mataKuliah.length; m++) {
            if (this.mataKuliah[m] != null) {
                if (this.getTotalSKS() > 24) {
                    this.masalahIRS[m] = "SKS yang Anda ambil lebih dari 24";

                    if (this.mataKuliah[m].getKode().equals("IK") && this.getJurusan().equals("Sistem Informasi")) {
                        this.masalahIRS[m+1] = "Mata Kuliah " + this.mataKuliah[m].getNama()+ " tidak dapat diambil jurusan SI";
                        break;
                    }
                    else if (this.mataKuliah[m].getKode().equals("SI") && this.getJurusan().equals("Ilmu Komputer")) {
                        this.masalahIRS[m+1] = "Mata Kuliah " + this.mataKuliah[m].getNama() + " tidak dapat diambil jurusan IK";
                        break;
                    }
                }
                else {
                    if (this.mataKuliah[m].getKode().equals("IK") && this.getJurusan().equals("Sistem Informasi")) {
                        this.masalahIRS[m] = "Mata Kuliah " + this.mataKuliah[m].getNama()+ " tidak dapat diambil jurusan SI";
                        break;
                    }
                    else if (this.mataKuliah[m].getKode().equals("SI") && this.getJurusan().equals("Ilmu Komputer")) {
                        this.masalahIRS[m] = "Mata Kuliah " + this.mataKuliah[m].getNama() + " tidak dapat diambil jurusan IK";
                        break;
                    }
                }
            }
        }
    }

    public String toString() {
        return this.nama;
    }

}
