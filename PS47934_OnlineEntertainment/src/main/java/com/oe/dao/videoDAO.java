package com.oe.dao;

import java.util.List;

import com.oe.entity.video;
import com.oe.ultils.jpaUltil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class videoDAO {

    public void create(video entity) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi thêm Video");
        } finally {
            em.close();
        }
    }

    public void update(video entity) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi cập nhật Video");
        } finally {
            em.close();
        }
    }

    public void delete(String id) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();

            video entity = em.find(video.class, id);

            if (entity != null) {
                em.remove(entity);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Video đang được tham chiếu bởi Favorite hoặc Share");
        } finally {
            em.close();
        }
    }

    public video findById(String id) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            return em.find(video.class, id);
        } finally {
            em.close();
        }
    }

    public List<video> findTop6ByViews() {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql =
                    "SELECT v FROM video v WHERE v.active = true ORDER BY v.views DESC";

            return em.createQuery(jpql, video.class)
                    .setMaxResults(6)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<video> findAll() {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql = "SELECT v FROM video v";

            return em.createQuery(jpql, video.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<video> findAll(int pageNumber, int pageSize) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql =
                    "SELECT v FROM video v WHERE v.active = true";

            TypedQuery<video> query =
                    em.createQuery(jpql, video.class);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public long countActiveVideos() {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql =
                    "SELECT COUNT(v) FROM video v WHERE v.active = true";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }
}