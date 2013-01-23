package gr.ekt.cerif.services.link.person;

import java.util.List;

import gr.ekt.cerif.entities.base.Person;
import gr.ekt.cerif.entities.link.person.Person_Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * A repository for links between persons.
 * 
 */
@Component
public interface LinkPersonPersonRepository extends CrudRepository<Person_Person, Long> {
	
	List<Person_Person> findByPerson1(Person person);
	
	List<Person_Person> findByPerson2(Person person);
	
}

