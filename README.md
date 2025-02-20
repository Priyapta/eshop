# Eshop
### Priyapta Naufal Sudrajat 2306245106
[![Continuous Integration (CI)](https://github.com/Priyapta/eshop/actions/workflows/ci.yml/badge.svg)](https://github.com/Priyapta/eshop/actions/workflows/ci.yml)

## Week 2 Reflection
### Reflection 1
Terkait improvement code quality:
1. Menambah protect Branches dimana saya harus mereview code sebelum di merge ke main dan Menghindari force delete terhadap master branch saya
2. Menambahkan workflow berupa dependency update tool (Dependabot) untuk mendeteksi dependencies yang out-of-date.

Saya telah menerapkan CI/CD menggunakan GitHub Actions dan Koyeb, yang memenuhi definisi Continuous Integration dan Continuous Deployment.

- Continuous Integration: Build dan testing otomatis menggunaka OSSF Scorecard, PMD, dan JaCoCo (Unit Test).
- Continuous Deployment: Deployment otomatis ke Koyeb setiap push ke master, dengan Dockerfile untuk fleksibilitas lingkungan deployment.

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

