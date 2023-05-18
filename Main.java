package game;

import java.util.ArrayList;
import java.util.List;


interface FolderManager {
    void createFolder(String folderName);
    void deleteFolder(String folderName);
    void displayFolders();                             // interface mengelola folder
}


abstract class AbstractFolderManager implements FolderManager {
    protected List<String> folders;

    public AbstractFolderManager() {
        folders = new ArrayList<>();
    }                                                  // abstrak mengimplementasi folder

 
    public void createFolder(String folderName) {
        folders.add(folderName);
        System.out.println("Folder '" + folderName + "' berhasil dibuat.");
    }

    
    public void deleteFolder(String folderName) {
        if (folders.contains(folderName)) {
            folders.remove(folderName);
            System.out.println("Folder '" + folderName + "' berrhasil dihapus.");
        } else {
            System.out.println("Folder '" + folderName + "' tidak ditemukan.");
        }
    }


    public void displayFolders() {
        if (folders.isEmpty()) {
            System.out.println("tidak ada folder yang tersedia.");
        } else {
            System.out.println("daftar folder:");
            for (String folder : folders) {
                System.out.println("- " + folder);
            }
        }
    }
}

public class Karakter {
    private String nama;
    private int kesehatan;
    private int serangan;
    private int pertahanan;

    public Karakter(String nama, int kesehatan, int serangan, int pertahanan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.serangan = serangan;
        this.pertahanan = pertahanan;
    }

    public String getNama() {
        return nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public int getSerangan() {
        return serangan;
    }

    public int getPertahanan() {
        return pertahanan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public void setSerangan(int serangan) {
        this.serangan = serangan;
    }

    public void setPertahanan(int pertahanan) {
        this.pertahanan = pertahanan;
    }

    public void serang(Karakter target) {
        int kerusakan = this.serangan - target.pertahanan;
        if (kerusakan > 0) {
            target.kesehatan -= kerusakan;
            System.out.println(this.nama + " menyerang " + target.nama + " sebesar " + kerusakan + " point kerusakan!");
            System.out.println(target.nama + " memiliki " + target.kesehatan + " kesehatan tersisa.");
        } else {
            System.out.println(this.nama + " menyerang " + target.nama + ", dann tidak berpengaruh!");
        }
    }

    
    class InnerFolderManager {
        private AbstractFolderManager folderManager;

        public InnerFolderManager() {
            folderManager = new FolderManagerImpl();
        }

        public void execute() {
            folderManager.createFolder("Folder 1");
            folderManager.createFolder("Folder 2");
            folderManager.displayFolders();
            folderManager.deleteFolder("Folder 1");
            folderManager.deleteFolder("Folder 4");
            folderManager.displayFolders();
        }

      
        private class FolderManagerImpl extends AbstractFolderManager {
        }
    }

    public static void main(String[] args) {
        Karakter karakter = new Karakter("Player 1", 1000, 100, 5);
        karakter.serang(new Karakter("Enemy", 10000, 8, 3));

        InnerFolderManager innerFolderManager = karakter.new InnerFolderManager();
        innerFolderManager.execute();
    }
}
