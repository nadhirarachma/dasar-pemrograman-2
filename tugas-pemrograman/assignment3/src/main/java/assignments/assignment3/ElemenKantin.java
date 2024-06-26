package assignments.assignment3;

class ElemenKantin extends ElemenFasilkom {
    
    private Makanan[] daftarMakanan = new Makanan[10];

    // Constructor
    ElemenKantin(String nama) {
        super(nama);
    }

    public Makanan[] getDaftarMakanan() {
        return this.daftarMakanan;
    }

    // Menambahkan makanan ke dalam daftarMakanan yang dijual seorang elemen kantin
    public void setMakanan(String nama, long harga) {
        Makanan food = new Makanan(nama, harga);

        for (int i = 0; i < daftarMakanan.length; i++) {
            if (daftarMakanan[i] == null) {
                daftarMakanan[i] = food;
                System.out.println(this.getNama() + " telah mendaftarkan makanan " + food.getNama() + " dengan harga " + food.getHarga());
                break;
            }

            else {
                if (daftarMakanan[i].getNama().equals(food.getNama())) {
                    System.out.println("[DITOLAK] " + food.getNama() + " sudah pernah terdaftar");
                    break;
                }
            }
        }
    }
}