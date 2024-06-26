package assignments.assignment2;

public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kapasitas;
    private Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];

    // Constructor untuk inisiasi attribut MataKuliah
    public MataKuliah(String kode, String nama, int sks, int kapasitas) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
    }

    // Getter untuk attribut MataKuliah yang mengembalikan nilai dari attribut 
    public String getKode() {
        return this.kode;
    }

    public String getNama() {
        return this.nama;
    }

    public int getSKS() {
        return this.sks;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public int getJumlahMahasiswa() {
        int jumlah = 0;
        for (int c = 0; c < this.daftarMahasiswa.length; c++) {
            if (this.daftarMahasiswa[c] != null) {
                jumlah += 1;
            }
        }

        return jumlah;
    }

    // Menambahkan mahasiswa yang melakukan add mata kuliah
    public void addMahasiswa(Mahasiswa mahasiswa) {
        for (int i = 0; i < this.daftarMahasiswa.length; i++) {
            if (this.daftarMahasiswa[i] == null) {
                this.daftarMahasiswa[i] = mahasiswa;
                break;
            }
        }
    }

    // Drop mahasiswa yang melakukan drop mata kuliah
    public void dropMahasiswa(Mahasiswa mahasiswa) {
        Mahasiswa[] nama = new Mahasiswa[this.kapasitas];

        int x = 0;

        // Menambahkan nama mahasiswa pada array baru jika nama tersebut bukan merupakan mahasiswa yang melakukan drop mata kuliah
        for (int j = 0; j < this.daftarMahasiswa.length; j++) {
            if (this.daftarMahasiswa[j] != null) {
                if (this.daftarMahasiswa[j].getNama().equals(mahasiswa.getNama())) {
                    continue;
                }
                else {
                    nama[x++] = this.daftarMahasiswa[j];
                }
            }
        }
        this.daftarMahasiswa = nama;
     }

    public String toString() {
        return nama;
    }
}
