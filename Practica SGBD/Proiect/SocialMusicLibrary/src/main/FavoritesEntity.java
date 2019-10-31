package main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FAVORITES", schema = "SYSTEM", catalog = "")
public class FavoritesEntity {
    private Long id;
    private Long idMemberFavs;
    private Long idSongFavs;

    FavoritesEntity(){}
    FavoritesEntity(MembersEntity membersEntity, SongsEntity songsEntity){
        idMemberFavs = membersEntity.getIdMember();
        idSongFavs = songsEntity.getIdSong();
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
    @Column(name = "ID_MEMBER_FAVS", nullable = false, precision = 0)
    public Long getIdMemberFavs() {
        return idMemberFavs;
    }

    public void setIdMemberFavs(Long idMemberFavs) {
        this.idMemberFavs = idMemberFavs;
    }

    @Basic
    @Column(name = "ID_SONG_FAVS", nullable = false, precision = 0)
    public Long getIdSongFavs() {
        return idSongFavs;
    }

    public void setIdSongFavs(Long idSongFavs) {
        this.idSongFavs = idSongFavs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesEntity that = (FavoritesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idMemberFavs, that.idMemberFavs) &&
                Objects.equals(idSongFavs, that.idSongFavs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMemberFavs, idSongFavs);
    }
}
