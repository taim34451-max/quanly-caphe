package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import model.video;

public class bai3 {
    private EntityManager em;

    public bai3(EntityManager em) {
        this.em = em;
    }

    // 1. Tìm video theo từ khóa (gọi Named Query từ orm.xml) [cite: 281-285]
    public List<video> findVideosByKeyword(String keyword) {
        TypedQuery<video> query = em.createNamedQuery("video.findByKeyword", video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    // 2. Tìm video yêu thích theo User ID [cite: 226, 269-271]
    public List<video> findVideosByUser(String userId) {
        TypedQuery<video> query = em.createNamedQuery("video.findByUser", video.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    // 3. Tìm video trong khoảng thời gian [cite: 230, 272-274]
    public List<video> findVideosInRange(Date minDate, Date maxDate) {
        TypedQuery<video> query = em.createNamedQuery("video.findInRange", video.class);
        query.setParameter("min", minDate);
        query.setParameter("max", maxDate);
        return query.getResultList();
    }

    // 4. Tìm video được yêu thích trong các tháng cụ thể [cite: 234, 275-280, 287-297]
    public List<video> findVideosInMonths(List<Integer> months) {
        TypedQuery<video> query = em.createNamedQuery("video.findInMonths", video.class);
        query.setParameter("months", months);
        return query.getResultList();
    }
}