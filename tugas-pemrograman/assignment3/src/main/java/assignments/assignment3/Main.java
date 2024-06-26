package assignments.assignment3;

import java.util.Scanner;

public class Main {

    private static ElemenFasilkom[] daftarElemenFasilkom = new ElemenFasilkom[100];

    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];

    private static int totalMataKuliah = 0;

    private static int totalElemenFasilkom = 0; 

    // Mengembalikan elemen berdasarkan namanya
    private static ElemenFasilkom getElemen(String objek) {
        for (ElemenFasilkom elemen : daftarElemenFasilkom) {
            if (elemen != null) {
                if (elemen.getNama().equals(objek)) {
                    return elemen;
                }
            }
        }
        return null;
    }

    // Mengembalikan nama mata kuliah berdasarkan namanya
    private static MataKuliah getMatkul(String namaMatkul) {
        for (int i = 0 ; i < getJumlahMatkul(); i++) {
            if (daftarMataKuliah[i].getNama().equals(namaMatkul)) {
                return daftarMataKuliah[i];
            }
        }
        return null;
    }

    private static int getJumlahElemen() {
        return totalElemenFasilkom;
    }

    private static int getJumlahMatkul() {
        return totalMataKuliah;
    }

    private static void addMahasiswa(String nama, long npm) {
        for (int i = 0; i < daftarElemenFasilkom.length; i++) {
            if (daftarElemenFasilkom[i] == null) {
                Mahasiswa mahasiswa = new Mahasiswa(nama, npm);
                daftarElemenFasilkom[i] = mahasiswa;
                totalElemenFasilkom++;

                System.out.println(nama + " berhasil ditambahkan");
                break;
            }
        }
    }

    private static void addDosen(String nama) {
        for (int j = 0; j < daftarElemenFasilkom.length; j++) {
            if (daftarElemenFasilkom[j] == null) {
                Dosen dosen = new Dosen(nama);
                daftarElemenFasilkom[j] = dosen;
                totalElemenFasilkom++;

                System.out.println(nama + " berhasil ditambahkan");
                break;
            }
        }
    }

    private static void addElemenKantin(String nama) {
        for (int k = 0; k < daftarElemenFasilkom.length; k++) {
            if (daftarElemenFasilkom[k] == null) {
                ElemenKantin elemenKantin = new ElemenKantin(nama);
                daftarElemenFasilkom[k] = elemenKantin;
                totalElemenFasilkom++;

                System.out.println(nama + " berhasil ditambahkan");
                break;
            }
        }
    }

    private static void menyapa(String objek1, String objek2) {
        ElemenFasilkom object1 = getElemen(objek1);
        ElemenFasilkom object2 = getElemen(objek2);

        if (objek1.equals(objek2)) {
            System.out.println("[DITOLAK] Objek yang sama tidak bisa saling menyapa"); 
        }
        else {
            object1.menyapa(object2);
        }
    }

    private static void addMakanan(String objek, String namaMakanan, long harga) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("ElemenKantin"))  {
            ElemenKantin elemen = (ElemenKantin) object;
            elemen.setMakanan(namaMakanan, harga);
        }
        else {
            System.out.println("[DITOLAK] " + objek + " bukan merupakan elemen kantin");
        }
    }

    private static void membeliMakanan(String objek1, String objek2, String namaMakanan) {
        ElemenFasilkom pembeli = getElemen(objek1);
        ElemenFasilkom penjual = getElemen(objek2);

        if (pembeli.equals(penjual)) {
            System.out.println("[DITOLAK] Elemen kantin tidak bisa membeli makanan sendiri");
        }
        else if (penjual.getTipe().equals("ElemenKantin")) {
            pembeli.membeliMakanan(pembeli, penjual, namaMakanan);
        }
        else {
            System.out.println("[DITOLAK] Hanya elemen kantin yang dapat menjual makanan");
        }
    }

    private static void createMatkul(String nama, int kapasitas) {
        for (int m = 0; m < daftarMataKuliah.length; m++) {
            if (daftarMataKuliah[m] == null) {
                MataKuliah mataKuliah = new MataKuliah(nama, kapasitas);
                daftarMataKuliah[m] = mataKuliah;
                totalMataKuliah++;

                System.out.println(nama + " berhasil ditambahkan dengan kapasitas " + kapasitas);
                break;
            }
        }
    }

    private static void addMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("Mahasiswa")) {
            Mahasiswa student = (Mahasiswa) object;
            student.addMatkul(getMatkul(namaMataKuliah));
        }
        else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat menambahkan matkul");
        }
    }

    private static void dropMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("Mahasiswa")) {
            Mahasiswa student = (Mahasiswa) object;
            student.dropMatkul(getMatkul(namaMataKuliah));
        }
        else {
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat drop matkul");
        }
    }

    private static void mengajarMatkul(String objek, String namaMataKuliah) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("Dosen")) {
            Dosen dosen = (Dosen) object;
            dosen.mengajarMataKuliah(getMatkul(namaMataKuliah));
        }
        else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat mengajar matkul");
        }
        
    }

    private static void berhentiMengajar(String objek) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("Dosen")) {
            Dosen dosen = (Dosen) object;
            dosen.dropMataKuliah();
        }
        else {
            System.out.println("[DITOLAK] Hanya dosen yang dapat berhenti mengajar");
        }
    }

    private static void ringkasanMahasiswa(String objek) {
        ElemenFasilkom object = getElemen(objek);
        if (object.getTipe().equals("Mahasiswa")) {
            Mahasiswa student = (Mahasiswa) object;

            System.out.println("Nama: " + student.getNama());
            System.out.println("Tanggal lahir: " + student.getTanggalLahir());
            System.out.println("Jurusan: " + student.getJurusan());
            System.out.println("Daftar Mata Kuliah:");

            if (student.getJumlahMatkul() == 0) {
                System.out.println("Belum ada mata kuliah yang diambil");
            }
            
            else {
                // Mencetak daftar mata kuliah yang diambil seorang mahasiswa 
                for (int i = 0; i < student.getJumlahMatkul(); i++) {
                    System.out.println((i+1) + ". " + student.getMatkul()[i]);
                }
            }
        }

        else {
            System.out.println("[DITOLAK] " + objek + " bukan merupakan seorang mahasiswa");
        }
    }

    private static void ringkasanMataKuliah(String namaMataKuliah) {
        MataKuliah namaMatkul = getMatkul(namaMataKuliah);
        String namaDosen = "";

        if (namaMatkul.getDosen() == null) {
            namaDosen = "Belum ada";
        }
        else {
            namaDosen = namaMatkul.getDosen().getNama();
        }

        System.out.println("Nama mata kuliah: " + namaMataKuliah);
        System.out.println("Jumlah mahasiswa: " + namaMatkul.getJumlahMahasiswa());
        System.out.println("Kapasitas: " + namaMatkul.getKapasitas());
        System.out.println("Dosen pengajar: " + namaDosen);
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini:");

        if (namaMatkul.getJumlahMahasiswa() == 0) {
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
        }

        else {
            // Mencetak daftar mahasiswa yang mengambil suatu mata kuliah
            for (int i = 0; i < namaMatkul.getJumlahMahasiswa(); i++) {
            System.out.println((i+1) + ". " + namaMatkul.getDaftarMahasiswa()[i]);
            }
        }
    }

    private static void nextDay() {
        for (int i = 0; i < getJumlahElemen(); i++) {
            ElemenFasilkom elemen = daftarElemenFasilkom[i];
            
            // Jika suatu objek telah menyapa lebih dari atau sama dengan setengah dari total elemenFasilkom
            if (elemen.getJumlahDisapa() >= ((getJumlahElemen()-1.0) / 2)) {
                elemen.setFriendship(elemen.getFriendship() + 10);
            }
            else {
                elemen.setFriendship(elemen.getFriendship() - 5);
            }
           
            if (elemen.getFriendship() < 0) {
                elemen.setFriendship(0);
            }
            else if (elemen.getFriendship() > 100) {
                elemen.setFriendship(100);
            }

            elemen.resetMenyapa();
        }
        System.out.println("Hari telah berakhir dan nilai friendship telah diupdate");
        friendshipRanking();
    }

    private static void friendshipRanking() {
        ElemenFasilkom[] ranking = new ElemenFasilkom[getJumlahElemen()];
        ElemenFasilkom elemen = null;

        for (int m = 0; m < getJumlahElemen(); m++) {
            ranking[m] = daftarElemenFasilkom[m];
        }

        // Mengurutkan berdasarkan nama secara alphabetical
        for (int i = 0; i < ranking.length; i++) {
            for (int j = i+1; j < ranking.length; j++) {
                if (ranking[i].getFriendship() < ranking[j].getFriendship()) {
                    elemen = ranking[i];
                    ranking[i] = ranking[j];
                    ranking[j] = elemen;
                }
                else if (ranking[i].getFriendship() == ranking[j].getFriendship()) {
                    if (ranking[i].getNama().compareTo(ranking[j].getNama()) > 0) {
                        elemen = ranking[i];
                        ranking[i] = ranking[j];
                        ranking[j] = elemen;
                    }
                }
            }
        }

        for (int k = 0; k < ranking.length; k++) {
            if (ranking[k] != null) {
                System.out.println((k+1) + ". " + ranking[k] + "(" + ranking[k].getFriendship() + ")");
            }
        }
    }

    private static void programEnd() {
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            String in = input.nextLine();
            if (in.split(" ")[0].equals("ADD_MAHASISWA")) {
                addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_DOSEN")) {
                addDosen(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("ADD_ELEMEN_KANTIN")) {
                addElemenKantin(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("MENYAPA")) {
                menyapa(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("ADD_MAKANAN")) {
                addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
            } else if (in.split(" ")[0].equals("MEMBELI_MAKANAN")) {
                membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
            } else if (in.split(" ")[0].equals("CREATE_MATKUL")) {
                createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_MATKUL")) {
                addMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("DROP_MATKUL")) {
                dropMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("MENGAJAR_MATKUL")) {
                mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("BERHENTI_MENGAJAR")) {
                berhentiMengajar(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MAHASISWA")) {
                ringkasanMahasiswa(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MATKUL")) {
                ringkasanMataKuliah(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("NEXT_DAY")) {
                nextDay();
            } else if (in.split(" ")[0].equals("PROGRAM_END")) {
                programEnd();
                break;
            }
        }
        input.close();
    }
}