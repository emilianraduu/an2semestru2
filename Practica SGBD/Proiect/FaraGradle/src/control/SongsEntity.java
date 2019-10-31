package control;

import javax.persistence.*;

@Entity
@Table(name = "SONGS", schema = "SYSTEM", catalog = "")
public class SongsEntity {
    private long idSong;
    private long idAlbumAp;
    private String songName;

    @Id
    @Column(name = "ID_SONG")
    public long getIdSong() {
        return idSong;
    }

    public void setIdSong(long idSong) {
        this.idSong = idSong;
    }

    @Basic
    @Column(name = "ID_ALBUM_AP")
    public long getIdAlbumAp() {
        return idAlbumAp;
    }

    public void setIdAlbumAp(long idAlbumAp) {
        this.idAlbumAp = idAlbumAp;
    }

    @Basic
    @Column(name = "SONG_NAME")
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongsEntity that = (SongsEntity) o;

        if (idSong != that.idSong) return false;
        if (idAlbumAp != that.idAlbumAp) return false;
        if (songName != null ? !songName.equals(that.songName) : that.songName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idSong ^ (idSong >>> 32));
        result = 31 * result + (int) (idAlbumAp ^ (idAlbumAp >>> 32));
        result = 31 * result + (songName != null ? songName.hashCode() : 0);
        return result;
    }
}
