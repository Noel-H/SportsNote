package fr.noelh.sportsnoteapi.repository;

import fr.noelh.sportsnoteapi.model.WeightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRecordRepository extends JpaRepository<WeightRecord, Long> {
}
