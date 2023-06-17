package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

public class MemberReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "사용자 ID는 필수 입력 값입니다")
        private String identity;

        @NotBlank(message = "사용자 PW는 필수 입력 값입니다")
        @Length(min = 4, max = 16, message = "PW는 4자 이상, 16자 이내로 입력해주세요")
        private String password;

        @NotBlank(message = "사용자 이름은 필수 입력 값입니다")
        private String name;

        private RoleType roleType;
    }
}
