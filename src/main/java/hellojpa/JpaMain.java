package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            entityManager.persist(member1);
//            entityManager.persist(member2);
//            System.out.println("=======================");
//            //persist를 한 다음에 commit 할 것!!
//
//
             //조회
//            Member findMember = entityManager.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());
//
//             수정
//            Member findMember = entityManager.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            //요구되는 특정 개수의 리스트 가져오기
//            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .getResultList();
//
//            for(Member member : result){
//                System.out.println("memberName : " + member.getName());
//            }

            // commit하는 순간 db에 insert sql을 보낸다.
            tx.commit();
        }
        catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }

        emf.close();

        // 엔티티 매니저는 쓰레드간에 공유해서는 안된다!
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행하여야 한다!!
    }
}
