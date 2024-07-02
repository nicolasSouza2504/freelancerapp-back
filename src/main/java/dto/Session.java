package dto;

import lombok.Data;

@Data
public class Session {

    private String authToken;
    private String userName;
    private Long userId;

    public Session(String authToken, String userName, Long userId) {
        this.authToken = authToken;
        this.userName = userName;
        this.userId = userId;
    }

}
