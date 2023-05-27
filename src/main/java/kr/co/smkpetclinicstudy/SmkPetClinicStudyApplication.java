package kr.co.smkpetclinicstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy
@EnableJpaAuditing      // JPA Auditing 기능을 활성화하는 역할 : 자동으로 생성/수정시간 값을 설정
@SpringBootApplication
public class SmkPetClinicStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmkPetClinicStudyApplication.class, args);
    }

}
