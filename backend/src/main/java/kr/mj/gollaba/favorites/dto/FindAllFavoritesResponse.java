package kr.mj.gollaba.favorites.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import kr.mj.gollaba.common.BaseApiResponse;
import kr.mj.gollaba.common.serializer.HashIdSerializer;
import kr.mj.gollaba.favorites.entity.Favorites;
import kr.mj.gollaba.poll.dto.FindAllPollResponse;
import kr.mj.gollaba.poll.entity.Poll;
import kr.mj.gollaba.poll.type.PollingResponseType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class FindAllFavoritesResponse implements BaseApiResponse {

    private List<FindAllPollResponse.PollResponse> polls = new ArrayList<>();

    public FindAllFavoritesResponse(List<Poll> polls, List<Favorites> favoritesList) {
        Map<Long, Favorites> favoritesByPollId = favoritesList.stream()
                .collect(Collectors.toMap(el -> el.getPoll().getId(), el -> el));

        this.polls = polls.stream()
                .map(el -> FindAllPollResponse.PollResponse.builder()
                        .pollId(el.getId())
                        .title(el.getTitle())
                        .creatorName(el.getCreatorName())
                        .responseType(el.getResponseType())
                        .isBallot(el.getIsBallot())
                        .endedAt(el.getEndedAt())
                        .pollImageUrl(el.getPollImageUrl())
                        .favorites(FindAllPollResponse.FavoritesResponse.of(favoritesByPollId.get(el.getId())))
                        .options(el.getOptions()
                                .stream()
                                .map(option -> FindAllPollResponse.OptionResponse.builder()
                                        .optionId(option.getId())
                                        .description(option.getDescription())
                                        .voteCount(option.getVoters().size())
                                        .createdAt(option.getCreatedAt())
                                        .updatedAt(option.getUpdatedAt())
                                        .build())
                                .collect(Collectors.toList()))
                        .createdAt(el.getCreatedAt())
                        .updatedAt(el.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public static FindAllFavoritesResponse from(List<Poll> polls, List<Favorites> favoritesList) {
        return new FindAllFavoritesResponse(polls, favoritesList);
    }

    @Getter
    public static class PollResponse {

        @ApiModelProperty(dataType = "string", example = "hashId")
        @JsonSerialize(using = HashIdSerializer.class)
        private Long pollId;

        private String title;

        private String creatorName;

        private PollingResponseType responseType;

        private Boolean isBallot;

        private LocalDateTime endedAt;

        private String pollImageUrl;

        private Long totalVoteCount;

        private FindAllPollResponse.FavoritesResponse favorites;

        private List<FindAllPollResponse.OptionResponse> options = new ArrayList<>();

        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;

        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDateTime updatedAt;

        @Builder
        private PollResponse(Long pollId, String title, String creatorName, PollingResponseType responseType, Boolean isBallot, LocalDateTime endedAt, List<FindAllPollResponse.OptionResponse> options, String pollImageUrl, FindAllPollResponse.FavoritesResponse favorites, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.pollId = pollId;
            this.title = title;
            this.creatorName = creatorName;
            this.responseType = responseType;
            this.isBallot = isBallot;
            this.endedAt = endedAt;
            this.options = options;
            this.pollImageUrl = pollImageUrl;
            this.favorites = favorites;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.totalVoteCount = Long.valueOf(options
                    .stream()
                    .mapToInt(option -> option.getVoteCount())
                    .sum());
        }

    }

    @Getter
    public static class OptionResponse {

        private Long optionId;

        private String description;

        private Integer voteCount;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedAt;

        @Builder
        public OptionResponse(Long optionId, String description, int voteCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.optionId = optionId;
            this.description = description;
            this.voteCount = voteCount;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

    }

    @Getter
    public static class FavoritesResponse {

        private Long favoritesId;

        @Builder
        private FavoritesResponse(Long favoritesId) {
            this.favoritesId = favoritesId;
        }

        public static FindAllPollResponse.FavoritesResponse from(Favorites favorites) {
            if (favorites == null) {
                return null;
            }

            return FindAllPollResponse.FavoritesResponse.builder()
                    .favoritesId(favorites.getId())
                    .build();
        }
    }
}
