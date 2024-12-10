package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.services.TennisTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TennisTableController {

    @Autowired
    private TennisTableService tennisTableService;

    // Crear una nueva mesa de tenis
    @PostMapping
    public ResponseEntity<TennisTable> createTennisTable(@RequestBody TennisTable tennisTable) {
        TennisTable newTable = tennisTableService.createTennisTable(tennisTable);
        return new ResponseEntity<>(newTable, HttpStatus.CREATED);
    }

    // Obtener todas las mesas de tenis
    @GetMapping
    public ResponseEntity<List<TennisTable>> getAllTennisTables() {
        List<TennisTable> tennisTables = tennisTableService.getAllTennisTables();
        return new ResponseEntity<>(tennisTables, HttpStatus.OK);
    }

    // Obtener una mesa de tenis por ID
    @GetMapping("/{tableId}")
    public ResponseEntity<TennisTable> getTennisTableById(@PathVariable Long tableId) {
        TennisTable tennisTable = tennisTableService.getTennisTableById(tableId);
        if (tennisTable == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tennisTable, HttpStatus.OK);
    }

    // Eliminar una mesa de tenis
    @DeleteMapping("/{tableId}")
    public ResponseEntity<Void> deleteTennisTable(@PathVariable Long tableId) {
        boolean isDeleted = tennisTableService.deleteTennisTable(tableId);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar una mesa de tenis
    @PutMapping("/{tableId}")
    public ResponseEntity<TennisTable> updateTennisTable(
            @PathVariable Long tableId, 
            @RequestBody TennisTable updatedTennisTable) {
        
        TennisTable tennisTable = tennisTableService.updateTennisTable(tableId, updatedTennisTable);
        if (tennisTable == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tennisTable, HttpStatus.OK);
    }
}
