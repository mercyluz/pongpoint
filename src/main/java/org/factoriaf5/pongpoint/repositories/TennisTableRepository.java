package org.factoriaf5.pongpoint.repositories;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisTableRepository extends JpaRepository<TennisTable, Long> {
    // JpaRepository ya tiene implementados métodos como findAll(), findById(), save(), deleteById(), etc.
}
