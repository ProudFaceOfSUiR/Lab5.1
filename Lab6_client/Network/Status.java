package Network;

import java.io.Serializable;

public enum Status implements Serializable {
    NOT_ESTABLISHED,
    ESTABLISHED,
    TAKEN_LOGIN,
    WRONG_PASSWORD;
}
