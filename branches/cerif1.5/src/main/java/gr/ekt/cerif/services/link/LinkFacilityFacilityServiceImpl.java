package gr.ekt.cerif.services.link;

import gr.ekt.cerif.entities.link.Facility_Facility;
import gr.ekt.cerif.services.infrastructure.FacilityRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A repository for links between facilities.
 * 
 */
@Component
public class LinkFacilityFacilityServiceImpl implements LinkFacilityFacilityService{

	@PersistenceContext(unitName="cerif-persistence-unit")
	private EntityManager entityManager;
	
	@Autowired
	private FacilityRepository facilityRepository;

	@Override
	@Transactional
	public void save(Facility_Facility entity) {
		if (entity.getFacility1() == null || entity.getFacility2() == null) {
			throw new IllegalArgumentException("Please provide both facilities.");
		}
		if (entity.getFacility1().getId() == null) {
			facilityRepository.save(entity.getFacility1());
		}
		if (entity.getFacility2().getId() == null) {
			facilityRepository.save(entity.getFacility2());
		}
		entityManager.merge(entity);
	}
	
	@Override
	@Transactional
	public void save(List<? extends Facility_Facility> entities) {
		for (Facility_Facility entity : entities) {
			save(entity);
		}
	}
	
}