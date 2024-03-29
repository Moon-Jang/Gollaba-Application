package kr.mj.gollaba.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GollabaErrorCode {
    /* 0 ~ 9999  common & auth */
    DATA_NOT_FOUND(0, HttpStatus.BAD_REQUEST, "존재하지 않는 데이터입니다."),
    INVALID_JWT_TOKEN(1, HttpStatus.UNAUTHORIZED, "유효하지 않은 jwt 토큰입니다."),
    NOT_EXIST_JWT_TOKEN(2, HttpStatus.UNAUTHORIZED, "jwt 토큰이 존재 하지 않습니다."),
    NOT_MATCHED_PASSWORD(3, HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    NOT_EXIST_REFRESH_TOKEN(4, HttpStatus.BAD_REQUEST, "존재하지 않는 리프레시 토큰입니다."),
    FAIL_LOGIN(5, HttpStatus.BAD_REQUEST, "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요."),
    INVALID_PARAMS(6, HttpStatus.BAD_REQUEST, "유효하지 않은 인자값 입니다."),
    NOT_SUPPORTED_HTTP_MEDIA_TYPE(7, HttpStatus.BAD_REQUEST, "Content-Type이 잘못되었습니다. 수정 후 다시 시도해주세요."),
    NOT_EXIST_HTTP_MEDIA_TYPE(8, HttpStatus.BAD_REQUEST, "Content-Type이 존재하지 않습니다. 수정 후 다시 시도해주세요."),
    IO_EXCEPTION(9, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 IO 장애입니다. 관리자에게 문의해주세요."),
    FAIL_TO_DECODE_HASH_ID(10, HttpStatus.BAD_REQUEST, "잘못된 아이디 입니다. hash id decoding fail"),
    DUPLICATED_KEY(11, HttpStatus.BAD_REQUEST, "이미 등록된 데이터입니다."),
    CONSTRAINT_VIOLATION(12, HttpStatus.INTERNAL_SERVER_ERROR, "SQL 제약 조건 에러입니다. 서버 관리자에게 문의해주세요."),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED,"인증 정보가 없거나 부족합니다."),
    FORBIDDEN(403, HttpStatus.FORBIDDEN,"해당 자원에 접근할 수 없습니다."),
    UNKNOWN_ERROR(9999, HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다. 관리자에게 문의해주세요."),

    /* 10000 ~ 19999 user */
    ALREADY_EXIST_USER(10000, HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    NOT_EXIST_USER_BY_UNIQUE_ID(10001, HttpStatus.BAD_REQUEST, "해당 아이디로 가입된 회원이 존재하지 않습니다."),
    NOT_EXIST_USER(10002, HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    ALREADY_EXIST_NICKNAME(10003, HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다,"),
    ALREADY_EXIST_PROVIDER_ID(10004, HttpStatus.BAD_REQUEST, "이미 존재하는 Provider 입니다."),

    /* 20000 ~ 29999 poll */
    NOT_EXIST_POLL(20000, HttpStatus.BAD_REQUEST, "존재하지 않는 투표입니다."),
    NOT_EXIST_OPTION(20001, HttpStatus.BAD_REQUEST, "존재하지 않는 옵션입니다."),
    NOT_EXIST_VOTER(20002, HttpStatus.BAD_REQUEST, "존재하지 않는 투표자입니다."),
    INVALID_IP_ADDRESS(20003, HttpStatus.BAD_REQUEST, "잘못된 ip주소입니다."),
    ALREADY_VOTE(20004, HttpStatus.BAD_REQUEST, "이미 해당 항목에 투표를 완료하셨습니다."),
    NOT_AVAILABLE_MULTI_VOTE_BY_RESPONSE_TYPE(20005, HttpStatus.BAD_REQUEST, "해당 투표는 중복 투표가 불가능합니다. 한가지 항목만 선택해주세요."),
    DONT_NEED_VOTER_NAME(20006, HttpStatus.BAD_REQUEST, "해당 투표는 익명투표입니다. 무기명으로 투표바랍니다."),
    NOT_EQUAL_POLL_CREATOR(20007, HttpStatus.BAD_REQUEST, "투표 생성자가 아님으로 해당 행위를 실행할 수 없습니다."),

    /* 30000 ~ 39999 favorites */
    NOT_EXIST_FAVORITES(20000, HttpStatus.BAD_REQUEST, "존재하지 않는 즐겨찾기입니다."),
    NOT_MATCHED_USER_FOR_FAVORITES(20001, HttpStatus.BAD_REQUEST, "해당 투표를 즐겨찾기 하지 않으셨습니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String description;

}
