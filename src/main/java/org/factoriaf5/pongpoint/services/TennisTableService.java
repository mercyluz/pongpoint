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

    public List<TennisTable> getAllTables() {
        return tennisTableRepository.findAll();
    }

    public Optional<TennisTable> getTableById(Long id) {
        return tennisTableRepository.findById(id);
    }

    public TennisTable createTennisTable(TennisTable tennisTable) {
        return tennisTableRepository.save(tennisTable);
    }

    public TennisTable updateTennisTable(Long id, TennisTable tennisTableDetails) {
        if (tennisTableRepository.existsById(id)) {
            tennisTableDetails.setId(id);
            return tennisTableRepository.save(tennisTableDetails);
        }
        return null;
    }

    public boolean deleteTennisTable(Long id) {
        if (tennisTableRepository.existsById(id)) {
            tennisTableRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
