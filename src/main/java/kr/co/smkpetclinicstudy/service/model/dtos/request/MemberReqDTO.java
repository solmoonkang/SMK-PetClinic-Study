package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.*;

public class MemberReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "사용자 ID를 입력해주세요")
        private String identity;

        @NotBlank(message = "사용자 PW를 입력해주세요")
        private String password;

        @NotBlank(message = "사용자 이름을 입력해주세요")
        private String name;

        private RoleType roleType;
    }
}
