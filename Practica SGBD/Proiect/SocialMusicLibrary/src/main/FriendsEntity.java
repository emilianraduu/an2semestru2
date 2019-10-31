package main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FRIENDS", schema = "SYSTEM", catalog = "")
public class FriendsEntity {
    private Long id;
    private Long idMember1;
    private Long idMember2;

    FriendsEntity(){

    }
    FriendsEntity(MembersEntity m1, MembersEntity m2){
        idMember1 = m1.getIdMember();
        idMember2 = m2.getIdMember();
    }
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_MEMBER1", nullable = false, precision = 0)
    public Long getIdMember1() {
        return idMember1;
    }

    public void setIdMember1(Long idMember1) {
        this.idMember1 = idMember1;
    }

    @Basic
    @Column(name = "ID_MEMBER2", nullable = false, precision = 0)
    public Long getIdMember2() {
        return idMember2;
    }

    public void setIdMember2(Long idMember2) {
        this.idMember2 = idMember2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsEntity that = (FriendsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idMember1, that.idMember1) &&
                Objects.equals(idMember2, that.idMember2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMember1, idMember2);
    }
}
