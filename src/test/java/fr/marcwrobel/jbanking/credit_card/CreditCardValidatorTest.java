package fr.marcwrobel.jbanking.credit_card;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardValidatorTest {

  @Test
  void masterCardIsValid() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("5555555555554444");
    //Execute
    boolean isValid = creditCardValidator.isValidCreditCard();
    //Verify
    assertEquals(true, isValid);
  }
  @Test
  void masterCardHasValidChecksum() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("5555555555554444");
    //Execute
    boolean isValid = creditCardValidator.hasChecksumValid();
    //Verify
    assertEquals(true, isValid);
  }

  @Test
  void masterCardIsInvalid() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("55555555555544455");
    //Execute
    boolean isValid = creditCardValidator.isValidCreditCard();
    //Verify
    assertEquals(false, isValid);
  }
  @Test
  void masterCardHasInvalidChecksum() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("5555555555554445");
    //Execute
    boolean isValid = creditCardValidator.hasChecksumValid();
    //Verify
    assertEquals(false, isValid);
  }


  @Test
  void visaCardIsValid() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("4111111111111111");
    //Execute
    boolean isValid = creditCardValidator.isValidCreditCard();
    //Verify
    assertEquals(true, isValid);
  }
  @Test
  void visaCardHasValidChecksum() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("4111111111111111");
    //Execute
    boolean isValid = creditCardValidator.hasChecksumValid();
    //Verify
    assertEquals(true, isValid);
  }

  @Test
  void visaCardIsInvalid() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("41111111111111111");
    //Execute
    boolean isValid = creditCardValidator.isValidCreditCard();
    //Verify
    assertEquals(false, isValid);
  }
  @Test
  void visaCardHasInvalidChecksum() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("4111111111111119");
    //Execute
    boolean isValid = creditCardValidator.hasChecksumValid();
    //Verify
    assertEquals(false, isValid);
  }

  @Test
  void creditCardIsInvalid() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("4111a111111111111");
    //Execute
    boolean isValid = creditCardValidator.isValidCreditCard();
    //Verify
    assertEquals(false, isValid);
  }

  @Test
  void hasCreditCardHerausgeber() {
    //Setup
    final CreditCardValidator creditCardValidator = new CreditCardValidator("5555555555554444");
    //Execute
    Optional<String> herausgeber = creditCardValidator.getHerausgeber();
    //Verify
    assertEquals(true, herausgeber.isPresent());
    assertEquals("mastercard", herausgeber.get());
  }
}
