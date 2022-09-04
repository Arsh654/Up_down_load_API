package com.buraak.orageServiceDB.Repository;

import com.buraak.orageServiceDB.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepo extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String fileName);
}
