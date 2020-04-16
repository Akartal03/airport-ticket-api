/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kartal.airportticketapi.repository;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.kartal.airportticketapi.model.Airport;
import tr.kartal.airportticketapi.utils.AirportSearchQueryCriteriaConsumer;
import tr.kartal.airportticketapi.utils.SearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log4j2
public class DaoUtils {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean saveObject(Object object) {
        try (var ses = entityManager.unwrap(Session.class)) {
            ses.save(object);
        } catch (Exception e) {
            log.error("saveObject: " + object.toString() + " -> " + e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public boolean saveObjectStateless(Object object) {
        try (var ses = entityManager.unwrap(Session.class).getSessionFactory().openStatelessSession()) {
            ses.insert(object);
        } catch (Exception e) {
            log.error("saveObject: " + object.toString() + " -> " + e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public boolean deleteObject(Object object) {
        try (var ses = entityManager.unwrap(Session.class)) {
            ses.delete(object);
        } catch (Exception e) {
            log.error("deleteObject: " + object.toString() + " -> " + e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public boolean updateObject(Object object) {
        try (var ses = entityManager.unwrap(Session.class)) {
            ses.update(object);
        } catch (Exception e) {
            log.error("updateObject: " + object.toString() + " -> " + e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

   public <X> List<X> getObjects(String sql) {
       var que = entityManager.createNativeQuery(sql);
       List<X> list;

       try {
           list = que.getResultList();
       } catch (Exception ex) {
           log.warn("DaoUtils getObject: " + ex.getMessage());
           return null;
       }
       return new ArrayList<>(list);
   }

    public List<Airport> searchAirport(List<SearchCriteria> params) {
        log.info("asdsadsa");
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Airport> query = builder.createQuery(Airport.class);
        final Root r = query.from(Airport.class);

        Predicate predicate = builder.conjunction();
        AirportSearchQueryCriteriaConsumer searchConsumer = new AirportSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }
}
