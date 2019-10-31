package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SongController {
    private EntityManagerFactory emf;

    public SongController(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void addSong(String albumName, String songName, String length) {
        EntityManager em = emf.createEntityManager();
        AlbumsEntity temp = findAlbumByName(albumName);
        SongsEntity insert = new SongsEntity(temp, songName, length);
        em.getTransaction().begin();
        em.persist(insert);
        em.getTransaction().commit();
        em.close();
    }

    public SongsEntity findSongByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from SongsEntity t where t.songName like :name");
        return (SongsEntity) query.setParameter("name", name).getSingleResult();
    }

    public void removeSongsFromAlbum(AlbumsEntity albumsEntity) {
        EntityManager em = emf.createEntityManager();
        List<SongsEntity> f = getSongsFromAlbum(albumsEntity);
        em.getTransaction().begin();
        for (int i = 1; i < f.size(); i++) {
            em.remove(f.get(i));
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<SongsEntity> getAlbumsByArtist(String artistName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from AlbumsEntity t where t.artist like :artistName");
        return query.setParameter("artistName", artistName).getResultList();
    }

    public void removeSong(SongsEntity songsEntity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(songsEntity);
        em.getTransaction().commit();
        em.close();
    }

    public List<SongsEntity> getSongsFromAlbum(AlbumsEntity album) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from SongsEntity t where t.idAlbum like :albumId");
        return query.setParameter("albumId", album.getIdAlbum()).getResultList();
    }

    public void addAlbum(String artist, String genre, String albumName, String artwork, String date) {
        EntityManager em = emf.createEntityManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String[] parts = date.split("-");
        Integer year = Integer.valueOf(parts[2]);
        Integer month = Integer.valueOf(parts[1]);
        Integer day = Integer.valueOf(parts[0]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        java.sql.Date data = new java.sql.Date(cal.getTimeInMillis());
        AlbumsEntity f = new AlbumsEntity(artist, genre, albumName, artwork, data);
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }

    public AlbumsEntity findAlbumByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from AlbumsEntity t where t.albumName like :name");
        return (AlbumsEntity) query.setParameter("name", name).getSingleResult();
    }

    public AlbumsEntity findAlbumBySong(SongsEntity song) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from AlbumsEntity t where t.idAlbum like :name");
        em.close();
        return (AlbumsEntity) query.setParameter("name", song.getIdAlbum()).getSingleResult();
    }

    public void removeAlbum(AlbumsEntity albumsEntity) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from AlbumsEntity t where t.idAlbum like :id");
        query.setParameter("id", albumsEntity.getIdAlbum());
        em.getTransaction().begin();
        removeSongsFromAlbum(albumsEntity);
        em.remove(query.getSingleResult());
        em.close();
    }

    public List<AlbumsEntity> getAllAlbums() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from AlbumsEntity t ");
        return (List<AlbumsEntity>) query.getResultList();
    }
}
