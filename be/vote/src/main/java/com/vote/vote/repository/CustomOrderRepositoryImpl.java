package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.customSelect.CustomOrderState;
import com.vote.vote.db.customSelect.CustomOrderStatePop;
import com.vote.vote.db.customSelect.CustomOrderStatePopAge;
import com.vote.vote.db.customSelect.CustomOrderStatePopGender;
import com.vote.vote.db.dto.QOrder;
import com.vote.vote.db.dto.QOrderList;
import com.vote.vote.db.dto.QPrd;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderReopsitoy {

    @PersistenceContext
    private EntityManager em;

    private QOrder order = QOrder.order;
    private QOrderList list = QOrderList.orderList;
    private QPrd prd = QPrd.prd;

    @Override
    public List<CustomOrderState> getOrderStateByManagerId(int managerId) {
        CaseBuilder a = new CaseBuilder();

        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // 여기다가 조건절을 단다.

        // booleanBuilder.and(order.rId.eq(managerId));
        String sql = "select l.product_id ,p.p_name name,  TO_CHAR(DBMS_LOB.SUBSTR(p.image, 4000))  image, NVL(sum(l.count),0)   sum, "
                + "NVL(sum( case when o.orderdate between add_months(sysdate,-1) and sysdate then l.count end ),0) one, "
                + "NVL(sum ( case when o.orderdate between add_months(sysdate,-2) and add_months(sysdate,-1) then l.count end),0) two, "
                + "NVL(sum ( case when o.orderdate between add_months(sysdate,-3) and add_months(sysdate,-2) then l.count end),0) three, "
                + "NVL(sum ( case when o.orderdate between add_months(sysdate,-4) and add_months(sysdate,-3) then l.count end),0) four, "
                + "NVL(sum ( case when o.orderdate between add_months(sysdate,-5) and add_months(sysdate,-4) then l.count end),0) five "
                + "from r_order o , orderlist l, product p " + "where o.order_id = l.order_id "
                + "and l.product_id = p.product_id "
                + "and  l.product_id in (select product_id from product where p_manager = " + managerId + ") "
                + "group by l.product_id, p.p_name, TO_CHAR(DBMS_LOB.SUBSTR(p.image, 4000)) ";

        Query nativeQuery = em.createNativeQuery(sql);
        // .setParameter("mId", managerId);
        // .getResultList();
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<CustomOrderState> result = jpaResultMapper.list(nativeQuery, CustomOrderState.class);

        return result;
    }

    @Override
    public List<CustomOrderStatePop> getOrderStatePopByProgramId(int pId) {

        String sql = " select pop.popular_id, pop.p_name pName, to_char(pop.p_image) img,nvl(pp.count, 0) "
                +" from ( "
                +" select p.popid  popId,sum(o.count) count "
                +"  from  product  p, orderlist  o " 
                +" where  p.product_id = o.product_id "
                +" and p.popid  != 0 "
                +" group by p.popid "
                +" ) pp, popular pop "
                +" where pp.popId(+) = pop.popular_id "
                +" and pop.program_Id = "+pId
                +" order by pop.popular_id ";

        Query nativeQuery = em.createNativeQuery(sql);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<CustomOrderStatePop> result = jpaResultMapper.list(nativeQuery, CustomOrderStatePop.class);

        return result;
    }

    @Override
    public List<CustomOrderStatePopGender> getOrderStatePopGenderByProgramId(int pId) {
        
        String sql = " select pop.popular_id, pop.p_name, nvl(pp.count,0), nvl(pp.gender,0) gender "
                +" from ( "
                +" select p.popid  popId,sum(o.count) count, nvl(u.gender, '0' ) gender "
                +"  from  product  p, orderlist  o,  r_order r, r_user u " 
                +" where  p.product_id = o.product_id "
                +" and p.popid  != 0 "
                +"  and r.order_id = o.order_id "
                +" and r.r_id(+) = u.r_id "
                +" group by p.popid, nvl(u.gender, '0' ) ) pp, popular pop "
                +" where pp.popId(+) = pop.popular_id "
                +" and pop.program_Id = "+pId
                +" order by pop.popular_id ";
        Query nativeQuery = em.createNativeQuery(sql);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<CustomOrderStatePopGender> result = jpaResultMapper.list(nativeQuery, CustomOrderStatePopGender.class);

        return result;
    }

    @Override
    public List<CustomOrderStatePopAge> getOrderStatePopPopAgeByProgramId(int pId) {
        String sql = " select pop.popular_id, pop.p_name, nvl(pp.count,0), nvl(pp.age,2) age "
                +" from ( "
                +" select p.popid  popId,sum(o.count) count, case when NVL( TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, u.birth)/12)/10),0) = 0 then 1  else  NVL( TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, u.birth)/12)/10),0) end  AS AGE   "
                +"  from  product  p, orderlist  o,  r_order r, r_user u " 
                +" where  p.product_id = o.product_id "
                +" and p.popid  != 0"
                +" and r.order_id = o.order_id "
                +" and r.r_id(+) = u.r_id "
                +" group by p.popid, case when NVL( TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, u.birth)/12)/10),0) = 0 then 1  else  NVL( TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, u.birth)/12)/10),0) end  "
                +"  ) pp, popular pop "
                +" where pp.popId(+) = pop.popular_id "
                +" and pop.program_Id = "+pId
                +" order by pop.popular_id ";
        Query nativeQuery = em.createNativeQuery(sql);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<CustomOrderStatePopAge> result = jpaResultMapper.list(nativeQuery, CustomOrderStatePopAge.class);
        return result;
    }
    
    
        
// select l.product_id ,p.p_name,count(*) 전체 , 
// count ( case when o.orderdate between add_months(sysdate,-1) and sysdate then 1 end) 한달,
// count ( case when o.orderdate between add_months(sysdate,-2) and add_months(sysdate,-1) then 1 end) 두달,
// count ( case when o.orderdate between add_months(sysdate,-3) and add_months(sysdate,-2) then 1 end) 세달,
// count ( case when o.orderdate between add_months(sysdate,-4) and add_months(sysdate,-3) then 1 end) 네달,
// count ( case when o.orderdate between add_months(sysdate,-5) and add_months(sysdate,-4) then 1 end) 다섯달
// from r_order o , orderlist l, product p
// where o.order_id = l.order_id
// and l.product_id = p.product_id
// and  l.product_id in (select product_id from product where p_manager = '261')
// group by l.product_id, p.p_name
}