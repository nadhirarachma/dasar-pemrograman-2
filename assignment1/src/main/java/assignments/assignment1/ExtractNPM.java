package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    
    // Method yang mengembalikan tahun masuk
    public static String getTahunMasuk(String tahunMasuk) {
        return "20" + tahunMasuk;
    }

    // Method yang mengembalikan nama jurusan, apabila kode jurusan ada dalam daftar jurusan, dikembalikan nama jurusannya, dan jika tidak akan mengembalikan "NPM tidak valid"
    public static String getJurusan(String kodeJurusan) {
        if (kodeJurusan.equals("01")) {
            return "Ilmu Komputer";
        }
        else if (kodeJurusan.equals("02")) {
            return "Sistem Informasi";
        }
        else if (kodeJurusan.equals("03")) {
            return "Teknologi Informasi";
        }
        else if (kodeJurusan.equals("04")) {
            return "Teknik Telekomunikasi";
        }
        else if (kodeJurusan.equals("05")) {
            return "Teknik Elektro";
        }
        else {
            return "NPM tidak valid!";
        }
    }

    // Method yang mengembalikan tanggal lahir
    public static String getTanggalLahir(String tanggalLahir) {
        return tanggalLahir.substring(0, 2) + "-" + tanggalLahir.substring(2, 4) + "-" + tanggalLahir.substring(4);
    }

    // Method yang mengembalikan umur, dimana tahun masuk dikurangi dengan tahun lahir
    public static String getUmur(String umur) {
        int usia = Integer.parseInt(getTahunMasuk(umur.substring(0,2))) - Integer.parseInt((getTanggalLahir(umur.substring(4,12))).substring(6,10));
        return Integer.toString(usia);
    }

    // Method yang mengembalikan kode NPM untuk validasi
    public static String getKodeNPM(String kode) {
        int sum = 0;

        // Menambahkan angka sesuai instruksi, dimana digit 1 dikali digit 13, 2 dengan 12, 3 dengan 11, dan seterusnya
        for (int i = 0, j = 12; i < 6 && j > 6; i++, j--) {
            sum += ((Integer.parseInt(kode.substring(i,i+1))) * (Integer.parseInt(kode.substring(j,j+1))));
        }

        // Menambahkan digit ke 6 pada hasil perhitungan
        sum += Integer.parseInt(kode.substring(6,7));
        String newSum = Integer.toString(sum);

        int num = Integer.parseInt(newSum.substring(0,1));
        int a = 1;

        // Jika hasilnya masih >= 10 atau lebih dari 1 digit, hasil tersebut akan terus ditambahkan hingga menghasilkan 1 digit angka
        while (newSum.length() > 1) {
            num += Integer.parseInt(newSum.substring(a,a+1));
            a++;
            newSum = Integer.toString(num);
        }

        return Integer.toString(num);
    }

    // Method yang mengembalikan hasil validasi dari npm
    public static boolean validate(long npm) {
        boolean valid = true;

        String nomerNpm = Long.toString(npm);
        String kodeJurusan = nomerNpm.substring(2,4);
        String daftarJurusan = "0102030405";

        if (nomerNpm.length() < 14) {
            valid = false;
        }

        else {
            if (daftarJurusan.contains(kodeJurusan)) {
                valid = true;

                if (Integer.parseInt(getUmur(nomerNpm)) < 15) {
                    valid = false;
                } 

                else {
        
                    if (nomerNpm.endsWith(getKodeNPM(nomerNpm))) {
                        valid = true;
                    }

                    else {
                        valid = false;
                    }
                }
            }
            
            else {
                valid = false;
            }
        }

        return valid;
    }

    // Method yang mengembalikan hasil dari ekstraksi npm
    public static String extract(long npm) {
        String nomorNpm = Long.toString(npm);

        // Melakukan ekstraksi dengan memanggil masing-masing method 
        String tahunMasuk = getTahunMasuk(nomorNpm.substring(0,2));
        String jurusan = getJurusan(nomorNpm.substring(2,4));
        String tanggalLahir = getTanggalLahir(nomorNpm.substring(4,12));

        return "Tahun masuk: " + tahunMasuk + "\nJurusan: " + jurusan + "\nTanggal Lahir: " + tanggalLahir;
    }

    public static void main(String args[]) {
        // Proses input dari user
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true;
                break;
            }

            else {

                if (validate(npm) == false) {
                    System.out.println("NPM tidak valid!");
                    break;
                }

                else {
                    System.out.print(extract(npm));
                    break;
                }
            }
        }
        
        input.close(); 
    }
}