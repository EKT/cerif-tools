/**
 * 
 */
package gr.ekt.cerif.entities.link.project;

import gr.ekt.cerif.entities.base.Project;
import gr.ekt.cerif.entities.infrastructure.Facility;
import gr.ekt.cerif.entities.link.CerifLinkEntity;
import gr.ekt.cerif.entities.second.Currency;
import gr.ekt.cerif.features.semantics.Class;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Links a project with a facility.
 * 
 */
@Entity
@Table(name="cfProj_Facil", uniqueConstraints=@UniqueConstraint(columnNames={"cfProjId", "cfFacilId", "cfClassId", "cfStartDate", "cfEndDate"}) )
public class Project_Facility implements CerifLinkEntity {
	
	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = -3052891621307942145L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * The project.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfProjId")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Project project;
	
	/**
	 * The facility.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfFacilId")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Facility facility;
	
	/**
	 * The class.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfClassId")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Class theClass;
	
	/**
	 * The start date.
	 */
	@NotNull
	@Column (name="cfStartDate")
	private Date startDate;
		
	/**
	 * The end date.
	 */
	@NotNull
	@Column (name="cfEndDate")
	private Date endDate;
	
	/**
	 * The fraction.
	 */
	@Column(name="cfFraction")
	private Double fraction;
	
	/**
	 * The price.
	 */
	@Column(name="cfPrice")
	private Double price;

	/**
	 * The currency code.
	 */
	@ManyToOne
	@JoinColumn(name="cfCurrCode")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Currency currency;

	/**
	 * The availability.
	 */
	@Column(name="cfAvailability")
	private String availability;

	/**
	 * The conditions.
	 */
	@Column(name="cfConditions")
	private String conditions;

	
	/**
	 * Default Constructor
	 */
	public Project_Facility() {
		
	}
	
	/**
	 * 
	 * @param project
	 * @param facility
	 * @param theClass
	 * @param startDate
	 * @param endDate
	 * @param fraction
	 * @param price
	 * @param currency
	 * @param availability
	 * @param conditions
	 */
	public Project_Facility(Project project, Facility facility,
			Class theClass, Date startDate, Date endDate, Double fraction,
			Double price, Currency currency, String availability,
			String conditions) {
		this.project = project;
		this.facility = facility;
		this.theClass = theClass;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fraction = fraction;
		this.price = price;
		this.currency = currency;
		this.availability = availability;
		this.conditions = conditions;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the start date.
	 * @return the start date.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 * @param startDate the start date.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * @return the end date.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 * @param endDate the end date.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the fraction.
	 * @return the fraction.
	 */
	public Double getFraction() {
		return fraction;
	}

	/**
	 * Sets the fraction.
	 * @param fraction the fraction.
	 */
	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}

	/**
	 * Returns the project.
	 * @return the project.
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 * @param project the project.
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns the class.
	 * @return the class.
	 */
	public Class getTheClass() {
		return theClass;
	}

	/**
	 * Sets the class.
	 * @param theClass the class.
	 */
	public void setTheClass(Class theClass) {
		this.theClass = theClass;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	
}
