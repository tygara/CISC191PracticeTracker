/**
 * Exception type used to signal validation errors when loading or
 * processing application data (such as malformed JSON structures).
 */
public class ValidationException extends Exception {

  /**
   * Creates a ValidationException with an error message.
   *
   * @param message description of the validation failure
   */
  public ValidationException(String message) {
    super(message);
  }

  /**
   * Creates a ValidationException with an error message and its cause.
   *
   * @param message description of the validation failure
   * @param cause the underlying exception that triggered this error
   */
  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}

