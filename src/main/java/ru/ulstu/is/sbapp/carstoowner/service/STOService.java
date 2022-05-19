package ru.ulstu.is.sbapp.carstoowner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.carstoowner.controller.STO.STODto;
import ru.ulstu.is.sbapp.carstoowner.model.STO;
import ru.ulstu.is.sbapp.carstoowner.repository.STORepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class STOService {
    private final Logger log = LoggerFactory.getLogger(STOService.class);
    private final STORepository stoRepository;
    private final ValidatorUtil validatorUtil;

    public STOService(STORepository stoRepository, ValidatorUtil validatorUtil) {
        this.stoRepository = stoRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public STO addSTO(String name) {
        if(!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("STO name is null or empty");
        }
        final STO sto = new STO(name);
        validatorUtil.validate(sto);
        return stoRepository.save(sto);
    }

    @Transactional
    public STODto addSTO(STODto stoDto) {
        return new STODto(addSTO(stoDto.getName()));
    }

    @Transactional(readOnly = true)
    public STO findSTO(Long id) {
        final Optional<STO> sto = stoRepository.findById(id);
        return sto.orElseThrow(() -> new STONotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<STO> findAllSTOs() {
        return stoRepository.findAll();
    }

    @Transactional
    public STO updateSTO(Long id, String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("STO name is null or empty");
        }
        final STO currentSTO = findSTO(id);
        currentSTO.setName(name);
        validatorUtil.validate(currentSTO);
        return stoRepository.save(currentSTO);
    }

    @Transactional
    public STODto updateSTO(STODto stoDto) {
        return new STODto(updateSTO(stoDto.getId(), stoDto.getName()));
    }

    @Transactional
    public STO deleteSTO(Long id) {
        final STO currentSTO = findSTO(id);
        stoRepository.delete(currentSTO);
        return currentSTO;
    }

    @Transactional
    public void deleteAllSTOs() throws InSTOfoundCarsException{
        var stos = findAllSTOs();
        for (var sto : stos) {
            if (sto.getCars().size() > 0) {
                throw new InSTOfoundCarsException("В сервисе " + sto.getName() + " есть автомобили");
            }
        }
        stoRepository.deleteAll();
    }
}
