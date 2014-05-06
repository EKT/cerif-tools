/**
 * 
 */
package gr.ekt.cerif.features.multilingual;

import gr.ekt.cerif.entities.second.Event;
import gr.ekt.cerif.entities.second.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Holds the multi-lingual description of a event entity.
 * 
 */
@Entity
@Table(name="cfEventDescr", uniqueConstraints=@UniqueConstraint(columnNames={"cfEventId","cfLangCode","cfTrans"}))
public class EventDescription implements CerifMultipleLanguageFeature {
	
	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = -2355425010559882423L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * The event.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfEventId")
	private Event event;
	
	/**
	 * The language.
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name="cfLangCode")
	private Language language;
	
	/**
	 * The translation.
	 */
	@NotNull
	@Column(name="cfTrans")
	@Enumerated(EnumType.STRING)
	private Translation translation;
	
	/**
	 * The event description.
	 */
	@NotNull
	@Column(name="cfDescr", length=20000)
	private String description;
	
	/**
	 * Default Constructor
	 */
	public EventDescription() {
		
	}
	
	/**
	 * 
	 * @param event
	 * @param language
	 * @param translation
	 * @param description
	 */
	public EventDescription(Event event, Language language,
			Translation translation, String description) {
		this.event = event;
		this.language = language;
		this.translation = translation;
		this.description = description;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}


	/**
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}


	/**
	 * @return the translation
	 */
	public Translation getTranslation() {
		return translation;
	}


	/**
	 * @param translation the translation to set
	 */
	public void setTranslation(Translation translation) {
		this.translation = translation;
	}	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventDescription [id=" + id + ", language=" + language
				+ ", translation=" + translation + ", description="
				+ description + "]";
	}

}