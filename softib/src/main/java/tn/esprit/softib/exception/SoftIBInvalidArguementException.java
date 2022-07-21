package tn.esprit.softib.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SoftIBInvalidArguementException extends RuntimeException {

    private static final long serialVersionUID = 4113204222861006995L;

    public SoftIBInvalidArguementException() {
        super();
    }

    public SoftIBInvalidArguementException(String message) {
        super(message);
    }

}
