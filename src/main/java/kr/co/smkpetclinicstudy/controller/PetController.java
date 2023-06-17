package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import kr.co.smkpetclinicstudy.service.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    /** Create Pet Controller
     *
     */
    @PostMapping
    public ResponseFormat<Void> createPet(@RequestBody @Validated PetReqDTO.CREATE create) {

        petService.createPet(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                "펫 정보가 성공적으로 생성되었습니다");
    }

    /** Get All PetTypes Controller
     *
     */
    @GetMapping("/pet_types")
    public ResponseFormat<Set<PetType>> getAllPetTypes() {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                petService.getAllPetTypes());
    }

    /** Get Pet Detail By id Controller
     *
     */
    @GetMapping("/detail/{pet_id}")
    public ResponseFormat<PetResDTO.READ_DETAIL> getDetailPetById(@PathVariable(name = "pet_id") Long petId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                petService.getDetailPetById(petId));
    }

    /** Get Owner's Pets By ownerId Controller
     *
     */
    @GetMapping("/owners/{owner_id}")
    public ResponseFormat<List<PetResDTO.READ>> getOwnerPetsByOwnerId(@PathVariable(name = "owner_id") Long ownerId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                petService.getOwnerPetsByOwnerId(ownerId));
    }

    /** Update Pet Controller
     *
     */
    @PutMapping
    public ResponseFormat<Void> updatePet(@RequestBody @Validated PetReqDTO.UPDATE update) {

        petService.updatePet(update);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "펫 정보가 성공적으로 수정되었습니다");
    }

    /** Delete Pet Controller
     *
     */
    @DeleteMapping("/{pet_id}")
    public ResponseFormat<Void> deletePet(@PathVariable(name = "pet_id") Long petId) {

        petService.deletePetById(petId);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "펫 정보가 성공적으로 삭제되었습니다");
    }
}
