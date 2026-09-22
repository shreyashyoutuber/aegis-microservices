package com.aegis.incidentservice.repository;

import com.aegis.incidentservice.model.Incident;
import com.aegis.incidentservice.model.IncidentStatus;
import com.aegis.incidentservice.model.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * IncidentRepository - Spring Data JPA repository for Incident entity.
 *
 * Provides built-in CRUD and custom query methods.
 */
@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    /**
     * Find all incidents by their current status.
     *
     * @param status - the status to filter by
     * @return list of matching incidents
     */
    List<Incident> findByStatus(IncidentStatus status);

    /**
     * Find all incidents by their type.
     *
     * @param type - the incident type to filter by
     * @return list of matching incidents
     */
    List<Incident> findByType(IncidentType type);

    /**
     * Find all incidents reported by a specific user.
     *
     * @param reportedBy - the username to search
     * @return list of incidents reported by this user
     */
    List<Incident> findByReportedBy(String reportedBy);
}
