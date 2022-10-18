package com.semillero.crakruk.service;



import com.semillero.crakruk.dto.ComboDto;

import java.util.List;

public interface IComboService {

    ComboDto createCombo(ComboDto dto);

    ComboDto getById(Long id);

    void deleteCombo(Long id);

    ComboDto updateCombo(Long id, ComboDto dto);

    List<ComboDto> getAll();
}
