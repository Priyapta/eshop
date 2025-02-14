# Eshop
### Priyapta Naufal Sudrajat 2306245106

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
