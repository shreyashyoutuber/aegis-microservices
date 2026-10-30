package com.aegis.responseservice.repository;

import com.aegis.responseservice.model.Dispatch;
import com.aegis.responseservice.model.DispatchStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DispatchRepository - Spring Data JPA repository for Dispatch entity.
 *
 * Provides CRUD and custom query methods for dispatch records.
 */
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

    /**
     * Find all dispatches by status.
     *
     * @param status - the dispatch status to filter by
     * @return list of matching dispatches
     */
    List<Dispatch> findByStatus(DispatchStatus status);

    /**
     * Find all dispatches for a specific incident.
     *
     * @param incidentId - the incident ID
     * @return list of dispatches for that incident
     */
    List<Dispatch> findByIncidentId(Long incidentId);

    /**
     * Find all dispatches for a specific resource.
     *
     * @param resourceId - the resource ID
     * @return list of dispatches for that resource
     */
    List<Dispatch> findByResourceId(Long resourceId);
}
