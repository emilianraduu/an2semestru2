package model;

public class Member {
    private Integer id_member;
    private String email;
    private String passwd;
    private String username;
    private String avatar;

    Member() {
    }

    Member(Integer id, String mail, String pwd, String uname, String avt) {
        id_member = id;
        email = mail;
        passwd = pwd;
        username = uname;
        avatar = avt;
    }
}
