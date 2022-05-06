package co.edu.icesi.colmenares.model.prchasing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import co.edu.icesi.colmenares.validation.BusinessentityExist;
import lombok.Data;

/**
 * The persistent class for the vendor database table.
 *
 */
@Entity
@Data
@NamedQuery(name = "Vendor.findAll", query = "SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name = "VENDOR_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "VENDOR_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENDOR_BUSINESSENTITYID_GENERATOR")
	private java.lang.Integer vendorid;

	@BusinessentityExist
	private int businessentityid;
	
	public int getBusinessentityid() {
		return businessentityid;
	}

	private String accountnumber;

	private String activeflag;

	@Min(1)
	private int creditrating;

	private Timestamp modifieddate;

	@NotBlank
	private String name;

	private String preferredvendorstatus;

	@Pattern(regexp = "^https.*$",message ="url must starts with https")
	private String purchasingwebserviceurl;

	// bi-directional many-to-one association to Productvendor
	@OneToMany(mappedBy = "vendor")
	private List<Productvendor> productvendors;

	// bi-directional many-to-one association to Purchaseorderheader
	@OneToMany(mappedBy = "vendor")
	private List<Purchaseorderheader> purchaseorderheaders;

	public Vendor() {
	}

	public Productvendor addProductvendor(Productvendor productvendor) {
		getProductvendors().add(productvendor);
		productvendor.setVendor(this);

		return productvendor;
	}

	public Purchaseorderheader addPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		getPurchaseorderheaders().add(purchaseorderheader);
		purchaseorderheader.setVendor(this);

		return purchaseorderheader;
	}
	
	public java.lang.Integer getVendorid() {
		return vendorid;
	}
	
	public void setVendorid(java.lang.Integer vendorid) {
		this.vendorid = vendorid;
	}
	
	public void setBusinessentityid(int businessentityid) {
		this.businessentityid = businessentityid;
	}
	

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public String getActiveflag() {
		return this.activeflag;
	}

	public int getCreditrating() {
		return this.creditrating;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public String getPreferredvendorstatus() {
		return this.preferredvendorstatus;
	}

	public List<Productvendor> getProductvendors() {
		return this.productvendors;
	}

	public List<Purchaseorderheader> getPurchaseorderheaders() {
		return this.purchaseorderheaders;
	}

	public String getPurchasingwebserviceurl() {
		return this.purchasingwebserviceurl;
	}

	public Productvendor removeProductvendor(Productvendor productvendor) {
		getProductvendors().remove(productvendor);
		productvendor.setVendor(null);

		return productvendor;
	}

	public Purchaseorderheader removePurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		getPurchaseorderheaders().remove(purchaseorderheader);
		purchaseorderheader.setVendor(null);

		return purchaseorderheader;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public void setActiveflag(String activeflag) {
		this.activeflag = activeflag;
	}

	public void setCreditrating(int creditrating) {
		
		this.creditrating = creditrating;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public void setPreferredvendorstatus(String preferredvendorstatus) {
		this.preferredvendorstatus = preferredvendorstatus;
	}

	public void setProductvendors(List<Productvendor> productvendors) {
		this.productvendors = productvendors;
	}

	public void setPurchaseorderheaders(List<Purchaseorderheader> purchaseorderheaders) {
		this.purchaseorderheaders = purchaseorderheaders;
	}

	public void setPurchasingwebserviceurl(String purchasingwebserviceurl) {
		this.purchasingwebserviceurl = purchasingwebserviceurl;			
	}

}