package com.database.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

//mark class as an Entity   
@javax.persistence.Entity
// defining class name as Table name
@Table
public class Entity {
    @Id
    @Column
    private String clientId;
    @Column
    private String cardNumber;
    @Column
    private Double transactionAmount;
    @Column
    private Date transactionDate;

    /**
     * @return the clientId
     */
    @ApiModelProperty ( required = true, value = "" )
    @NotNull
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId( String clientId ) {
        this.clientId = clientId;
    }

    /**
     * @return the cardNumber
     */
    @ApiModelProperty ( required = true, value = "" )
    @NotNull
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber( String cardNumber ) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the transactionAmount
     */
    @ApiModelProperty ( required = true, value = "" )
    @NotNull
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @param transactionAmount the transactionAmount to set
     */
    public void setTransactionAmount( Double transactionAmount ) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @return the transactionDate
     */
    @ApiModelProperty ( required = true, value = "" )
    @NotNull
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate( Date transactionDate ) {
        this.transactionDate = transactionDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash( cardNumber, clientId );
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Entity other = (Entity)obj;
        return Objects.equals( cardNumber, other.cardNumber ) && Objects.equals( clientId, other.clientId );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Entity [clientId=" );
        builder.append( clientId );
        builder.append( ", cardNumber=" );
        builder.append( cardNumber );
        builder.append( ", transactionAmount=" );
        builder.append( transactionAmount );
        builder.append( ", transactionDate=" );
        builder.append( transactionDate );
        builder.append( "]" );
        return builder.toString();
    }

}
