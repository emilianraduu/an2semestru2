package main;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERS", schema = "SYSTEM", catalog = "")
public class MembersEntity {
    private long idMember;
    private String email;
    private String passwd;
    private String salt;
    private String lname;
    private String fname;
    private String avatar;

    public MembersEntity(){

    }

    public MembersEntity(String mail, String ps, String salt, String ln, String fn, String av){
        email = mail;
        passwd = ps;
        this.salt = salt;
        lname = ln;
        fname = fn;
        avatar = av;
    }
    @Id
    @Column(name = "ID_MEMBER")
    public long getIdMember() {
        return idMember;
    }

    public void setIdMember(long idMember) {
        this.idMember = idMember;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWD")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "SALT")
    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    @Basic
    @Column(name = "LNAME")
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Basic
    @Column(name = "FNAME")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "AVATAR")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembersEntity that = (MembersEntity) o;

        if (idMember != that.idMember) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwd != null ? !passwd.equals(that.passwd) : that.passwd != null) return false;
        if (lname != null ? !lname.equals(that.lname) : that.lname != null) return false;
        if (fname != null ? !fname.equals(that.fname) : that.fname != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMember ^ (idMember >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }
}
