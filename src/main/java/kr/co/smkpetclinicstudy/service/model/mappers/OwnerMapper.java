package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")  // Spring의 컴포넌트 모델을 사용할 수 있다(Bean을 주입하고 구현체 클래스를 만들지 않고도 사용 가능하다)
public interface OwnerMapper {
    // OwnerDTO와 OwnerEntity 간의 데이터 전환을 담당

    /** MapStruct
     *  MapStruct에서 Interface와 구현 클래스는 컴파일 시간에 자동으로 생성된다
     *      따라서 OwnerMapper Interface를 만들면 OwnerMapperImpl 클래스는 자동으로 생성된다
     *      그래서 별도의 구현 클래스를 작성하지 않아도 OwnerMapper Interface만으로도 Mapper를 사용할 수 있다
     *      따라서 OwnerMapper Interface를 생성하고, 해당 Interface를 사용하여 Mapper를 호출한다
     *  이를 위해 'Mappers.getMapper(OwnerMapper.class)'와 같이 OwnerMapper Interface를 인자로 전달하여 Mapper Object를 생성한다
     *  이렇게 생성된 OwnerMapper Object를 통해 Mapper 메소드를 호출할 수 있다
     */

    // OwnerReqDTO.CREATE -> Owner Entity
    @Mapping(source = "create.firstName", target = "firstName")
    @Mapping(source = "create.lastName", target = "lastName")
    @Mapping(source = "create.address", target = "address")
    @Mapping(source = "create.city", target = "city")
    @Mapping(source = "create.telephone", target = "telephone")
    Owner toOwnerEntity(OwnerReqDTO.CREATE create);

    // Owner Entity -> OwnerResDTO.READ
    @Mapping(source = "id", target = "ownerId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "city", target = "city")
    OwnerResDTO.READ toOwnerReadDto(Owner owner);
}
