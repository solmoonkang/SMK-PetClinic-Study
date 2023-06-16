package kr.co.smkpetclinicstudy.service;

import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.model.mappers.OwnerMapper;
import kr.co.smkpetclinicstudy.service.service.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerSampleServiceTest {

    // Spring 컨테이너가 SampleService 객체를 생성하고 의존성 주입을 자동으로 처리한다
    // 따라서 SampleService 객체를 직접 생성할 필요 없이, Test Class에 @Autowired 을 사용하여 SampleService 객체를 주입받을 수 있다
    // 이 방식은 TestCode 작성 시 유연성을 높여주며, 코드 중복을 줄일 수 있어 코드의 가독성을 높여준다
    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    private OwnerReqDTO.CREATE create;

    private Owner owner;

    @BeforeEach
    void setUpOwnerCreate() {
        // OwnerReqDTO.CREATE 객체 초기화 코드
        // Owner owner 객체 초기화 코드
        create = OwnerReqDTO.CREATE.builder()
                .firstName("SOLMOON")
                .lastName("KANG")
                .address("BOO-YOUNG APT 301, Inchang-Dong")
                .city("GURI-SI")
                .telephone("010-3884-2913")
                .build();

        owner = Owner.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .address(create.getAddress())
                .city(create.getCity())
                .telephone(create.getTelephone())
                .build();
    }

    @AfterEach
    void tearDownOwnerCreate() {
    }

    @Test
    @DisplayName("createOwner SampleService Test")
    public void createOwnerTest() {
        // given
        given(ownerMapper.toOwnerEntity(eq(create))).willReturn(owner);

        // when
        ownerService.createOwner(create);

        // then
        verify(ownerMapper, times(1)).toOwnerEntity(create);
        verify(ownerRepository, times(1)).save(eq(owner));
    }

    @Test
    @DisplayName("createOwner SampleService Test with Duplicate Owner Telephone")
    public void createOwnerTest_withDuplicateOwnerTelephone() {
        // given : Owner 의 전화번호를 이미 존재하는 상황을 가정한다
        // willReturn : void 메소드에서 호출 후 예외를 던질 때 사용된다
        given(ownerRepository.existsByTelephone(create.getTelephone())).willReturn(true);

        // when : createOwner 메소드가 DuplicatedException 을 던지는지 확인한다
        assertThrows(DuplicatedException.class, () -> ownerService.createOwner(create));

        // then : Owner 가 저장되지 않았음을 검증한다
        verify(ownerRepository, never()).save(owner);
    }


    @Test
    @DisplayName("getOwnerById SampleService Test")
    public void getOwnerByIdTest() {
        Long ownerId = 1L;
        owner = Owner.builder()
                .firstName("SOLMOON")
                .lastName("KANG")
                .city("GURI-SI")
                .build();

        // given
            // thenReturn : 스텁 메소드의 기본 반환 값을 설정하는데 사용된다(스텁 메소드 : 호출된 경우 지정된 값을 반환한다)
        OwnerResDTO.READ read = ownerMapper.toOwnerReadDto(owner);
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));

        // when
        OwnerResDTO.READ actual = ownerService.getOwnerById(ownerId);

        // then
        assertThat(actual).isEqualTo(read);
        verify(ownerRepository, times(1)).findById(ownerId);
    }

    @Test
    @DisplayName("getOwnerById SampleService Test with Not Found Owner")
    public void getOwnerByIdTest_withNotFoundOwner() {

    }
}
