package control;

import javax.persistence.*;

@Entity
@Table(name = "ALBUMS", schema = "SYSTEM", catalog = "")
public class AlbumsEntity {
    private long idAlbum;
    private String artist;
    private String genre;
    private String albumName;
    private String artwork;

    @Id
    @Column(name = "ID_ALBUM")
    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Basic
    @Column(name = "ARTIST")
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Basic
    @Column(name = "GENRE")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "ALBUM_NAME")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Basic
    @Column(name = "ARTWORK")
    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumsEntity that = (AlbumsEntity) o;

        if (idAlbum != that.idAlbum) return false;
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (albumName != null ? !albumName.equals(that.albumName) : that.albumName != null) return false;
        if (artwork != null ? !artwork.equals(that.artwork) : that.artwork != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idAlbum ^ (idAlbum >>> 32));
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (albumName != null ? albumName.hashCode() : 0);
        result = 31 * result + (artwork != null ? artwork.hashCode() : 0);
        return result;
    }
}
