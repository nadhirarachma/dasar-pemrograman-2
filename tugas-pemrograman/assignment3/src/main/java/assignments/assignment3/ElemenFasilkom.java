package assignments.assignment3;

abstract class ElemenFasilkom {

    private String tipe;
    
    private String nama;

    private int friendship;
    
    private int jumlahDisapa;

    private ElemenFasilkom[] telahMenyapa = new ElemenFasilkom[100];

    // Constructor
    ElemenFasilkom(String nama){
        this.nama = nama;
    }

    // Getter untuk attribut elemen fasilkom yang mengembalikan nilai dari attribut 
    public String getTipe() {
        if (this instanceof Mahasiswa) {
            this.tipe = "Mahasiswa";
        }
        else if (this instanceof Dosen) {
            this.tipe = "Dosen";
        }
        else if (this instanceof ElemenKantin) {
            this.tipe = "ElemenKantin";
        }
        return this.tipe;
    }

    public String getNama() {
        return this.nama;
    }

    public int getFriendship() {
        return friendship;
    }
    
    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public ElemenFasilkom[] getDisapa() {
        return telahMenyapa;
    }

    public int getJumlahDisapa() {
        return this.jumlahDisapa;
    }

    // Mengecek matkul sama antara dosen dan mahasiswa
    public boolean matkulSama(ElemenFasilkom disapa) {
        boolean result = false;

        Mahasiswa elemen1 = null;
        Dosen elemen2 = null;
        
        if (this.getTipe().equals("Mahasiswa") && disapa.getTipe().equals("Dosen")) {
            elemen1 = (Mahasiswa) this;
            elemen2 = (Dosen) disapa;
        }
        else if (this.getTipe().equals("Dosen") && disapa.getTipe().equals("Mahasiswa")) {
            elemen1 = (Mahasiswa) disapa;
            elemen2 = (Dosen) this;
        }

        for (MataKuliah matkul : elemen1.getMatkul()) {
            if (matkul != null) {
                if (matkul.equals(elemen2.getMatkul())) {
                    return true;
                }
            }
        }
        
        return result;
    }

    // Menambahkan elemen yang telah disapa ke dalam array telahMenyapa dan kalkulasi nilai friendship
    public void menyapa(ElemenFasilkom elemenFasilkom) {
        for (int i = 0; i < telahMenyapa.length; i++) {
            if (telahMenyapa[i] == null) {
                telahMenyapa[i] = elemenFasilkom;

                this.jumlahDisapa++;
                elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahDisapa++] = this;

                if (this.getTipe().equals("Mahasiswa") && elemenFasilkom.getTipe().equals("Dosen")) {
                    if (this.matkulSama(elemenFasilkom) == true) {
                        this.setFriendship(this.getFriendship() + 2);
                        elemenFasilkom.setFriendship(elemenFasilkom.getFriendship() + 2);
                    }
                }

                System.out.println(nama + " menyapa dengan " + elemenFasilkom);
                break;
            }
            else {
                if (telahMenyapa[i].equals(elemenFasilkom)) {
                    System.out.println("[DITOLAK] " + nama + " telah menyapa " + elemenFasilkom + " hari ini");
                    break;
                }
            }
        }
    }

    public void resetMenyapa() {
        for (int i = 0; i < getJumlahDisapa(); i++) {
            telahMenyapa[i] = null;
        }
        this.jumlahDisapa = 0;
    }

    // Interaksi jual-beli makanan dan kalkulasi nilai friendship
    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
        ElemenKantin seller = (ElemenKantin) penjual;
        boolean dijual = false;

        for (Makanan food : seller.getDaftarMakanan()) {
            if (food != null) {
                if (food.getNama().equals(namaMakanan)){
                    this.setFriendship(this.getFriendship() + 1);
                    penjual.setFriendship(penjual.getFriendship() + 1); 

                    dijual = true;
                    System.out.println(pembeli + " berhasil membeli " + namaMakanan + " seharga " + food.getHarga());
                }
            }
        }

        if (dijual == false) {
            System.out.println("[DITOLAK] " + penjual + " tidak menjual " + namaMakanan);
        }
    }

    public String toString() {
        return this.nama;
    }
}