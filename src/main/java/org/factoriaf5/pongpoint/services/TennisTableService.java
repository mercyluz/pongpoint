package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.repositories.TennisTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TennisTableService {

    @Autowired
    private TennisTableRepository tennisTableRepository;

    // Crear una nueva mesa de tenis
    public TennisTable createTennisTable(TennisTable tennisTable) {
        return tennisTableRepository.save(tennisTable);
    }

    // Obtener todas las mesas de tenis
    public List<TennisTable> getAllTennisTables() {
        return tennisTableRepository.findAll();
    }

    // Obtener una mesa de tenis por ID
    public TennisTable getTennisTableById(Long id) {
        Optional<TennisTable> tennisTable = tennisTableRepository.findById(id);
        return tennisTable.orElse(null);
    }

    // Eliminar una mesa de tenis
    public boolean deleteTennisTable(Long id) {
        if (tennisTableRepository.existsById(id)) {
            tennisTableRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar una mesa de tenis
    public TennisTable updateTennisTable(Long id, TennisTable updatedTennisTable) {
        if (tennisTableRepository.existsById(id)) {
            updatedTennisTable.setId(id);
            return tennisTableRepository.save(updatedTennisTable);
        }
        return null;
    }
}
