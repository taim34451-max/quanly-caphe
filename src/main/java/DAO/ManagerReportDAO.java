package DAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ManagerReportDAO {

    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PolyCoffee");


    // =====================================================
    // GET ENTITY MANAGER
    // =====================================================

    private EntityManager getEntityManager() {
        return factory.createEntityManager();
    }


    // =====================================================
    // 1. DAILY REVENUE
    // =====================================================

    public BigDecimal getDailyRevenue(LocalDate date) {

        EntityManager em = getEntityManager();

        try {

            LocalDate nextDay = date.plusDays(1);

            String jpql = """
                    SELECT COALESCE(SUM(b.total), 0)
                    FROM Bill b
                    WHERE b.status = 'COMPLETED'
                    AND b.createdDate >= :start
                    AND b.createdDate < :end
                    """;

            TypedQuery<BigDecimal> query =
                    em.createQuery(jpql, BigDecimal.class);

            query.setParameter("start", date.atStartOfDay());
            query.setParameter("end", nextDay.atStartOfDay());

            return query.getSingleResult();

        } finally {
            em.close();
        }
    }


    // =====================================================
    // 2. WEEKLY REVENUE
    // =====================================================

    public BigDecimal getWeeklyRevenue(LocalDate date) {

        EntityManager em = getEntityManager();

        try {

            // Monday = first day of week
            LocalDate startOfWeek =
                    date.minusDays(date.getDayOfWeek().getValue() - 1);

            LocalDate endOfWeek =
                    startOfWeek.plusDays(7);

            String jpql = """
                    SELECT COALESCE(SUM(b.total), 0)
                    FROM Bill b
                    WHERE b.status = 'COMPLETED'
                    AND b.createdDate >= :start
                    AND b.createdDate < :end
                    """;

            TypedQuery<BigDecimal> query =
                    em.createQuery(jpql, BigDecimal.class);

            query.setParameter(
                    "start",
                    startOfWeek.atStartOfDay()
            );

            query.setParameter(
                    "end",
                    endOfWeek.atStartOfDay()
            );

            return query.getSingleResult();

        } finally {
            em.close();
        }
    }


    // =====================================================
    // 3. MONTHLY REVENUE
    // =====================================================

    public BigDecimal getMonthlyRevenue(LocalDate date) {

        EntityManager em = getEntityManager();

        try {

            LocalDate startOfMonth =
                    date.withDayOfMonth(1);

            LocalDate endOfMonth =
                    startOfMonth.plusMonths(1);

            String jpql = """
                    SELECT COALESCE(SUM(b.total), 0)
                    FROM Bill b
                    WHERE b.status = 'COMPLETED'
                    AND b.createdDate >= :start
                    AND b.createdDate < :end
                    """;

            TypedQuery<BigDecimal> query =
                    em.createQuery(jpql, BigDecimal.class);

            query.setParameter(
                    "start",
                    startOfMonth.atStartOfDay()
            );

            query.setParameter(
                    "end",
                    endOfMonth.atStartOfDay()
            );

            return query.getSingleResult();

        } finally {
            em.close();
        }
    }


    // =====================================================
    // 4. HIGHEST REVENUE ITEMS
    // =====================================================

    public List<Object[]> getHighestRevenueItems(
            LocalDate startDate,
            LocalDate endDate) {

        EntityManager em = getEntityManager();

        try {

            String jpql = """
                    SELECT
                        p.productId,
                        p.productName,
                        p.category,
                        SUM(bd.quantity),
                        SUM(bd.quantity * bd.price)
                    FROM BillDetail bd
                    JOIN bd.bill b
                    JOIN bd.product p
                    WHERE b.status = 'COMPLETED'
                    AND b.createdDate >= :start
                    AND b.createdDate < :end
                    GROUP BY
                        p.productId,
                        p.productName,
                        p.category
                    ORDER BY
                        SUM(bd.quantity * bd.price) DESC
                    """;

            TypedQuery<Object[]> query =
                    em.createQuery(jpql, Object[].class);

            query.setParameter(
                    "start",
                    startDate.atStartOfDay()
            );

            query.setParameter(
                    "end",
                    endDate.atStartOfDay()
            );

            return query.getResultList();

        } finally {
            em.close();
        }
    }


    // =====================================================
    // 5. ITEMS SORTED BY CATEGORY
    // =====================================================

   public List<Object[]> getSalesByCategory(
        LocalDate startDate,
        LocalDate endDate) {

    EntityManager em = getEntityManager();

    try {

    	String jpql = """
    	        SELECT
    	            p.category,
    	            SUM(bd.quantity),
    	            SUM(bd.quantity * bd.price)
    	        FROM BillDetail bd
    	        JOIN bd.bill b
    	        JOIN bd.product p
    	        WHERE b.status = 'COMPLETED'
    	        AND b.createdDate >= :start
    	        AND b.createdDate < :end
    	        GROUP BY
    	            p.category
    	        ORDER BY
    	            p.category ASC
    	        """;

        TypedQuery<Object[]> query =
                em.createQuery(jpql, Object[].class);

        query.setParameter(
                "start",
                startDate.atStartOfDay()
        );

        query.setParameter(
                "end",
                endDate.atStartOfDay()
        );
        
        return query.getResultList();

    } finally {
        em.close();
    }
}
}