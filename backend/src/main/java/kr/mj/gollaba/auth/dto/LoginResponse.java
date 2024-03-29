package kr.mj.gollaba.auth.dto;

import kr.mj.gollaba.common.BaseApiResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse implements BaseApiResponse {

    private final String accessToken;

    private final String refreshToken;

    @Builder
    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
