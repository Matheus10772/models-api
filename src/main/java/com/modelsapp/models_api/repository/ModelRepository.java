package com.modelsapp.models_api.repository;

import com.modelsapp.models_api.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    // Método para buscar uma modelo por nome
    public Optional<Model> findById(UUID id);

    // Método para buscar uma modelo por nome
    public Optional<Model> findByName(String name);
}
