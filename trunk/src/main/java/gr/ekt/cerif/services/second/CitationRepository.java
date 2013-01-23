/**
 * 
 */
package gr.ekt.cerif.services.second;

import gr.ekt.cerif.entities.second.Citation;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for citations.
 * 
 */
public interface CitationRepository extends CrudRepository<Citation, Long> {

}
