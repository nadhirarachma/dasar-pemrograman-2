package assignments.assignment3;

class MataKuliah {

    private String nama;
    
    private int kapasitas;

    private Dosen dosen;

    private Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];

    // Constructor
    MataKuliah(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    // Getter untuk attribut mata kuliah yang mengembalikan nilai dari attribut 
    public String getNama() {
        return this.nama;
    }

    public int getKapasitas() {
        return this.kapasitas;
    }

    public Dosen getDosen() {
        return this.dosen;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public int getJumlahMahasiswa() {
        int jumlah = 0;
        
        for (Mahasiswa student : daftarMahasiswa) {
            if (student != null) {
                jumlah += 1;
            }
        }

        return jumlah;
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        for (int i = 0; i < daftarMahasiswa.length; i++) {
            if (daftarMahasiswa[i] == null) {
                daftarMahasiswa[i] = mahasiswa;
                break;
            }
        }
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        Mahasiswa[] studentList = new Mahasiswa[this.kapasitas];
        int num = 0;

        // Menambahkan mahasiswa pada array baru jika mahasiswa tersebut tidak melakukan drop mata kuliah 
        for (int j = 0; j < daftarMahasiswa.length; j++) {
            if (daftarMahasiswa[j] != null) {
                if (daftarMahasiswa[j].getNama().equals(mahasiswa.getNama())) {
                    continue;
                }
                else {
                    studentList[num++] = daftarMahasiswa[j];
                }
            }
        }
        this.daftarMahasiswa = studentList;
    }

    public void addDosen(Dosen lecturer) {
        if (this.dosen == null) {
            this.dosen = lecturer;
        }
    }

    public void dropDosen() {
        this.dosen = null;
    }

    public String toString() {
        return this.nama;
    }
}