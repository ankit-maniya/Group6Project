package com.example.group6project

object CheckOutFormValidation {
    fun validateAddress(address: String): Boolean {
        // Implement your address validation logic
        return address.isNotEmpty()
    }

    fun validateMobileNumber(mobileNumber: String): Boolean {
        // Implement your mobile number validation logic
        return mobileNumber.isNotEmpty() && mobileNumber.length == 10
    }

    fun validateCreditCardDetails(name: String, expiryDate: String): Boolean {
        // Implement your credit card details validation logic
        return name.isNotEmpty() && expiryDate.isNotEmpty()
    }

    fun validateUserInfo(newName: String, email: String): Boolean {
        // Implement your user info validation logic
        return newName.isNotEmpty() && email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateCardNumber(cardNumber: String): Boolean {
        // Implement your card number validation logic
        return cardNumber.isNotEmpty() && cardNumber.length == 16
    }

    fun submitDataToFirebase(
        address: String,
        mobileNumber: String,
        name: String,
        expiryDate: String,
        email: String,
        cardNumber: String
    ): Boolean {
        // Validate each field
        return validateAddress(address) &&
                validateMobileNumber(mobileNumber) &&
                validateCreditCardDetails(name, expiryDate) &&
                validateUserInfo(name, email) &&
                validateCardNumber(cardNumber)
    }
}