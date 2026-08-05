package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.favorite;
import model.report;
import model.user;
import model.video;

public class bai2 {
    private EntityManager em;

    // Constructor nhận EntityManager để thực thi truy vấn
    public bai2(EntityManager em) {
        this.em = em;
    }

    // 1. Tìm các video yêu thích theo người sử dụng (nhập user id)
    public List<favorite> getFavoritesByUser(String userId) {
        String jpql = "SELECT f FROM favorite f JOIN FETCH f.video WHERE f.user.id = :uid";
        TypedQuery<favorite> query = em.createQuery(jpql, favorite.class);
        query.setParameter("uid", userId);
        return query.getResultList();
    }
       
    // 2. Tìm các video được yêu thích có title chứa từ khóa
    public List<video> findVideosByKeyword(String keyword) {
        String jpql = "SELECT DISTINCT o.video FROM favorite o WHERE o.video.title LIKE :keyword";
        TypedQuery<video> query = em.createQuery(jpql, video.class);
        // Thêm dấu % để tìm kiếm chứa từ khóa
        query.setParameter("keyword", "%" + keyword + "%"); 
        return query.getResultList();
    }

    // 3. Tìm những người sử dụng thích video (nhập video id)
    public List<user> findUsersByVideoId(String videoId) {
        String jpql = "SELECT o.user FROM favorite o WHERE o.video.id = :vid";
        TypedQuery<user> query = em.createQuery(jpql, user.class);
        query.setParameter("vid", videoId);
        return query.getResultList();
    }

    // 4. Hiển thị tất cả các video không có hoặc có yêu thích
    public List<video> findVideosByFavoriteStatus(boolean isFavorite) {
        // Kiểm tra xem danh sách favorites của video có rỗng hay không
        String jpql = isFavorite 
            ? "SELECT o FROM video o WHERE o.favorites IS NOT EMPTY" 
            : "SELECT o FROM video o WHERE o.favorites IS EMPTY";
        TypedQuery<video> query = em.createQuery(jpql, video.class);
        return query.getResultList();
    }

    // 5. Tổng hợp số lượt thích từng video (video title, số lượt thích, ngày cũ nhất, mới nhất)
    public List<report> getFavoriteReport() {
        // Sử dụng hàm khởi tạo (Constructor) của lớp report ngay trong JPQL
        String jpql = "SELECT new model.report(o.video.title, count(o), max(o.likeDate), min(o.likeDate)) " +
                      "FROM favorite o GROUP BY o.video.title";
        TypedQuery<report> query = em.createQuery(jpql, report.class);
        return query.getResultList();
    }
}