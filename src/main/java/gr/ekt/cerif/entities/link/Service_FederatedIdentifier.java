/**
 * 
 */
package gr.ekt.cerif.entities.link;

import java.util.Date;

import gr.ekt.cerif.entities.infrastructure.Service;
import gr.ekt.cerif.entities.second.FederatedIdentifier;
import gr.ekt.cerif.features.semantics.Class;

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

/**
 * @author nhoussos
 *
 */
@Entity
@Table(name="cfSrv_FedId", uniqueConstraints=@UniqueConstraint(columnNames={"cfSrvId","cfFedId","cfClassId","cfStartDate","cfEndDate"}))
public class Service_FederatedIdentifier implements CerifLinkEntity {

	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = -6241876529284735348L;

	/**
	 * 
	 */
	public Service_FederatedIdentifier() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param service
	 * @param fedId
	 * @param theClass
	 * @param startDate
	 * @param endDate
	 * @param fraction
	 */
	public Service_FederatedIdentifier(Long id, Service service,
			FederatedIdentifier fedId, Class theClass, Date startDate,
			Date endDate, Double fraction) {
		super();
		this.id = id;
		this.service = service;
		this.fedId = fedId;
		this.theClass = theClass;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fraction = fraction;
	}

	/**
	 * The auto-generated id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * The service.
	 */
	@ManyToOne(optional=false) 
	@JoinColumn(name="cfSrvId")
	private Service service;
	
	/**
	 * The federated identifier.
	 */
	@ManyToOne(optional=false) 
	@JoinColumn(name="cfFedId")
	private FederatedIdentifier fedId;
	
	/**
	 * The Class.
	 */
	@ManyToOne(optional=false) 
	@JoinColumn(name="cfClassId")	
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
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the fedId
	 */
	public FederatedIdentifier getFedId() {
		return fedId;
	}

	/**
	 * @param fedId the fedId to set
	 */
	public void setFedId(FederatedIdentifier fedId) {
		this.fedId = fedId;
	}

	/**
	 * @return the theClass
	 */
	public Class getTheClass() {
		return theClass;
	}

	/**
	 * @param theClass the theClass to set
	 */
	public void setTheClass(Class theClass) {
		this.theClass = theClass;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the fraction
	 */
	public Double getFraction() {
		return fraction;
	}

	/**
	 * @param fraction the fraction to set
	 */
	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}	
}
