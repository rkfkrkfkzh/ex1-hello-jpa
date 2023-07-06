
package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {



            Team team2 = new Team();
            team2.setName("CCC");
            em.persist(team2);

            Member member1 = new Member();
            member1.setName("ABC1");
            member1.setTeam(team2);
            em.persist(member1);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("member1.getClass() = " + findMember.getClass());
            System.out.println("member1.getTeam().getClass() = " + findMember.getTeam().getClass());

            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());


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
