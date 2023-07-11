
package hellojpa;

import org.hibernate.Criteria;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Member member = new Member();
            member.setName("lim");
            em.persist(member);

            //flush -> commit, query
            em.flush();
            //dbconn.executeQuery("selet * from member"); // 결과 0

            System.out.println("===============");
            List<Member> resultList = em.createNativeQuery("select member_id, city,street,zipcode,user_name from member", Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println(member1);
            }
            System.out.println("==================");
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
