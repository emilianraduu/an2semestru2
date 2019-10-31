package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class FavoritesController {
    private EntityManagerFactory emf;

    public FavoritesController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void addFav(MembersEntity membersEntity, SongsEntity songsEntity) {
        EntityManager em = emf.createEntityManager();
        FavoritesEntity f = new FavoritesEntity(membersEntity, songsEntity);
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }

    public List<FavoritesEntity> findFavoritesByMember(MembersEntity membersEntity) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from FavoritesEntity t where t.idMemberFavs like :id");
        query.setParameter("id", membersEntity.getIdMember());
        return (List<FavoritesEntity>) query.getResultList();

    }

    public void removeFav(FavoritesEntity favoritesEntity) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from FavoritesEntity t where t.idSongFavs like :id");
        query.setParameter("id", favoritesEntity.getIdSongFavs());
        em.getTransaction().begin();
        em.remove(query.getSingleResult());
        em.getTransaction().commit();
        em.close();
    }

    public void removeAllFav(MembersEntity membersEntity) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from FavoritesEntity t where t.idMemberFavs like :id");
        query.setParameter("id", membersEntity.getIdMember());
        List<FavoritesEntity> list = query.getResultList();
        em.getTransaction().begin();

        for (int i = 1; i < list.size(); i++) {
            em.remove(list.get(i));
        }
        em.getTransaction().commit();
        em.close();
    }
}
