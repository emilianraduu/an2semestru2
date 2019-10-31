package main;

import javax.persistence.*;

@Entity
@Table(name = "FAVORITES", schema = "SYSTEM", catalog = "")
public class FavoritesEntity {
    private long idMemberFavs;
    private long idSongFavs;

    public FavoritesEntity(){}
    public FavoritesEntity(MembersEntity membersEntity, SongsEntity songsEntity){
        idMemberFavs = membersEntity.getIdMember();
        idSongFavs = songsEntity.getIdSong();
    }

    @Id
    @Column(name = "ID_MEMBER_FAVS")
    public long getIdMemberFavs() {
        return idMemberFavs;
    }

    public void setIdMemberFavs(long idMemberFavs) {
        this.idMemberFavs = idMemberFavs;
    }

    @Basic
    @Column(name = "ID_SONG_FAVS")
    public long getIdSongFavs() {
        return idSongFavs;
    }

    public void setIdSongFavs(long idSongFavs) {
        this.idSongFavs = idSongFavs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritesEntity that = (FavoritesEntity) o;

        if (idMemberFavs != that.idMemberFavs) return false;
        if (idSongFavs != that.idSongFavs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMemberFavs ^ (idMemberFavs >>> 32));
        result = 31 * result + (int) (idSongFavs ^ (idSongFavs >>> 32));
        return result;
    }
}
