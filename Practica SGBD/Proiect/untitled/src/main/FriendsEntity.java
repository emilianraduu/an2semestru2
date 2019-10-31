package main;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS", schema = "SYSTEM", catalog = "")
public class FriendsEntity {
    private long idMember1;
    private long idMember2;

    public FriendsEntity(){}
    public FriendsEntity(MembersEntity m1, MembersEntity m2){
        idMember1 = m1.getIdMember();
        idMember2 = m2.getIdMember();
    }

    @Id
    @Column(name = "ID_MEMBER1")
    public long getIdMember1() {
        return idMember1;
    }

    public void setIdMember1(long idMember1) {
        this.idMember1 = idMember1;
    }

    @Basic
    @Column(name = "ID_MEMBER2")
    public long getIdMember2() {
        return idMember2;
    }

    public void setIdMember2(long idMember2) {
        this.idMember2 = idMember2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsEntity that = (FriendsEntity) o;

        if (idMember1 != that.idMember1) return false;
        if (idMember2 != that.idMember2) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMember1 ^ (idMember1 >>> 32));
        result = 31 * result + (int) (idMember2 ^ (idMember2 >>> 32));
        return result;
    }
}
