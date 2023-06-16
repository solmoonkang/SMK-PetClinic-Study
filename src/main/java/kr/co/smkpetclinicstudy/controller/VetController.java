package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.InvalidInputException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.smkpetclinicstudy.service.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    /** Create Vet SampleController
     *
     */
    @PostMapping
    public ResponseFormat<Void> createVet(@RequestBody @Validated VetReqDTO.CREATE create) {

        vetService.createVet(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                create.getFirstName() + "님 수의사 정보가 성공적으로 생성되었습니다");
    }

    /** Get Vet By vetId SampleController
     *
     */
    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                vetService.getVetById(vetId));
    }

    /** Get Vet's Pet By vetId SampleController
     *
     */
    @GetMapping("/pets/{vet_id}")
    public ResponseFormat<List<PetResDTO.READ>> getVetPetsByVetId(@PathVariable(name = "vet_id") Long vetId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                vetService.getVetPetsByVetId(vetId));
    }

    /** Get All Specialties List SampleController
     *
     */
    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getVetSpecialtiesName() {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                vetService.getVetSpecialtiesName());
    }

    /** Update Vet SampleController
     *
     */
    @PutMapping
    public ResponseFormat<Void> updateVet(@RequestBody @Validated VetReqDTO.UPDATE update) {

        vetService.updateVet(update);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                update.getFirstName() + "님 수의사 정보가 성공적으로 수정되었습니다");
    }

    /** Delete Vet SampleController
     *
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVetById(@PathVariable(name = "vet_id") Long vetId) {

        vetService.deleteVetById(vetId);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "수의사 정보가 성공적으로 삭제되었습니다");
    }
}
