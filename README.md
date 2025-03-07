# Eshop
### Priyapta Naufal Sudrajat 2306245106
[![Continuous Integration (CI)](https://github.com/Priyapta/eshop/actions/workflows/ci.yml/badge.svg)](https://github.com/Priyapta/eshop/actions/workflows/ci.yml)

## Week 4 Reflection
### PReflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.
TDD sangat membantu dalam menemukan bug sejak awal dan memastikan code berfungsi sesuai harapan. Namun, cakupan pengujian masih terbatas, terutama untuk edge cases dan kondisi ekstrem. Saat ini, pengujian hanya berfokus pada unit test, sehingga perlu ditambahkan pengujian integrasi untuk memastikan komponen bekerja sama dengan baik.

Dari segi maintainability, code pengujian cukup mudah dipahami, tetapi perlu lebih banyak dokumentasi dan mungkin refactoring agar tetap bersih dan terorganisir. Dengan memperluas cakupan, menambahkan pengujian integrasi, dan meningkatkan maintainability, pengujian akan lebih efektif dan bernilai bagi proyek ke depan.

### You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.
Sebagian besar code saya sudah memenuhi prinsip F.I.R.S.T., tetapi ada beberapa hal yang perlu ditingkatkan. Pengujian berjalan cepat (Fast) dan hasilnya konsisten (Repeatable, Self-Validating). Namun, beberapa pengujian masih bergantung pada data di @BeforeEach, sehingga perlu dibuat lebih independen (Independent).

Selain itu, saya perlu menambahkan lebih banyak pengujian untuk edge cases agar cakupannya lebih luas. Dengan memastikan pengujian lebih mandiri dan mencakup kondisi ekstrem, saya yakin kualitas dan keandalan code akan meningkat.
## Draft
<details>
<summary><strong>Tutorial 1</strong></summary>
  
## Reflection 1

- Untuk membuat sebuah clean code tentu harus teliti dalam membaca code dan melihat ulang ke codingan apakah dari penamaan fungsi dan variabel sudah benar.
Biasanya yang sangat terlihat dalam memakai  function karena dengan penggunaan function terlihat jelas apakah nama dari function kita sudah benar atau belum dalam memenuhi aturan clean code

## Reflection 2

- Yang saya rasakan cukup frustasi dalam membuat uni test dan cukup senang karena tidak ada erorr dalam menjalankan unit test
- Dalam membuat unit test tidak ada batasan mungkin hanya membuat edge case saja sehingga program dapat berjalan di beberapa kondisi berbeda
- Dalam mememastikan apakah unit test sudah cukup dengan kode coverage dapat melihat bagian kode mana yang telah diuji,selain itu
  dapat testing edge case untuk memperkirakan kasus yang tidak biasa atau ekstrem dalam kondisi tertentu
### Apakah 100% code coverage berarti tidak ada bug 
- tidak meskipun kode coverage 100% masih ada skenario yang dapat menyebabkan bug mungkin di unit test tidak mencakup semua skenario error
  dan kemungkinan ada bug saat integrasi dengan komponen lain sehingga masih ada faktor external yang lain. Untuk memastikan tidak ada error harus dipastikan apakah unit test yang diberikan sudah mencakup edge case dan test integrasi yang diperlukan

### After writing the CreateProductFunctionalTest.java

- Mengulang kode yang sama dalam functional test dapat menurunkan kualitas kode dengan meningkatkan redundansi, mengurangi keterbacaan, serta memperumit pemeliharaan. Selain itu, hal ini juga melanggar prinsip Don't Repeat Yourself (DRY). Untuk menjaga kebersihan kode, kita dapat menggunakan pendekatan seperti membuat superclass abstrak yang menangani setup umum dalam pengujian. Dengan cara ini, duplikasi kode dapat dikurangi sehingga kode tetap terstruktur, mudah dibaca, dan lebih mudah dipelihara.

</details>

<details> 

<summary><strong>Tutorial 2</strong></summary>

## Week 2 Reflection
### Reflection 1

Terkait improvement code quality:
1. Menambah protect Branches dimana saya harus mereview code sebelum di merge ke main dan Menghindari force delete terhadap master branch saya
2. Menambahkan workflow berupa dependency update tool (Dependabot) untuk mendeteksi dependencies yang out-of-date.

Saya telah menerapkan CI/CD menggunakan GitHub Actions dan Koyeb, yang memenuhi definisi Continuous Integration dan Continuous Deployment.

- Continuous Integration: Build dan testing otomatis menggunaka OSSF Scorecard, PMD, dan JaCoCo (Unit Test).
- Continuous Deployment: Deployment otomatis ke Koyeb setiap push ke master, dengan Dockerfile untuk fleksibilitas lingkungan deployment.


</details>
<details>
<summary><strong>Tutorial 3</strong></summary>

## Week 3 Reflection
### Principles Applied to the Project
1. Single Responsibility Principle
    - Dalam mengimplementasi aturan ini dilihat dari controller bahwa class controller sudah memakai aturan ini
2. Open Closed Principle
    - Menggunakan antar muka `CarService` yang diimplemetasikan terhadap `CarServiceimpl`
3. Liskov Subtitution
    - Subkelas dapat menggantikan superclass mereka tanpa mengganggu fungsionalitas program. Misalnya, setiap implementasi `CarService` dapat digunakan di mana saja `CarService` diperlukan tanpa mempengaruhi logika aplikasi.
    - Memastikan bahwa setiap implementasi `CarService` dapat digunakan secara  tanpa mempengaruhi logika aplikasi. Contohnya, `CarServiceImpl` yang dapat menginherit function dari sebuah interface `CarService`
4. Interface Segregation Principle (ISP)
    - Menghindari dependensi yang tidak perlu dengan memastikan hanya kelas tertentu bergantun pada interface yang dibutuhkan. Contoh pada `CarServiceimpl` dimana hanya method-method tertentu yang diimplementasikan relevan terhadap kelas ini.
5. Dependency Inversion Principle (DIP)
    - Mengubah instance langsung dari `CarServiceImpl` dalam controller dengan interface `CarService`. Dengan cara ini maka hanya bergantung pada abstraksi bukan implementasi spesifik sehingga lebih mudah dalam pengetesan.

### Explain the advantages of applying SOLID principles to your project with examples
1. Maintainability yang Lebih Baik : Dengan SRP, kode lebih bersih dan terorganisir. Setiap perubahan hanya perlu dilakukan di satu tempat tanpa mengganggu bagian lain.
2. Kemudahan dalam Pengembangan dan Ekstensi : OCP memastikan bahwa fitur baru dapat ditambahkan tanpa merusak kode lama. Jika saya ingin menambahkan model atau fitur baru, saya cukup membuat subclass atau meng-extend yang sudah ada.
3. Pewarisan yang Lebih Aman dan Stabil : LSP memastikan bahwa subclass bisa menggantikan superclass tanpa masalah. Ini mencegah bug akibat perilaku yang tidak sesuai ketika pewarisan digunakan.
### Explain the disadvantages of not applying SOLID principles to your project with examples
1. Kode Sulit dipelihara: Dimana jika menggunakan SOLID kode lebih terstuktur dan lebih rapih dan tentu mudah dibaca yang mana jika bekerja pada proyek yang besar dimenyulitkan programmer dalam membaca kode mereka ataupun kode orang lain
2. Tanpa prinsip seperti Open/Closed Principle (terbuka untuk ekstensi, tertutup untuk modifikasi), penambahan fitur baru menjadi lebih sulit dan berisiko. Setiap perubahan sering kali memerlukan modifikasi langsung pada kode yang sudah ada, yang dapat mengganggu stabilitas sistem dan meningkatkan kemungkinan munculnya bug.
3. Perubahan Kecil dalam menyebabkan error di banyak tempat dimananya sedikitnya penggunaan if else condition sehingga dapat mengurangi kesalahan pada logic.
</details>

