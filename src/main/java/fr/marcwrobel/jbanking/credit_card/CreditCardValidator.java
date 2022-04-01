package fr.marcwrobel.jbanking.credit_card;

import java.util.Optional;
import java.util.regex.Pattern;

public class CreditCardValidator {

  public static final String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
    "(?<mastercard>5[1-5][0-9]{14})|" +
    "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
    "(?<amex>3[47][0-9]{13})|" +
    "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
    "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";


  private static final Pattern visaPattern = Pattern.compile("^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?))");
  private static final Pattern mastercardPattern = Pattern.compile("(?<mastercard>5[1-5][0-9]{14})");
  private static final Pattern discoverPattern = Pattern.compile("(?<discover>6(?:011|5[0-9]{2})[0-9]{12})");
  private static final Pattern amexPattern = Pattern.compile("(?<amex>3[47][0-9]{13})");
  private static final Pattern dinersPattern = Pattern.compile("(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})");
  private static final Pattern jcbPattern = Pattern.compile("(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11})");

  Pattern pattern = Pattern.compile(regex);
  private final String creditCard;

  public CreditCardValidator(String creditCard) {
    this.creditCard = creditCard;
  }

  public boolean isValidCreditCard() {
    return pattern.matcher(creditCard).matches();
  }

  public boolean hasChecksumValid() {
    int sum = 0;
    boolean alternate = false;
    for (int i = creditCard.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(creditCard.substring(i, i + 1));
      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return (sum % 10 == 0);
  }

  public Optional<String> getHerausgeber() {
    return isValidCreditCard() ? Optional.ofNullable(getHerausgeberName()):Optional.empty();
  }

  private String getHerausgeberName() {
    if (visaPattern.matcher(creditCard).matches()) {
      return "visa";
    } else if (mastercardPattern.matcher(creditCard).matches()) {
      return "mastercard";
    } else if (discoverPattern.matcher(creditCard).matches()) {
      return "discover";
    } else if (amexPattern.matcher(creditCard).matches()) {
      return "amex";
    } else if (dinersPattern.matcher(creditCard).matches()) {
      return "diners";
    } else
      return "jcb";
  }

}
