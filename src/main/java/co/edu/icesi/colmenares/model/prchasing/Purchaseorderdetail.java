package co.edu.icesi.colmenares.model.prchasing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.edu.icesi.colmenares.validation.PurchaseorderheaderExist;
import lombok.Data;

/**
 * The persistent class for the purchaseorderdetail database table.
 *
 */
@Entity
@NamedQuery(name = "Purchaseorderdetail.findAll", query = "SELECT p FROM Purchaseorderdetail p")
public class Purchaseorderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private PurchaseorderdetailPK id;
//	
	@Id
	@SequenceGenerator(name = "PURCHASEORDERDETAIL_ID_GENERATOR", allocationSize = 1, sequenceName = "PURCHASEORDERDETAIL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PURCHASEORDERDETAIL_ID_GENERATOR")
	private java.lang.Integer id;

	private Timestamp duedate;

	private Timestamp modifieddate;

	@Min(1)
	private int orderqty;

	private int productid;

	private BigDecimal receivedqty;

	private BigDecimal rejectedqty;

	@NotNull
	@Min(1)
	private BigDecimal unitprice;

	// bi-directional many-to-one association to Purchaseorderheader
	@ManyToOne
	@JoinColumn(name = "purchaseorderid")
	@NotNull
	@PurchaseorderheaderExist
	private Purchaseorderheader purchaseorderheader;

	public Purchaseorderdetail() {
	}

	public Timestamp getDuedate() {
		return this.duedate;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public int getOrderqty() {
		return this.orderqty;
	}

	public int getProductid() {
		return this.productid;
	}

	public Purchaseorderheader getPurchaseorderheader() {
		return this.purchaseorderheader;
	}

	public BigDecimal getReceivedqty() {
		return this.receivedqty;
	}

	public BigDecimal getRejectedqty() {
		return this.rejectedqty;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setOrderqty(int orderqty) {
		
		this.orderqty = orderqty;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public void setPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		this.purchaseorderheader = purchaseorderheader;
	}

	public void setReceivedqty(BigDecimal receivedqty) {
		this.receivedqty = receivedqty;
	}

	public void setRejectedqty(BigDecimal rejectedqty) {
		this.rejectedqty = rejectedqty;
	}

	public void setUnitprice(BigDecimal unitprice) {
		
		this.unitprice = unitprice;
	}

}