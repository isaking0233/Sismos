package mx.ipn.escom.loginsystem.repository;

import mx.ipn.escom.loginsystem.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EarthquakeRepository extends JpaRepository<Earthquake, Long> {
    List<Earthquake> findByMagnitudeGreaterThanEqual(double magnitude);
}
