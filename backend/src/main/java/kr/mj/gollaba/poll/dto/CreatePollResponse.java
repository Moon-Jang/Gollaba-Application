package kr.mj.gollaba.poll.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import kr.mj.gollaba.common.BaseApiRequest;
import kr.mj.gollaba.common.BaseApiResponse;
import kr.mj.gollaba.common.serializer.HashIdSerializer;
import kr.mj.gollaba.exception.GollabaErrorCode;
import kr.mj.gollaba.exception.GollabaException;
import kr.mj.gollaba.poll.entity.Option;
import kr.mj.gollaba.poll.entity.Poll;
import kr.mj.gollaba.poll.type.PollingResponseType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreatePollResponse implements BaseApiResponse {

    @ApiModelProperty(dataType = "string", example = "hashId")
    @JsonSerialize(using = HashIdSerializer.class)
    private Long pollId;

    public CreatePollResponse(Long pollId) {
        this.pollId = pollId;
    }
}
