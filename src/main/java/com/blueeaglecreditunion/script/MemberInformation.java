package com.blueeaglecreditunion.script;


/**
 * ADT to hold the member information
 */
public class MemberInformation {

    private String cardPrefix;
    private String cardStatus;
    private String cardNumber;
    private String cardExpirationDate;
    private String memberNumber;
    private String primaryAccountNumber;
    private String statusCode;
    private String memberName;
    private String memberPhoneNumber;
    private String primaryAddressLineOne;
    private String primaryAddressLineTwo;
    private String city;
    private String state;
    private String postalCode;
    private String primaryCountry;
    private String description;
    private String dateCardIssued;
    private String email;
    private int accountNumber;
    private String lastActivityDate;

    public MemberInformation() {

    }

    public MemberInformation(String cardPrefix, String cardNumber, String cardExpirationDate, String memberNumber, String primaryAccountNumber, String cardStatus, String statusCode1, String name, String memberPhoneNumber, String primaryAddressLineOne, String primaryAddressLineTwo, String city, String state, String postalCode, String primaryCountry, int statusCode, String description, String dateCardIssued, String email, int accountNumber, String lastActivityDate){
        this.cardPrefix = cardPrefix;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.memberNumber = memberNumber;
        this.primaryAccountNumber = primaryAccountNumber;
        this.cardStatus = cardStatus;
        this.statusCode = statusCode1;
        this.memberName = name;
        this.memberPhoneNumber = memberPhoneNumber;
        this.primaryAddressLineOne = primaryAddressLineOne;
        this.primaryAddressLineTwo = primaryAddressLineTwo;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.primaryCountry = primaryCountry;
        this.description = description;
        this.dateCardIssued = dateCardIssued;
        this.email = email;
        this.accountNumber = accountNumber;
        this.lastActivityDate = lastActivityDate;
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public String getPrimaryAddressLineOne() {
        return primaryAddressLineOne;
    }

    public void setPrimaryAddressLineOne(String primaryAddressLineOne) {
        this.primaryAddressLineOne = primaryAddressLineOne;
    }

    public String getPrimaryAddressLineTwo() {
        return primaryAddressLineTwo;
    }

    public void setPrimaryAddressLineTwo(String primaryAddressLineTwo) {
        this.primaryAddressLineTwo = primaryAddressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrimaryCountry() {
        return primaryCountry;
    }

    public void setPrimaryCountry(String primaryCountry) {
        this.primaryCountry = primaryCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCardIssued() {
        return dateCardIssued;
    }

    public void setDateCardIssued(String dateCardIssued) {
        this.dateCardIssued = dateCardIssued;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }
}
