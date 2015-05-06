# CERIF JPA Persistence #

CERIF JPA Persistence is a Java persistence library that provides all needed tools for accessing CERIF<sup>1</sup> compliant databases. Its fundamental technologies are the Java Persistence API (JPA) and the Spring framework, whereas Maven is used for automation management.

The library is being used in CERIF-based production systems such as systems utilized to provide services of the Hellenic National Information System of Research and Technology<sup>2</sup>, the EuroRIs-Net+ Observatory EuroRIs-Net+<sup>3</sup> and the PAERIP Inventory<sup>4</sup>. It has been successfully tested in various versions of both MySQL and PostgreSQL databases.

The library consists of two modules:

## cerif-jpa-model ##

This is the actual data model which includes the entities of CERIF.

The package structure follows the CERIF conceptual structure:
  * gr.ekt.cerif.entities – Entity Types
    * gr.ekt.cerif.entities.base – Base Entities
    * gr.ekt.cerif.entities.result – Result Entities
    * gr.ekt.cerif.entities.second – 2nd Level Entities
  * gr.ekt.cerif.features - Features

A fundamental characteristic is that all primary keys are simple; there are no compound primary keys – where composite keys are specified in CERIF candidate composite key are employed in addition to a separate, single primary key.

From a technological point of view, pure, standards-compliant, RDBMS independent JPA annotations are used for the object to relational mapping specification.

The use of Spring is only required in order to employ the functionality provided by the _cerif-jpa-services_ module. Of course, this module can be easily deployed into an EJB environment, by removing the  Spring dependencies from pom.xml and updating the transaction type to “JTA” in persistence.xml.


## cerif-jpa-services ##

This module provides ready-to-use functionality for common persistence needs. Its basic technology is Spring Data – JPA<sup>5</sup>, which makes it easy to implement JPA based repositories.

It defines a common object-oriented structure for every entity of the model, as depicted in the following image.

![http://cerif-jpa-persistence.googlecode.com/svn/trunk/CERIF%201.6/diagram.png](http://cerif-jpa-persistence.googlecode.com/svn/trunk/CERIF%201.6/diagram.png)

Let us consider the entity Person:
  * PersonRepository is the service interface.
  * PersonRepositoryImpl is the implementation of the service interface.
  * PersonCrudRepository is the Spring Data CrudRepository.

This object-oriented scheme facilitates the implementation and usage of database related functions through a delegation process.

Queries written in JPQL can be included into XXXCrudRepository interfaces using JPA’s @Query annotation along with the functions’ signature related with each query. XXXCrudRepository interfaces extend Spring’s CrudRepository interface which enables the usage of annotation based queries and basic CRUD functions. The _spring-data_ package provides a series of ready to use functions through the CrudRepository interface<sup>6</sup>.

By properly forming a function’s name, a SELECT query can be used without the need for actually implementing the query itself<sup>7</sup>. These functions also need to be included in XXXCrudRepository interfaces.

Custom functions with more complex logic along with basic CRUD functions that require a different approach compared to the ones in XXXCrudRepository, can be included in XXXRepositoryImpl classes. These classes implement the actual delegation scheme.

The XXXRepository interfaces provide access to all aforementioned functions. In XXXRepositoryImpl classes the implemented functions of XXXRepository call the functions of the appropriate XXXCrudRepository or they provide a custom implementation. The _cerif-jpa-services_ package also implements a mechanism for accessing these custom functions and queries from an external client. CRUD operations are implemented so as to be type driven and generic, hence the programmer only uses a single function per operation instead of a different one per data type.


## Example code ##

```
//create a project with a project title
@Autowired
private PersistenceService service;

//in a function
Project proj = new Project();
proj.setAcronym("ACRO");
service.save(proj);
ProjectTitle projt = new ProjectTitle(proj, Language.ENGLISH, Translation.HUMAN, "Title");
service.save(projt);
```

```
//Find an Organisation Unit and edit it
//inside OrganisationUnitCrudRepository interface in services package 
@Query(value= "select o from gr.ekt.cerif.entities.base.OrganisationUnit o where o.id = ?1")
OrganisationUnit findById(Long id);

//inside OrganisationUnitRepository interface in services package 
OrganisationUnit findById(Long id);

//inside OrganisationUnitRepositoryImpl class in services package
@Autowired
private OrganisationUnitCrudRepository organisationUnitCrudRepository;

public OrganisationUnit findById(Long id) {
    return organisationUnitCrudRepository.findById(id);
}

//in a function
OrganisationUnit org = service.getBaseService().getOrganisationUnitRepository().findById((long)100);
if (org!=null) {
    org.setAcronym("ACRO");
    service.save(org);
}
```


  1. http://www.eurocris.org/Index.php?page=CERIFreleases&t=1
  1. http://www.epset.gr/en
  1. http://observatory.euroris-net.eu
  1. http://inventory.paerip.org
  1. http://projects.spring.io/spring-data-jpa
  1. http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/api/org/springframework/data/repository/CrudRepository.html
  1. http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation