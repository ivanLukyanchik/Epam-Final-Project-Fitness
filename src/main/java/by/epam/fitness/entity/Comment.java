package by.epam.fitness.entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * The type Comment.
 */
public class Comment extends Entity {
    private Long id;
    private Long clientId;
    private Long coachId;
    private String commentContent;
    private Timestamp paymentData;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {}

    /**
     * Instantiates a new Comment.
     *
     * @param id             the id
     * @param clientId       the client id
     * @param coachId        the coach id
     * @param commentContent the comment content
     * @param paymentData    the payment data
     */
    public Comment(Long id, Long clientId, Long coachId, String commentContent, Timestamp paymentData) {
        this.id = id;
        this.clientId = clientId;
        this.coachId = coachId;
        this.commentContent = commentContent;
        this.paymentData = paymentData;
    }

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
    public void setId(Long id) {
        this.id = id;
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
     * Gets coach id.
     *
     * @return the coach id
     */
    public Long getCoachId() {
        return coachId;
    }

    /**
     * Sets coach id.
     *
     * @param coachId the coach id
     */
    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    /**
     * Gets comment content.
     *
     * @return the comment content
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * Sets comment content.
     *
     * @param commentContent the comment content
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return  Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getClientId(), comment.getClientId()) &&
                Objects.equals(getCoachId(), comment.getCoachId()) &&
                Objects.equals(getCommentContent(), comment.getCommentContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getClientId(),getCoachId(),getCommentContent());
    }
}
