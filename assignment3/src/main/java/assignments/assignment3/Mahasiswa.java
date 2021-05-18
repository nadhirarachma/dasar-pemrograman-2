package assignments.assignment3;

class Mahasiswa extends ElemenFasilkom {

    private MataKuliah[] daftarMataKuliah = new MataKuliah[10];
    
    private long npm;

    private String tanggalLahir;
    
    private String jurusan;

    // Constructor
    Mahasiswa(String nama, long npm) {
        super(nama);
        this.npm = npm;
    }

    // Getter untuk attribut mahasiswa yang mengembalikan nilai dari attribut 
    public long getNPM() {
        return this.npm;
    }

    public String getTanggalLahir() {
        return extractTanggalLahir(npm);
    }

    public String getJurusan() {
        return extractJurusan(npm);
    }

    public MataKuliah[] getMatkul() {
        return this.daftarMataKuliah;
    }

    public int getJumlahMatkul() {
        int jumlah = 0;

        for (MataKuliah matkul : daftarMataKuliah) {
            if (matkul != null) {
                jumlah += 1;
            }
        }

        return jumlah;
    }

    // Menambahkan mata kuliah ke daftar mata kuliah yang diambil saat ini dan menambahkan mahasiswa ke daftar mahasiswa yang mengambil mata kuliah tersebut
    public void addMatkul(MataKuliah mataKuliah) {
        for (int i = 0; i < daftarMataKuliah.length; i++) {
            if (daftarMataKuliah[i] == null) {
                if (mataKuliah.getKapasitas() <= mataKuliah.getJumlahMahasiswa()) {
                    System.out.println("[DITOLAK] " + mataKuliah + " telah penuh kapasitasnya");
                    break;
                }
                
                // Menambahkan mata kuliah ke daftar mata kuliah yang diambil saat ini dan menambahkan mahasiswa ke daftar mahasiswa yang mengambil mata kuliah tersebut
                else {
                    daftarMataKuliah[i] = mataKuliah;
                    daftarMataKuliah[i].addMahasiswa(this);
                    System.out.println(this.getNama() + " berhasil menambahkan mata kuliah " + mataKuliah);
                    break;
                }
            }
            else {
                if (daftarMataKuliah[i].getNama().equals(mataKuliah.getNama())) {
                    System.out.println("[DITOLAK] " + mataKuliah + " telah diambil sebelumnya");
                    break;
                }
            }
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        MataKuliah[] daftarMatkul = new MataKuliah[this.daftarMataKuliah.length];
        boolean ada = false;
        int num = 0;

        // Menambahkan mata kuliah pada array baru jika mata kuliah tersebut bukan merupakan mata kuliah yang akan di drop dan melakukan drop mahasiswa
        for (int j = 0; j < daftarMataKuliah.length; j++) {
            if (daftarMataKuliah[j] != null) {
                if (daftarMataKuliah[j].equals(mataKuliah)) {
                    ada = true;
                    daftarMataKuliah[j].dropMahasiswa(this);
                    continue;
                }
                else {
                    daftarMatkul[num++] = daftarMataKuliah[j];
                    break;
                }
            }   
        }

        this.daftarMataKuliah = daftarMatkul;

        if (ada == false) {
            System.out.println("[DITOLAK] " + mataKuliah + " belum pernah diambil");
        }
        else {
            System.out.println(this.getNama() + " berhasil drop mata kuliah " +  mataKuliah);
        }
    }

    public String extractTanggalLahir(long npm) {
        String birthday = Long.toString(npm).substring(4,12);
        String date = birthday.substring(0,2);
        String month = birthday.substring(2,4);
        String year = birthday.substring(4);

        if (birthday.substring(0,1).equals("0") && birthday.substring(2,3).equals("0")) {
            date = birthday.substring(0,2).replace("0","");
            month = birthday.substring(2,4).replace("0","");
        }
        else if (birthday.substring(0,1).equals("0")) {
            date = birthday.substring(0,2).replace("0","");
        }
        else if (birthday.substring(2,3).equals("0")) {
            month = birthday.substring(2,4).replace("0","");
        }

        this.tanggalLahir = date + "-" + month + "-" + year;
        return this.tanggalLahir;
    }

    public String extractJurusan(long npm) {
        String kodeJurusan = Long.toString(npm).substring(2,4);
        if (kodeJurusan.equals("01")) {
            this.jurusan = "Ilmu Komputer";
        }
        else {
            this.jurusan = "Sistem Informasi";
        }
        return this.jurusan;
    }
}