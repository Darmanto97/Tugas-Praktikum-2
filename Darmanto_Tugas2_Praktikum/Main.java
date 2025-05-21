import java.util.ArrayList; 
import java.util.Scanner;

public class Main {
    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static ArrayList<Buku> bukuDipinjam = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menambahkan buku awal
        daftarBuku.add(new Buku("Algoritma dan Pemrograman", "John Doe", "Teknologi", true));
        daftarBuku.add(new Buku("Sejarah Dunia", "Jane Smith", "Sejarah", true));
        daftarBuku.add(new Buku("Fiksi Fantasi", "Alice Johnson", "Fiksi", true));
        daftarBuku.add(new Buku("Non-Fiksi Inspiratif", "Bob Brown", "Non-Fiksi", true));
        daftarBuku.add(new Buku("Pemrograman Java", "Charlie White", "Teknologi", true));
       
        // Menu utama
        while (true) {
            System.out.println("Menu Utama:");
            System.out.println("1. Input Data Buku");
            System.out.println("2. Pencarian Buku");
            System.out.println("3. Peminjaman Buku");
            System.out.println("4. Pengembalian Buku");
            System.out.println("5. Manajemen Data Buku");
            System.out.println("6. Tampilkan Daftar Buku");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1 -> inputDataBuku();
                case 2 -> pencarianBuku();
                case 3 -> peminjamanBuku();
                case 4 -> pengembalianBuku();
                case 5 -> manajemenDataBuku();
                case 6 -> tampilkanDaftarBuku();
                case 7 -> {
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    static void inputDataBuku() {// Menambahkan buku baru
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        String penulis = scanner.nextLine();
        System.out.print("Masukkan kategori buku: ");
        String kategori = scanner.nextLine();
        daftarBuku.add(new Buku(judul, penulis, kategori, true));
        System.out.println("Buku berhasil ditambahkan.");
    }

    static void tampilkanDaftarBuku() {// Menampilkan daftar buku
        System.out.println("Daftar Buku:");
        for (Buku buku : daftarBuku) {
            System.out.println("Judul: " + buku.getJudul() + ", Penulis: " + buku.getPenulis() + ", Kategori: " + buku.getKategori() + ", Tersedia: " + (buku.isTersedia() ? "Ya" : "Tidak"));
        }
    }

    static void pencarianBuku() {// Mencari buku berdasarkan judul
        System.out.print("Masukkan judul buku yang ingin dicari: ");
        String judul = scanner.nextLine();
        Buku buku = cariBuku(judul);
        if (buku != null) {
            System.out.println("Buku ditemukan:");
            System.out.println("Judul: " + buku.getJudul());
            System.out.println("Penulis: " + buku.getPenulis());
            System.out.println("Kategori: " + buku.getKategori());
            System.out.println("Tersedia: " + (buku.isTersedia() ? "Ya" : "Tidak"));
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    static void peminjamanBuku() {// Meminjam buku
        while (true) {
            System.out.print("Masukkan judul buku yang ingin dipinjam (ketik 'selesai' untuk mengakhiri): ");
            String judul = scanner.nextLine();
            if (judul.equalsIgnoreCase("selesai")) {
                break;
            }
            Buku buku = cariBuku(judul);
            if (buku != null && buku.isTersedia()) {
                buku.setTersedia(false);
                bukuDipinjam.add(buku);
                System.out.println("Buku berhasil dipinjam, selamat membaca.");
            } else {
                System.out.println("Buku tidak tersedia atau tidak ditemukan. Silakan coba lagi.");
            }
        }
        cetakStrukPeminjaman();
    }

    static void pengembalianBuku() {// Mengembalikan buku
        System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
        String judul = scanner.nextLine();
        Buku buku = cariBuku(judul);
        if (buku != null && !buku.isTersedia()) {
            buku.setTersedia(true);
            bukuDipinjam.remove(buku);
            System.out.println("Buku berhasil dikembalikan, terimakasih.");
            System.out.print("Masukkan jumlah hari keterlambatan: ");
            int hariKeterlambatan = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (hariKeterlambatan > 7) {
                int denda = (hariKeterlambatan - 7) * 5000;
                System.out.println("Total denda: Rp. " + denda);
            }
        } else {
            System.out.println("Buku tidak ditemukan dalam daftar peminjaman. Silakan coba lagi.");
        }
        cetakStrukPengembalian();
    }

    static void manajemenDataBuku() {// Manajemen data buku
        System.out.println("Manajemen Data Buku:");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Ubah Buku");
        System.out.println("3. Hapus Buku");
        System.out.println("4. Kembali ke Menu Utama");
        System.out.print("Pilih menu: ");
        
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline = untuk menghindari error

        switch (pilihan) {// Pilihan menu manajemen data buku
            case 1 -> inputDataBuku();
            case 2 -> ubahBuku();
            case 3 -> hapusBuku();
            default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    static void ubahBuku() {// Mengubah data buku
        System.out.print("Masukkan judul buku yang ingin diubah: ");
        String judul = scanner.nextLine();
        Buku buku = cariBuku(judul);
        if (buku != null) {
            System.out.print("Masukkan judul baru: ");
            buku.setJudul(scanner.nextLine());
            System.out.print("Masukkan penulis baru: ");
            buku.setPenulis(scanner.nextLine());
            System.out.print("Masukkan kategori baru: ");
            buku.setKategori(scanner.nextLine());
            System.out.println("Buku berhasil diubah.");
        } else {
            System.out.println("Buku tidak ditemukan. Silakan coba lagi.");
        }
    }

    static void hapusBuku() {// Menghapus buku
        System.out.print("Masukkan judul buku yang ingin dihapus: ");
        String judul = scanner.nextLine();
        Buku buku = cariBuku(judul);
        if (buku != null) {
            daftarBuku.remove(buku);
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Buku tidak ditemukan. Silakan coba lagi.");
        }
    }

    static Buku cariBuku(String judul) {// Mencari buku berdasarkan judul
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        return null;
    }

    static void cetakStrukPeminjaman() {// Mencetak struk peminjaman
        System.out.println("===================================");
        System.out.println("Struk Peminjaman:");
        for (Buku buku : bukuDipinjam) {
            System.out.println("Buku: " + buku.getJudul() + " | Status: Dipinjam");
        }
    }

    static void cetakStrukPengembalian() {// Mencetak struk pengembalian
        System.out.println("===================================");
        System.out.println("Struk Pengembalian:");
        for (Buku buku : daftarBuku) {
            if (buku.isTersedia()) {
                System.out.println("Buku: " + buku.getJudul() + " | Status: Tersedia");
            }
        }
    }
}