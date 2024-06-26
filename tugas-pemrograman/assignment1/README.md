# Tugas Pemrograman 1: NPM Extractor

CSGE601021 Dasar-Dasar Pemrograman 2 @ Fakultas Ilmu Komputer Universitas Indonesia,
Semester Genap 2020/2021

Pada Tugas Pemrograman 1, anda diminta untuk melakukan validasi dan ekstraksi informasi terhadap Nomor Panggilan Mahasiswa (NPM)

Tugas Pemrograman ini mencakup materi berikut:

- Pemrosesan I/O dasar (memproses *standard input* dan *standard output*)
- Percabangan dan perulangan
- *Methods*
- Manipulasi *string*

## Daftar Isi

1. [Latar belakang dan deskripsi](#latar-belakang-dan-deskripsi)
2. [Persiapan](#persiapan)
3. [Menjalankan dan menguji program](#menjalankan-dan-menguji-program)
4. [Pengumpulan](#pengumpulan)
5. [Daftar pekerjaan](#daftar-pekerjaan)
   1. [Daftar pekerjaan wajib](#daftar-pekerjaan-wajib)
6. [Penilaian](#penilaian)


## Latar belakang dan deskripsi

Silakan baca [dokumen soal][dokumen tp1].

## Persiapan

Silakan ikuti persiapan yang ada pada berkas [`README.md`][root-readme] di
*root* repositori ini.

> Template pengerjaan tugas ini disediakan di *path* 
`src\main\java\assignments\assignment1` 

## Menjalankan dan menguji program

> Catatan:<br> 
> - Ubah `gradlew.bat` dengan `./gradlew` dan `\` dengan `/` jika kamu
menggunakan Linux/Mac.
> - Tanda <kbd>></kbd> pada awal *command* tidak perlu ditulis. 
Digunakan sebagai pembeda antara *command* dan *output*-nya

Kamu bisa menjalankan beberapa *task* dengan Gradle melalui IDE atau
terminal/Command Prompt.
Jalankan *task* tersebut di root folder dari repository ini.

Untuk menjalankan program:

```bash
> gradlew.bat :assignment1:run --console plain
```

Untuk menguji program dengan *test case* yang disediakan:

```bash
> gradlew.bat :assignment1:test
```

> Tips: kamu bisa memeriksa hasil tes dalam bentuk dokumen HTML. Lihat dokumen
> HTML tersebut di dalam folder `build\reports` yang ada di direktori ini.

Jika kamu tidak ingin menggunakan Gradle, kamu juga bisa melakukan kompilasi
dan menjalankan program dengan `javac` dan `java` seperti biasa. Masuk ke
direktori `src\main\java`, *compile* semua berkas `.java` kamu, dan jalankan
*main class*-nya.

```bash
> cd src\main\java
src\main\java> javac assignments\assignment1\*.java
src\main\java> java assignments.assignment1.ExtractNPM
```

> Catatan: <kbd>src\main\java></kbd> tidak perlu ditulis saat menjalankan *command*-nya (hanya menandakan bahwa anda sudah berada di folder src\main\java)

Kamu juga bisa memeriksa kebenaran program kamu dengan membandingkan nilai
*return* dari *method* `validate` dan `extract` dengan nilai *return* yang
diharapkan. Terdapat beberapa *test case* yang bisa kamu lihat di dalam
berkas `src\test\java\assignments\assignment1\ExtractNPMTest.java`.

Kode seperti berikut:

```java
assertEquals(true, ExtractNPM.validate(20022808200017L));
```

artinya jika *method* `validate` kamu dipanggil dengan argumen `"20022808200017L"`,
maka ia harus mengembalikan nilai yang setara dengan `true`.

## Pengumpulan

Simpan pekerjaan kamu, lalu lakukan `git add`, `git commit`, dan `git push`
sebagaimana dijelaskan pada dokumen
Getting Started with a Java Project.
*Push* solusi kamu **sebelum 19 Maret 2021 pukul 23.55**. Disarankan untuk
*push* **sebelum pukul 22.00** untuk mengantisipasi antrean pada GitLab
Pipelines.

Jangan lupa untuk menambahkan asdos kamu sebagai *member* di repositori kamu.
Tim pengajar hanya akan menilai pekerjaan kamu berdasarkan ***commit* terbaru**
yang **sudah di-*push* sebelum waktu dan tanggal yang ditentukan**. *Commit*
tersebut diidentifikasi dengan memeriksa GitLab Pipelines.

> Catatan: *timestamp* pada GitLab Pipelines tidak pernah bohong.
> *So, no funny business, please :)*

## Daftar pekerjaan

### Daftar pekerjaan wajib

- [ ] Buat sekurang-kurangnya 1 *commit* yang mengandung progres kamu dalam
      mengerjakan Tugas Pemrograman 1.
- [ ] *Push* *commit* kamu ke repositori Git *online* yang ada pada proyek
      GitLab kamu.
- [ ] Implementasikan method `validate` dengan benar.
- [ ] Implementasikan method `extract` dengan benar.
- [ ] Implementasikan TODO pada method `main` agar sesuai dengan 
      output yang diinginkan
- [ ] Demonstrasikan program kamu ke asdos kamu.

## Penilaian

- 50% kebenaran program
- 40% demonstransi program
- 10% kerapian dan dokumentasi

[dokumen tp1]: https://docs.google.com/document/d/1SdYI3sFAKsCmHF4Ktt4IVgQma0xKNI_WOvQmavxsCrg/export?format=pdf&attachment=false
[root-readme]: ../README.md#memulai