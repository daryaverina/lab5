package ru.ulstu.is.sbapp.car.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.car.model.Car;
import ru.ulstu.is.sbapp.car.model.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OwnerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Owner addOwner(String firstName, String lastname) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastname)) {
            throw new IllegalArgumentException("Owner FIO is null or empty");
        }
        final Owner owner = new Owner(firstName, lastname);
        em.persist(owner);
        return owner;
    }

    @Transactional(readOnly = true)
    public Owner findOwner(Long id) {
        final Owner owner = em.find(Owner.class, id);
        if(owner == null) {
            throw new EntityNotFoundException(String.format("Owner with id [%s] is not found", id));
        }
        return owner;
    }

    @Transactional(readOnly = true)
    public List<Owner> findAllOwners() {
        return em.createQuery("select o from Owner o", Owner.class)
                .getResultList();
    }

    @Transactional
    public Owner updateOwner(Long id, String firstName, String lastName) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Owner FIO is null or empty");
        }
        final Owner currentOwner = findOwner(id);
        currentOwner.setFirstName(firstName);
        currentOwner.setLastName(lastName);
        return em.merge(currentOwner);
    }

    @Transactional
    public Owner setCar(Long id, Car car){
        final Owner currentOwner = findOwner(id);
        currentOwner.setCar(car);
        return em.merge(currentOwner);
    }

    @Transactional
    public int carsCount(Long id) {
        final Owner currentOwner = findOwner(id);
        return currentOwner.carsCount();
    }


    @Transactional
    public Owner deleteOwner(Long id) {
        final Owner currentOwner = findOwner(id);
        em.remove(currentOwner);
        return currentOwner;
    }

    @Transactional
    public void deleteAllOwners() {
        em.createQuery("delete from Owner").executeUpdate();
    }
}
