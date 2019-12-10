package by.epam.fitness.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

/**
 * The type Order information.
 */
public class OrderInformation extends Entity {

    private Long id;
    private BigDecimal cost;
    private Timestamp paymentData;
    private Date membershipEndDate;
    private Long clientId;
    private String cardNumber;

    /**
     * Instantiates a new Order information.
     *
     * @param id                the id
     * @param cost              the cost
     * @param paymentData       the payment data
     * @param membershipEndDate the membership end date
     * @param clientId          the client id
     * @param cardNumber        the card number
     */
    public OrderInformation(Long id, BigDecimal cost, Timestamp paymentData, Date membershipEndDate, Long clientId, String cardNumber) {
        this.id = id;
        this.cost = cost;
        this.paymentData = paymentData;
        this.membershipEndDate = membershipEndDate;
        this.clientId = clientId;
        this.cardNumber = cardNumber;
    }

    /**
     * Instantiates a new Order information.
     */
    public OrderInformation(){}

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets payment data.
     *
     * @return the payment data
     */
    public Timestamp getPaymentData() {
        return paymentData;
    }

    /**
     * Sets payment data.
     *
     * @param paymentData the payment data
     */
    public void setPaymentData(Timestamp paymentData) {
        this.paymentData = paymentData;
    }

    /**
     * Gets membership end date.
     *
     * @return the membership end date
     */
    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    /**
     * Sets membership end date.
     *
     * @param membershipEndDate the membership end date
     */
    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderInformation orderInformation = (OrderInformation) o;
        return  Objects.equals(getId(), orderInformation.getId()) &&
                Objects.equals(getCost(), orderInformation.getCost()) &&
                Objects.equals(getMembershipEndDate(), orderInformation.getMembershipEndDate()) &&
                Objects.equals(getPaymentData(), orderInformation.getPaymentData()) &&
                Objects.equals(getClientId(), orderInformation.getClientId()) &&
                Objects.equals(getCardNumber(), orderInformation.getCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getCost(),getMembershipEndDate(),getPaymentData(),getClientId(),getCardNumber());
    }
}

