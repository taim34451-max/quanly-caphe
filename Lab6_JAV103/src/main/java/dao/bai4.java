package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import model.report;
import model.video;

public class bai4 {
    private EntityManager em;

    public bai4(EntityManager em) {
        this.em = em;
    }

    // 1. Truy vấn 10 video ngẫu nhiên bằng SQL Thuần (Native Query) [cite: 302-312]
    public List<video> getRandom10Videos() {
        // Dùng thẳng lệnh SQL của SQL Server (ORDER BY newid())
        String sql = "SELECT TOP 10 * FROM Videos ORDER BY newid()";
        Query query = em.createNativeQuery(sql, video.class);
        return query.getResultList();
    }

    // 2. Tổng hợp lượt thích theo năm bằng Stored Procedure [cite: 313-333, 345-353]
    public List<Object[]> getFavoriteByYear(Integer year) {
        // Sửa thành trả về List<Object[]>
        StoredProcedureQuery query = em.createStoredProcedureQuery("spFavoriteByYear");
        query.registerStoredProcedureParameter("Year", Integer.class, ParameterMode.IN);
        query.setParameter("Year", year);
        return query.getResultList();
    }
        
}