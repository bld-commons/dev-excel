package bld.generator.report.db2.persistence.domain;
import java.util.Date;

import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * The Class UserEntity.
 */
@Entity
@Table(name = "user_entity",uniqueConstraints = {@UniqueConstraint(columnNames = {"realm_id","username"}),@UniqueConstraint(columnNames = {"realm_id","email_constraint"})})
public class UserEntity {
    
 
    
    /** The email. */
    @Column(name = "email", length = 255)
    private String email;
    
    /** The email constraint. */
    @Column(name = "email_constraint", length = 255)
    private String emailConstraint;
    
    /** The email verified. */
    @Column(name = "email_verified")
    @NotNull
    private boolean emailVerified;
    
    /** The enabled. */
    @Column(name = "enabled")
    @NotNull
    private boolean enabled;
    
    /** The federation link. */
    @Column(name = "federation_link", length = 255)
    private String federationLink;
    
    /** The first name. */
    @Column(name = "first_name", length = 255)
    private String firstName;
    
    /** The last name. */
    @Column(name = "last_name", length = 255)
    private String lastName;
    
    /** The realm id. */
    @Column(name = "realm_id", length = 255)
    private String realmId;
    
    /** The username. */
    @Column(name = "username", length = 255)
    private String username;
    
    /** The created timestamp. */
    @Column(name = "created_timestamp",updatable = false)
    private Long createdTimestamp;
    
    /** The service account client link. */
    @Column(name = "service_account_client_link", length = 255)
    private String serviceAccountClientLink;
    
    /** The not before. */
    @Column(name = "not_before")
    @NotNull
    private Integer notBefore;
    
   
    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the email constraint.
     *
     * @return the email constraint
     */
    public String getEmailConstraint() {
        return emailConstraint;
    }
    
    /**
     * Sets the email constraint.
     *
     * @param emailConstraint the new email constraint
     */
    public void setEmailConstraint(String emailConstraint) {
        this.emailConstraint = emailConstraint;
    }
    
    /**
     * Checks if is email verified.
     *
     * @return true, if is email verified
     */
    public boolean isEmailVerified() {
        return emailVerified;
    }
    
    /**
     * Sets the email verified.
     *
     * @param emailVerified the new email verified
     */
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    
    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Sets the enabled.
     *
     * @param enabled the new enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * Gets the federation link.
     *
     * @return the federation link
     */
    public String getFederationLink() {
        return federationLink;
    }
    
    /**
     * Sets the federation link.
     *
     * @param federationLink the new federation link
     */
    public void setFederationLink(String federationLink) {
        this.federationLink = federationLink;
    }
    
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Gets the realm id.
     *
     * @return the realm id
     */
    public String getRealmId() {
        return realmId;
    }
    
    /**
     * Sets the realm id.
     *
     * @param realmId the new realm id
     */
    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }
    
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Gets the created timestamp.
     *
     * @return the created timestamp
     */
    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }
    
    /**
     * Sets the created timestamp.
     *
     * @param createdTimestamp the new created timestamp
     */
    public void setCreatedTimestamp(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    
    /**
     * Gets the service account client link.
     *
     * @return the service account client link
     */
    public String getServiceAccountClientLink() {
        return serviceAccountClientLink;
    }
    
    /**
     * Sets the service account client link.
     *
     * @param serviceAccountClientLink the new service account client link
     */
    public void setServiceAccountClientLink(String serviceAccountClientLink) {
        this.serviceAccountClientLink = serviceAccountClientLink;
    }
    
    /**
     * Gets the not before.
     *
     * @return the not before
     */
    public Integer getNotBefore() {
        return notBefore;
    }
    
    /**
     * Sets the not before.
     *
     * @param notBefore the new not before
     */
    public void setNotBefore(Integer notBefore) {
        this.notBefore = notBefore;
    }
    
    /** The id. */
    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("credentials", "federatedIdentities", "userAttributes", "userConsents", "userGroupMemberships", "userRequiredActions", "userRoleMappings").toString();
    }
	
	/**
	 * Instantiates a new user entity.
	 */
	public UserEntity() {
		super();
		this.enabled=true;
		this.emailVerified=false;
		this.createdTimestamp=(new Date()).getTime();
		this.notBefore=0;
	}
    
    
}
