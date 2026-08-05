package com.oe.dao;

import java.util.List;

import com.oe.entity.share;
import com.oe.ultils.jpaUltil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class shareDAO {

    public void create(share s) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi khi lưu lịch sử Share: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Object[]> reportShareByVideo(String videoId) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql = """
                    SELECT s.user.fullname,
                           s.user.email,
                           s.emails,
                           s.shareDate
                    FROM share s
                    WHERE s.video.id = :vid
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("vid", videoId);

            return query.getResultList();

        } finally {
            em.close();
        }
    }
}