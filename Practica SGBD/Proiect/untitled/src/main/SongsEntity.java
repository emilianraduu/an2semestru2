package main;

import javax.persistence.*;

@Entity
@Table(name = "SONGS", schema = "SYSTEM", catalog = "")
public class SongsEntity {
    private long idSong;
    private long idAlbum;
    private String songName;
    private String length;

    public SongsEntity() {
    }

    public SongsEntity(AlbumsEntity album, String songName, String length) {
        idAlbum = album.getIdAlbum();
        this.songName = songName;
        this.length = length;
    }

    @Id
    @Column(name = "ID_SONG")
    @SequenceGenerator(name = "song_seq", sequenceName = "song_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "song_seq")
    public long getIdSong() {
        return idSong;
    }

    public void setIdSong(long idSong) {
        this.idSong = idSong;
    }

    @Basic
    @Column(name = "ID_ALBUM")
    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Basic
    @Column(name = "SONG_LENGTH")
    public String getSongLength() {
        return length;
    }

    public void setSongLength(String length) {
        this.length = length;
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
        if (idAlbum != that.idAlbum) return false;
        if (songName != null ? !songName.equals(that.songName) : that.songName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idSong ^ (idSong >>> 32));
        result = 31 * result + (int) (idAlbum ^ (idAlbum >>> 32));
        result = 31 * result + (songName != null ? songName.hashCode() : 0);
        return result;
    }
}
