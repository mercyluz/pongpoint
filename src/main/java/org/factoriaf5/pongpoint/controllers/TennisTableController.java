package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.services.TennisTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Asegúrate de que esté marcada como RestController
@RequestMapping("/api/tables") // La ruta base para todas las peticiones
public class TennisTableController {

    @Autowired
    private TennisTableService tennisTableService;

    @GetMapping
    public List<TennisTable> getAllTables() {
        return tennisTableService.getAllTables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTableById(@PathVariable Long id) {
        Optional<TennisTable> tennisTable = tennisTableService.getTableById(id);

        if (tennisTable.isPresent()) {
            return ResponseEntity.ok(tennisTable.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found");
        }
    }

    @PostMapping
    public ResponseEntity<TennisTable> createTennisTable(@RequestBody TennisTable tennisTable) {
        TennisTable createdTable = tennisTableService.createTennisTable(tennisTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTennisTable(@PathVariable Long id, @RequestBody TennisTable tennisTableDetails) {
        TennisTable updatedTable = tennisTableService.updateTennisTable(id, tennisTableDetails);
        
        if (updatedTable != null) {
            return ResponseEntity.ok(updatedTable);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found for update");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTennisTable(@PathVariable Long id) {
        boolean isDeleted = tennisTableService.deleteTennisTable(id);
        
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Table deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found for deletion");
        }
    }
}
