package assignments.assignment3;

class Dosen extends ElemenFasilkom {

    private MataKuliah mataKuliah;

    // Constructor
    Dosen(String nama) {
        super(nama);
    }

    public MataKuliah getMatkul() {
        return this.mataKuliah;
    }

    // Menambahkan mata kuliah yang diajar seorang dosen dan menambahkan dosen ke mata kuliah tersebut
    public void mengajarMataKuliah(MataKuliah mataKuliah) {
        if (this.mataKuliah != null) {
            System.out.println("[DITOLAK] " + this.getNama() + " sudah mengajar mata kuliah " + this.mataKuliah);
        }  
        else {
            if (mataKuliah.getDosen() != null) {
                System.out.println("[DITOLAK] " + mataKuliah + " sudah memiliki dosen pengajar");
            }
            else {
                this.mataKuliah = mataKuliah;
                this.mataKuliah.addDosen(this);
                System.out.println(this.getNama() + " mengajar mata kuliah " + mataKuliah);
            }
        }
    }

    public void dropMataKuliah() {
        if (getMatkul() == null) {
            System.out.println("[DITOLAK] " + this.getNama() + " sedang tidak mengajar mata kuliah apapun");
        }  
        else {
            this.mataKuliah.dropDosen();
            System.out.println(this.getNama() + " berhenti mengajar " + this.mataKuliah);
            this.mataKuliah = null;
        }
    }
}