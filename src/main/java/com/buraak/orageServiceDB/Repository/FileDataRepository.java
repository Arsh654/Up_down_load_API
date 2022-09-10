package com.buraak.orageServiceDB.Repository;

import com.buraak.orageServiceDB.Entity.FileData;
import com.buraak.orageServiceDB.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Long> {

    Optional<FileData> findByName(String fileName);
}
