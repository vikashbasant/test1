package decimal.test.exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestException extends Exception {

    private String statusCode;
    private String message;
    private transient Object errorMessages;

    public TestException(String errorCode, String message,
                         String errorMessage) {
        this.statusCode = errorCode;
        this.message = message;
        this.errorMessages = errorMessage;
    }

    public TestException(String errorCode, String message) {
        this.statusCode = errorCode;
        this.message = message;
    }
}