package com.oe.dao;

import java.util.List;

import com.oe.entity.favorite;
import com.oe.ultils.jpaUltil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class favoriteDAO {

    public void create(favorite fav) {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fav);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi khi Like video: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void deleteByUserIdAndVideoId(String userId, String videoId) {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            em.getTransaction().begin();

            String jpql = "SELECT f FROM favorite f WHERE f.user.id = :uid AND f.video.id = :vid";

            favorite fav = em.createQuery(jpql, favorite.class)
                    .setParameter("uid", userId)
                    .setParameter("vid", videoId)
                    .getSingleResult();

            if (fav != null) {
                em.remove(fav);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public favorite findByUserIdAndVideoId(String userId, String videoId) {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            String jpql = "SELECT f FROM favorite f WHERE f.user.id = :uid AND f.video.id = :vid";

            return em.createQuery(jpql, favorite.class)
                    .setParameter("uid", userId)
                    .setParameter("vid", videoId)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<favorite> findByUserId(String userId) {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            String jpql = "SELECT f FROM favorite f WHERE f.user.id = :uid";

            TypedQuery<favorite> query = em.createQuery(jpql, favorite.class);
            query.setParameter("uid", userId);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> reportFavoriteByVideo() {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            String jpql = """
                    SELECT f.video.title,
                           COUNT(f),
                           MAX(f.likeDate),
                           MIN(f.likeDate)
                    FROM favorite f
                    GROUP BY f.video.title
                    """;

            return em.createQuery(jpql, Object[].class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public Long countByVideoId(String videoId) {
        EntityManager em = jpaUltil.getEntityManager();
        try {
            String jpql = """
                    SELECT COUNT(f)
                    FROM favorite f
                    WHERE f.video.id = :vid
                    """;

            return em.createQuery(jpql, Long.class)
                    .setParameter("vid", videoId)
                    .getSingleResult();

        } catch (Exception e) {
            return 0L;
        } finally {
            em.close();
        }
    }
}