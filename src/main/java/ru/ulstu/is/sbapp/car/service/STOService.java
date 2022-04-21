package ru.ulstu.is.sbapp.car.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.car.model.Car;
import ru.ulstu.is.sbapp.car.model.STO;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class STOService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public STO addSTO(String name) {
        if(!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("STO name is null or empty");
        }
        final STO sto = new STO(name);
        em.persist(sto);
        return sto;
    }

    @Transactional(readOnly = true)
    public STO findSTO(Long id) {
        final STO sto = em.find(STO.class, id);
        if(sto == null) {
            throw new EntityNotFoundException(String.format("STO with id [%s] is not found", id));
        }
        return sto;
    }

    @Transactional(readOnly = true)
    public List<STO> findAllSTOs() {
        return em.createQuery("select s from STO s", STO.class)
                .getResultList();
    }

    @Transactional
    public STO updateSTO(Long id, String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("STO name is null or empty");
        }
        final STO currentSTO = findSTO(id);
        currentSTO.setName(name);
        return em.merge(currentSTO);
    }

    @Transactional
    public STO setCar(Long id, Car car){
        final STO currentSTO = findSTO(id);
        currentSTO.setCar(car);
        return em.merge(currentSTO);
    }

    @Transactional
    public int carsCount(Long id) {
        final STO currentSTO = findSTO(id);
        return currentSTO.carsCount();
    }


    @Transactional
    public STO deleteSTO(Long id) {
        final STO currentSTO = findSTO(id);
        em.remove(currentSTO);
        return currentSTO;
    }

    @Transactional
    public void deleteAllSTOs() {
        em.createQuery("delete from STO").executeUpdate();
    }
}
