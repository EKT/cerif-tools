package gr.ekt.cerif.services.link.person;

import gr.ekt.cerif.entities.link.person.Person_Prize;
import gr.ekt.cerif.services.base.PersonRepository;
import gr.ekt.cerif.services.second.PrizeRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A repository for links between persons and prizes.
 * 
 */
@Component
public class LinkPersonPrizeRepository {

	@PersistenceContext(unitName="cerif-persistence-unit")
	private EntityManager entityManager;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PrizeRepository prizeRepository;
	
	@Transactional
	public void save(Person_Prize entity) {
		if (entity.getPerson() == null || entity.getPrize() == null) {
			throw new IllegalArgumentException("Please provide both a person and a prize.");
		}
		if (entity.getPerson().getId() == null) {
			personRepository.save(entity.getPerson());
		}
		if (entity.getPrize().getId() == null) {
			prizeRepository.save(entity.getPrize());
		}
		entityManager.merge(entity);
	}
	
	@Transactional
	public void save(List<Person_Prize> entityList) {
		for (Person_Prize entity : entityList) {
			save(entity);
		}
	}
	
}
