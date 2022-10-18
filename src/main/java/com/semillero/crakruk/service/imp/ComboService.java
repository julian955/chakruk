package com.semillero.crakruk.service.imp;

import com.semillero.crakruk.dto.ComboDto;
import com.semillero.crakruk.exeption.EntityNotFoundException;
import com.semillero.crakruk.mapper.ComboMapper;
import com.semillero.crakruk.model.Combo;
import com.semillero.crakruk.repository.ComboRepository;
import com.semillero.crakruk.service.IComboService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ComboService implements IComboService {

    private final ComboRepository comboRepository;
    private final ComboMapper mapper;

    @Override
    @Transactional
    public ComboDto createCombo(ComboDto dto) {
        Combo combo = mapper.toEntity(dto);
        comboRepository.save(combo);
        return mapper.toDto(combo);
    }

    @Override
    public ComboDto getById(Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Combo", "id", id));
        return mapper.toDto(combo);
    }

    @Override
    public void deleteCombo(Long id) {

        if (!comboRepository.existsById(id)){
            throw new EntityNotFoundException("Combo", "id", id);
        }
        comboRepository.deleteById(id);
    }

    public Combo findByID(Long id){
        Optional<Combo> combo = comboRepository.findById(id);
        if(combo.isEmpty())throw new EntityNotFoundException("Combo", "id", id);
        return combo.get();
    }

    @Override
    public ComboDto updateCombo(Long id, ComboDto dto) {
        Combo combo = this.findByID(id);
        mapper.update(combo,dto);
        comboRepository.save(combo);
        return mapper.toDto(combo);
    }

    @Override
    public List<ComboDto> getAll() {
        return mapper.toDtoList(comboRepository.findAll());
    }
}
