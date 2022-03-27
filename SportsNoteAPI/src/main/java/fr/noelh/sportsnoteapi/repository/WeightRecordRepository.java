package fr.noelh.sportsnoteapi.repository;

import fr.noelh.sportsnoteapi.model.WeightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeightRecordRepository extends JpaRepository<WeightRecord, Long> {
    Optional<WeightRecord> findByDate(LocalDate localDate);
    WeightRecord getByDate(LocalDate localDate);
    boolean existsByDate(LocalDate localDate);
}
