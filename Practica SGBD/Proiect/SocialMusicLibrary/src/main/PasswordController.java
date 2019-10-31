package main;

import javax.persistence.EntityManagerFactory;
import java.io.*;

public class PasswordController {

    private static String email;
    private static String password;
    private static String[] parts;
    private Boolean ok = false;
    private EntityManagerFactory emf;
    PersonController p = new PersonController(emf);

    public PasswordController(EntityManagerFactory emf) {
        try {
            parts = getInfoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (parts != null) {
            if (parts[0] != null)
                email = parts[0];
            if (parts[1] != null)
                password = parts[1];
            if (email != null && password != null) {
                ok = true;
            }
        }
        this.emf = emf;
        init();

    }

    public Boolean getBoolean() {
        return ok;
    }

    public void setBoolean(Boolean k) {
        ok = k;
    }

    public void init() {
//        if(p.findByEmail(email).getPasswd() == password) System.out.println("ok");

    }


    public void deleteUser() {
        File file = new File(p.getLoginInfo());
        file.delete();
        ok = false;
    }

    private String getEmail() {
        return email;
    }

    private String getPassword() {
        return password;
    }


    private String[] getInfoFile() throws IOException {
        File file = new File(p.getLoginInfo());
        file.createNewFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] parts = st.split(" ");
                return parts;
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
