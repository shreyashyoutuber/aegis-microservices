package com.aegis.resourceservice.repository;

import com.aegis.resourceservice.model.Resource;
import com.aegis.resourceservice.model.ResourceStatus;
import com.aegis.resourceservice.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * ResourceRepository - JPA Repository for Resource entities.
 *
 * Provides CRUD operations and custom queries:
 * - findByStatus : Get resources by availability status
 * - findByType   : Get resources by type
 * - findByTypeAndStatus : Get resources by both type and status
 */
@Repository
@SuppressWarnings("null")
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    /**
     * Find all resources with a given status.
     *
     * @param status - the status to filter by
     * @return list of matching resources
     */
    List<Resource> findByStatus(ResourceStatus status);

    /**
     * Find all resources of a given type.
     *
     * @param type - the type to filter by
     * @return list of matching resources
     */
    List<Resource> findByType(ResourceType type);

    /**
     * Find all resources of a given type and status.
     *
     * @param type   - the type to filter by
     * @param status - the status to filter by
     * @return list of matching resources
     */
    List<Resource> findByTypeAndStatus(ResourceType type,
                                       ResourceStatus status);
}
