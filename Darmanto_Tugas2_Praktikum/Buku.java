public class Buku {
    private String judul;
    private String penulis;
    private String kategori;
    private boolean tersedia;

    // konstruktor = untuk menginisialisasi objek buku
    // konstruktor ini digunakan untuk membuat objek buku baru
    public Buku(String judul, String penulis, String kategori, boolean tersedia) {
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.tersedia = tersedia;
    }

    // getter dan setter = untuk mwngakses dan mengubah buku dari luar kelas
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
