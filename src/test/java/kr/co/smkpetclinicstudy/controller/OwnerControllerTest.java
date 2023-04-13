package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.service.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    // Business Logic 이 담긴 Service 객체를 호출하므로, Controller 에서는 Service 객체에 대한 의존성을 주입받아야 한다
    // 이 때 @InjectMocks 를 사용하면 Mockito 가 Controller 객체 내부의 Service 객체에 대한 의존성을 자동으로 주입해준다
    @InjectMocks
    private OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    private OwnerReqDTO.CREATE create;
    private OwnerReqDTO.UPDATE update;
    private OwnerResDTO.READ read;


    @BeforeEach // @Test 메서드가 실행하기 전 매번 실행되는 메서드, 객체를 초기화하거나 DB를 리셋하는 등 공통적인 작업을 진행할 수 있다
    void setUpOwnerCreateDto() {
        // OwnerReqDTO.CREATE 객체 초기화 코드
        create = OwnerReqDTO.CREATE.builder()
                .firstName("SOLMOON")
                .lastName("KANG")
                .address("BOO-YOUNG APT 301, Inchang-Dong")
                .city("GURI-SI")
                .telephone("010-3884-2913")
                .build();
    }

    @AfterEach
    void tearDownOwnerCreateDto() {   // 각각의 테스트가 실행된 후 호출되어 OwnerReqDTO.CREATE 객체를 해제한다
        // OwnerReqDTO.CREATE 객체 해제 코드
        create = null;
    }

    @Test
    @DisplayName("createOwner Controller Test")
    void createOwnerTest() {
        // given
        Mockito.doNothing().when(ownerService).createOwner(create);

        // when
        ResponseFormat<String> responseFormat = ownerController.createOwner(create);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 201 CREATED")
                .isEqualTo(201);
        assertThat(responseFormat.getMessage())
                .as("Response message should be 'SOLMOON님 소유자 정보가 성공적으로 생성되었습니다'")
                .isEqualTo("SOLMOON님 소유자 정보가 성공적으로 생성되었습니다");
    }

    @Test
    @DisplayName("createOwner Controller Test with Duplicate Owner Telephone")
    void createOwnerTest_withDuplicateOwnerTelephone() throws DuplicatedException {
        // given
        Mockito.doThrow(DuplicatedException.class).when(ownerService).createOwner(create);

        // when
        ResponseFormat<String> responseFormat = ownerController.createOwner(create);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 400 DUPLICATE")
                .isEqualTo(400);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '소유자 핸드폰 번호가 중복되었습니다'")
                .isEqualTo("소유자 핸드폰 번호가 중복되었습니다");
    }

    @Test
    @DisplayName("createOwner Service Test with Not Found Owner")
    void createOwnerTest_withNotFoundOwner() throws NotFoundException {
        // given
        Mockito.doThrow(NotFoundException.class).when(ownerService).createOwner(create);

        // when
        ResponseFormat<String> responseFormat = ownerController.createOwner(create);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 404 NOTFOUND")
                .isEqualTo(404);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '해당 소유자 정보를 찾지 못하였습니다'")
                .isEqualTo("해당 소유자 정보를 찾지 못하였습니다");
    }


    @BeforeEach
    void setUpOwnerReadDto() {
        // OwnerResDTO.READ 객체 초기화 코드
        read = OwnerResDTO.READ.builder()
                .ownerId(1L)
                .firstName("SOLMOON")
                .lastName("KANG")
                .city("GURI-SI")
                .build();
    }

    @AfterEach
    void tearDownOwnerReadDto() {
        // OwnerResDTO.READ 객체 해제 코드
    }

    @Test
    @DisplayName("getOwnerById Controller Test")
    void getOwnerByIdTest() {
        // given
        Mockito.when(ownerService.getOwnerById(read.getOwnerId())).thenReturn(read);

        // when
        ResponseFormat<OwnerResDTO.READ> responseFormat = ownerController.getOwnerById(read.getOwnerId());

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 200 SUCCESS")
                .isEqualTo(200);
        assertThat(responseFormat.getMessage())
                .isEqualTo(ErrorCode.SUCCESS_EXECUTE.getMessage());
        assertThat(responseFormat.getData())
                .as("Response data should not be null")
                .isEqualTo(read)
                .isNotNull();
    }

    @Test
    @DisplayName("getOwnerById Controller Test with Not Found Owner")
    void getOwnerByIdTest_withNotFoundOwner() throws NotFoundException {
        //given
        Mockito.doThrow(NotFoundException.class).when(ownerService).getOwnerById(read.getOwnerId());

        // when
        ResponseFormat<OwnerResDTO.READ> responseFormat = ownerController.getOwnerById(read.getOwnerId());

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 404 NOTFOUND")
                .isEqualTo(404);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '해당 소유자 정보를 찾지 못하였습니다'")
                .isEqualTo("해당 소유자 정보를 찾지 못하였습니다");
    }


    @BeforeEach
    void setUpOwnerUpdateDto() {
        // OwnerReqDTO.UPDATE 객체 초기화 코드
        update = OwnerReqDTO.UPDATE.builder()
                .ownerId(1L)
                .firstName("SOLMOON")
                .lastName("KANG")
                .address("BOO-YOUNG APT 301, Inchang-Dong")
                .city("GURI-SI")
                .telephone("010-3884-2913")
                .build();
    }

    @AfterEach
    void tearDownOwnerUpdateDto() {
        // OwnerReqDTO.UPDATE 객체 해제 코드
    }

    @Test
    @DisplayName("updateOwner Controller Test")
    void updateOwner() {
        // given
        Mockito.doNothing().when(ownerService).updateOwner(update);

        // when
        ResponseFormat<String> responseFormat = ownerController.updateOwner(update);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 200 SUCCESS")
                .isEqualTo(201);
        assertThat(responseFormat.getMessage())
                .as("Response message should be 'SOLMOON님 소유자 정보가 성공적으로 수정되었습니다'")
                .isEqualTo("SOLMOON님 소유자 정보가 성공적으로 수정되었습니다");
    }


    @Test
    @DisplayName("updateOwner Controller Test with Duplicate Owner Telephone")
    void updateOwnerTest_withDuplicateOwnerTelephone() throws DuplicatedException {
        // given
        Mockito.doThrow(DuplicatedException.class).when(ownerService).updateOwner(update);

        // when
        ResponseFormat<String> responseFormat = ownerController.updateOwner(update);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 400 DUPLICATE")
                .isEqualTo(400);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '소유자 핸드폰 번호가 중복되었습니다'")
                .isEqualTo("소유자 핸드폰 번호가 중복되었습니다");
    }

    @Test
    @DisplayName("updateOwner Controller Test with Not Found Owner")
    void updateOwnerTest_withNotFoundOwner() throws NotFoundException {
        // given
        Mockito.doThrow(NotFoundException.class).when(ownerService).updateOwner(update);

        // when
        ResponseFormat<String> responseFormat = ownerController.updateOwner(update);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 404 NOTFOUND")
                .isEqualTo(404);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '해당 소유자 정보를 찾지 못하였습니다'")
                .isEqualTo("해당 소유자 정보를 찾지 못하였습니다");
    }


    @Test
    @DisplayName("deleteOwnerById Controller Test")
    void deleteOwnerById() {
        // given
        Long ownerId = 1L;

        Mockito.doNothing().when(ownerService).deleteOwnerById(ownerId);

        // when
        ResponseFormat<String> responseFormat = ownerController.deleteOwnerById(ownerId);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 200 SUCCESS")
                .isEqualTo(201);
        assertThat(responseFormat.getMessage())
                .as("Response message should be 'SOLMOON님 소유자 정보가 성공적으로 삭제되었습니다'")
                .isEqualTo("SOLMOON님 소유자 정보가 성공적으로 삭제되었습니다");
    }

    @Test
    @DisplayName("deleteOwnerById Controller Test with Not Found Owner")
    void deleteOwnerById_withNotFoundOwner() throws NotFoundException {
        // given
        Long ownerId = 999L;

        Mockito.doThrow(NotFoundException.class).when(ownerService).deleteOwnerById(ownerId);

        // when
        ResponseFormat<String> responseFormat = ownerController.deleteOwnerById(ownerId);

        // then
        assertThat(responseFormat.getStatus())
                .as("Response status should be 404 NOTFOUND")
                .isEqualTo(404);
        assertThat(responseFormat.getMessage())
                .as("Response status should be '해당 소유자 정보를 찾지 못하였습니다'")
                .isEqualTo("해당 소유자 정보를 찾지 못하였습니다");
    }

}
