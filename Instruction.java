//file:Instruction.java


public class Instruction implements Description {
    public static void getHelp(){
        System.out.println("Selamat datang di game Monster Saku");
        System.out.println("");
        System.out.println("Game Monster Saku adalah aplikasi yang mengimplementasikan simulasi pertarungan antar monster saku. Permainan ini merupakan jenis permainan PvP yang bisa dimainkan oleh 2 pemain yang saling berlawanan.Masing-masing pemain akan menerima kombinasi 6 monster yang ditentukan secara acak oleh aplikasi pada setiap permainan");
        System.out.println("");
        System.out.println("Kedua pemain akan mengeluarkan monster satu per satu dan monster akan saling bertarung hingga salah satu dari kedua pemain sudah kehabisan monster yang dimilikinya");
        System.out.println("");
        System.out.println("Menu View Monsters Info digunakan untuk menampilkan informasi setiap atribut dari monster-monster yang ada saat permainan ");
        System.out.println("");
        System.out.println("Menu View Game Info digunakan untuk menampilkan informasi turn,informasi monster yang sedang bertarung, beserta informasi monster yang tidak sedang digunakan");
        System.out.println("");
    }
}
