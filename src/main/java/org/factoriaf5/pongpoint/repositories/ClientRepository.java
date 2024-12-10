package org.factoriaf5.pongpoint.repositories;

import org.factoriaf5.pongpoint.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}


