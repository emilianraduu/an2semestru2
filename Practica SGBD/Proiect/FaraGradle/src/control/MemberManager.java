package control;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MemberManager {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PSGBD");

    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("Input command:");
//            String command = scanner.nextLine();
//            if (command.equals("exit")) break;
//            String[] params = command.trim().split("\\s+");
//            switch (params[0]) {
//                case "create-person":
//                    createPerson(params[1]); //the person name
//                    break;
//                case "create-movie":
////                    createMovies(params[1], params[2]); //the album name and the director
//                    break;
//                case "list-movies":
//                    listMovies(params[1]); //the director name
//                    break;
//            }
//        }
    }
    private void registerMember(String email, String password, String avatar) {
//		...//Implement this method
    }
    private void checkLogin(String email, String password){
        // verifica parola daca e aceeasi cu cea din baza de date si ii salveaza in cookies user ul si parola
    }
    private void listMovies(String directorName) {
//		...//Implement this method
    }
    public static void main(String args[]) {
        new MemberManager().run();
    }
}