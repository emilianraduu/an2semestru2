package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class AppManager {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PSGBD");
    PersonController p = new PersonController(emf);
    PasswordController pw = new PasswordController(emf);
    SongController sc = new SongController(emf);
    MembersEntity loggedUser = new MembersEntity();

    public EntityManagerFactory getEmf(){
        return emf;
    }

    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            if (pw.getBoolean() == false) {
//
//                System.out.println("Please login or register");
//                System.out.print("Input command:");
//                String command = scanner.nextLine();
//                String[] params = command.trim().split("\\s+");
//                System.out.println(pw.getBoolean());
//                switch (params[0]) {
//                    case "register":
//                        createPerson(params[1], params[2], params[3], params[4], params[5]);
//                        break;
//
//                    case "login":
//                        checkLogin(params[1], params[2]);
//                        break;
//                }
//            }
//
//            if (pw.getBoolean()) {
//                System.out.println("You are logged in.");
//                System.out.print("Input command:");
//                String command = scanner.nextLine();
//                if (command.equals("exit")) break;
//                String[] params = command.trim().split("\\s+");
//                pw.getBoolean();
//                switch (params[0]) {
//                    case "friend":
//                        addFriend(params[1], params[2]);
//                        break;
//
//                    case "rm-friend":
//                        removeFriend(params[1], params[2]);
//                        break;
//
//                    case "find":
//                        findByEmail(params[1]);
//                        break;
//
//                    case "get-friends":
//                        getFriends(params[1]);
//                        break;
//
//                    case "add-album":
//                        addAlbum(params[1], params[2], params[3], params[4], params[5]);
//                        break;
//
//                    case "add-song":
//                        addSong(params[1], params[2], params[3]);
//                        break;
//
//                    case "find-album":
//                        findAlbum(params[1]);
//                        break;
//
//                    case "remove-album":
//                        removeAlbum(params[1]);
//                        break;
//
//                    case "sign-out":
//                        signOut();
//                        break;
//
//                }
//            }
//        }
    }

    public void addSong(String albumName, String songName, String length) {
        sc.addSong(albumName, songName, length);
    }

    public void removeAlbum(String albumName) {
        AlbumsEntity albumsEntity = sc.findAlbumByName(albumName);
        sc.removeAlbum(albumsEntity);
    }

    public void findAlbum(String name) {
        sc.findAlbumByName(name);
    }

    public void createPerson(String email, String pass, String fname, String lname, String avatar) {
        String salt = PasswordUtils.getSalt(30);
        String mySecurePassword = PasswordUtils.generateSecurePassword(pass, salt);
        p.create(new MembersEntity(email, mySecurePassword, salt, fname, lname, avatar));
    }

    public void addAlbum(String artist, String genre, String albumName, String artwork, String date) {
        sc.addAlbum(artist, genre, albumName, artwork, date);
    }

    public void findByEmail(String email) {
        p.findByEmail(email);
    }

    public void removeFriend(String email1, String email2) {
        p.removeFriend(p.findByEmail(email1), p.findByEmail(email2));
    }

    public void signOut() {
        pw.deleteUser();
    }

    public Boolean checkLogin(String email, String passwd) {
        loggedUser = p.getLoggedUser();
        return p.checkUser(email, passwd);
    }

    public List<FriendsEntity> getFriends(String email) {
        return p.getAllFriendsByMail(email);
    }

    public void addFriend(String email1, String email2) {
        p.addFriend(p.findByEmail(email1), p.findByEmail(email2));
    }

//    public static void main(String args[]) {
//        new AppManager().run();
//    }
}

