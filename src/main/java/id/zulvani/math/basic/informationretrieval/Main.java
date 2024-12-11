package id.zulvani.math.basic.informationretrieval;

import id.zulvani.math.basic.informationretrieval.model.Document;

public class Main {

    public Main(){

        Document[] documents = new Document[3];
        documents[0] = new Document("1", "Pemerintah melalui Menteri Hukum dan Hak Asasi Manusia atau Menkumham Yasonna H Laoly memperluas pembatasan terhadap warga negara asing alias WNA yang boleh masuk wilayah Indonesia. Terutama, melarang pekerja asing. Kebijakan ini termaktub dalam Peraturan Menteri Hukum dan HAM atau Permenkumham Nomor 27 Tahun 2021. Rincinya, mengatur pembatasan orang asing masuk ke wilayah Indonesia dalam masa Pemberlakuan Pembatasan Kegiatan Masyarakat atau PPKM Darurat. Permenkumham ini berlaku pada Rabu 21 Juli 2021 dengan 2 hari masa tenggang untuk sosialisasi dan koordinasi. Namun, ada 5 kategori WNA yang masuk daftar pengecualian. Apa saja kategori WNA yang masih bisa masuk wilayah Indonesia? Simak dalam Infografis berikut ini:");
        documents[1] = new Document("2", "Bulu Tangkis menjadi andalan Indonesia berburu medali di Olimpiade Tokyo 2020. Cabang olahraga atau cabor ini digelar pada 24 Juli hingga 2 Agustus 2021. Tidak tanggung-tanggung, dari 28 atlet Indonesia yang bertarung di Negeri Sakura, 11 di antaranya adalah atlet bulu tangkis. Mereka mencoba mempertahankan tradisi medali emas di Olimpiade. Tercatat Indonesia telah menggondol 7 medali emas sejak cabor bulu tangkis dipertandingkan pada Olimpiade Barcelona 1992. Bermula dari tunggal putri Susy Susanti dan tunggal putra Alan Budikusuma. Laga bulu tangkis Indonesia di Olimpiade Tokyo 2020 disiarkan Emtek Group sebagai official broadcaster. Simak jadwalnya dalam Infografis berikut ini:");
        documents[2] = new Document("3", "Covid-19 tidak mengenal usia dan status. Siapa pun bisa tertular. Jadi jangan bebal, sebab tidak ada yang kebal dari virus corona. Bahkan tidak ada jaminan orang yang sudah terinfeksi Covid-19, tidak akan terinfeksi lagi. Mereka bisa kembali terinfeksi Covid-19. Kesadaran melindungi diri sangat penting. Apalagi dengan melindungi diri, juga sekaligus melindungi keluarga, orang terdekat, dan orang sekitar. Ini menjadi tanggung jawab bersama. Disiplin protokol kesehatan menjadi cara yang paling efektif melindungi diri. Apa saja? Simak Infografis berikut ini: ** #IngatPesanIbu Pakai Masker, Cuci Tangan Pakai Sabun, Jaga Jarak dan Hindari Kerumunan. Selalu Jaga Kesehatan, Jangan Sampai Tertular dan Jaga Keluarga Kita.");

        String query = "olahraga indonesia";
        MatrixProcessor im = new MatrixProcessor(documents, query);
        im.toIncidentMatrix();
        System.out.println("----");
        im.toCountMatrix(false);
        System.out.println("----");
        im.toCountMatrix(true);
        System.out.println("----");

//        int agus = im.countWordInText("agus zulvani ganteng sekali. agus zulvani adalah", "agus");
//        int sekali = im.countWordInText("agus zulvani ganteng sekali. agus zulvani adalah", "sekali");
//        System.out.println(agus);
//        System.out.println(sekali);

    }

    public static void main(String[] args) {
        new Main();
    }

}
