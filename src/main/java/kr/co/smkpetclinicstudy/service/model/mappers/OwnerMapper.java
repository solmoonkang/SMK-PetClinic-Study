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

    /** INSTANCE -> static final
     *  Mappers.getMapper()를 호출할 때마다 새로운 인스턴스를 생성하지 않고 기존의 생성된 인스턴스를 재사용하는 것이 좋은 방법이다
     *      이렇게 할 경우 매번 새로운 객체를 생성하지 않아도 되기 때문에 성능상 이점이 있다
     *      따라서 Mappers.getMapper()로 인스턴스를 가져오고 나면, 해당 인스턴스를 상수로 선언하여 사용할 수 있다
     *
     *  INSTANCE는 OwnerMapper의 인스턴스를 단일화해서 사용하기 위한 목적으로 선언된 것이다
     *      따라서 INSTANCE를 사용하면 OwnerMapper Interface의 메소드를 호출할 때마다 매번 새로운 인스턴스를 생성하지 않고, 미리 생성된
     *      단일 인스턴스를 사용할 수 있어서 성능상 이점이 있다
     *      또한 INSTANCE를 사용하면 OwnerMapperImpl을 직접 생성하지 않고도 OwnerMapper의 메소드를 사용할 수 있다
     *
     *  OwnerMapper 내에서 ownerMapper 객체를 static final로 선언하면 이 객체는 상수로 사용된다
     *      이렇게 상수로선언하면 객체가 여러 번 생성되지 않으므로 성능상의 이점이 있다
     */

    // OwnerReqDTO.CREATE -> Owner Entity
    @Mapping(source = "create.firstName", target = "firstName")
    @Mapping(source = "create.lastName", target = "lastName")
    @Mapping(source = "create.address", target = "address")
    @Mapping(source = "create.city", target = "city")
    @Mapping(source = "create.telephone", target = "telephone")
    Owner ownerCreateDtoToEntity(OwnerReqDTO.CREATE create);

    // Owner Entity -> OwnerResDTO.READ
    @Mapping(source = "id", target = "ownerId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "city", target = "city")
    OwnerResDTO.READ ownerEntityToReadDto(Owner owner);
}
