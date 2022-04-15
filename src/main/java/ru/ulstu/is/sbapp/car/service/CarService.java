package ru.ulstu.is.sbapp.car.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.car.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CarService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Car addCar(String model, float price) {
        if(!StringUtils.hasText(model)) {
            throw new IllegalArgumentException("Car model is null or empty");
        }
        final Car car = new Car(model, price);
        em.persist(car);
        return car;
    }

    @Transactional(readOnly = true)
    public Car findCar(Long id) {
        final Car car = em.find(Car.class, id);
        if(car == null) {
            throw new EntityNotFoundException(String.format("Car with id [%s] is not found", id));
        }
        return car;
    }

    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        return em.createQuery("select c from Car c", Car.class)
                .getResultList();
    }

    @Transactional
    public Car updateCar(Long id, String model, float price) {
        if (!StringUtils.hasText(model)) {
            throw new IllegalArgumentException("Car model is null or empty");
        }
        final Car currentCar = findCar(id);
        currentCar.setModel(model);
        currentCar.setPrice(price);
        return em.merge(currentCar);
    }

    @Transactional
    public Car deleteCar(Long id) {
        final Car currentCar = findCar(id);
        em.remove(currentCar);
        return currentCar;
    }

    @Transactional
    public void deleteAllCars() {
        em.createQuery("delete from Car").executeUpdate();
    }
}
