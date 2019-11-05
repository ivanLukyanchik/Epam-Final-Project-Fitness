package by.epam.fitness.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class OrderInformation extends Entity {

    private Long id;
    private BigDecimal cost;
    private Timestamp paymentData;
    private Date membershipEndDate;
    private Long clientId;
    private String cardNumber;

    public OrderInformation(Long id, BigDecimal cost, Timestamp paymentData, Date membershipEndDate, Long clientId, String cardNumber) {
        this.id = id;
        this.cost = cost;
        this.paymentData = paymentData;
        this.membershipEndDate = membershipEndDate;
        this.clientId = clientId;
        this.cardNumber = cardNumber;
    }

    public OrderInformation(){}

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Timestamp getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(Timestamp paymentData) {
        this.paymentData = paymentData;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

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

