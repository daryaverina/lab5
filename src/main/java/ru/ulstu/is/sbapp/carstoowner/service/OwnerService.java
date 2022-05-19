package ru.ulstu.is.sbapp.carstoowner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.carstoowner.controller.Owner.OwnerDto;
import ru.ulstu.is.sbapp.carstoowner.model.Owner;
import ru.ulstu.is.sbapp.carstoowner.repository.OwnerRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final Logger log = LoggerFactory.getLogger(OwnerService.class);
    private final OwnerRepository ownerRepository;
    private final ValidatorUtil validatorUtil;

    public OwnerService(OwnerRepository ownerRepository, ValidatorUtil validatorUtil) {
        this.ownerRepository = ownerRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Owner addOwner(String firstName, String lastname) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastname)) {
            throw new IllegalArgumentException("Owner FIO is null or empty");
        }
        final Owner owner = new Owner(firstName, lastname);
        validatorUtil.validate(owner);
        return ownerRepository.save(owner);
    }

    @Transactional
    public OwnerDto addOwner(OwnerDto ownerDto) {
        return new OwnerDto(addOwner(ownerDto.getFirstName(), ownerDto.getLastName()));
    }

    @Transactional(readOnly = true)
    public Owner findOwner(Long id) {
        final Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElseThrow(() -> new OwnerNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    @Transactional
    public Owner updateOwner(Long id, String firstName, String lastName) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Owner FIO is null or empty");
        }
        final Owner currentOwner = findOwner(id);
        currentOwner.setFirstName(firstName);
        currentOwner.setLastName(lastName);
        validatorUtil.validate(currentOwner);
        return ownerRepository.save(currentOwner);
    }

    @Transactional
    public OwnerDto updateOwner(OwnerDto ownerDto) {
        return new OwnerDto(updateOwner(ownerDto.getId(), ownerDto.getFirstName(), ownerDto.getLastName()));
    }

    @Transactional
    public Owner deleteOwner(Long id) {
        Owner currentOwner = findOwner(id);
        ownerRepository.delete(currentOwner);
        return currentOwner;
    }

    @Transactional
    public void deleteAllOwners() throws InOwnerFoundCarsException {
        List<Owner> owners = findAllOwners();
        for(var owner : owners){
            if(owner.getCars().size() > 0)
                throw new InOwnerFoundCarsException("У " + owner.getFirstName() + " " + owner.getLastName() + " есть автомобили");
        }
        ownerRepository.deleteAll();
    }
}
