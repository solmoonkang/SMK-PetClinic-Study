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

    /** Create Vet Controller
     *
     */
    @PostMapping
    public ResponseFormat<Void> createVet(@RequestBody @Validated VetReqDTO.CREATE create) {
        try {
            vetService.createVet(create);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_CREATED,
                    create.getFirstName() + "님 수의사 정보가 성공적으로 생성되었습니다");
        } catch (InvalidInputException e) {
            return ResponseFormat.fail(ErrorCode.FAIL_INVALID_VALUE);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    /** Get Vet By vetId Controller
     *
     */
    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    vetService.getVetById(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.NOT_FOUND_VET);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    /** Get Vet's Pet By vetId Controller
     *
     */
    @GetMapping("/pets/{vet_id}")
    public ResponseFormat<List<PetResDTO.READ>> getVetPetsByVetId(@PathVariable(name = "vet_id") Long vetId) {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    vetService.getVetPetsByVetId(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.NOT_FOUND_VET);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    /** Get All Specialties List Controller
     *
     */
    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getVetSpecialtiesName() {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    vetService.getVetSpecialtiesName());
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    /** Update Vet Controller
     *
     */
    @PutMapping
    public ResponseFormat<Void> updateVet(@RequestBody @Validated VetReqDTO.UPDATE update) {
        try {
            vetService.updateVet(update);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    update.getFirstName() + "님 수의사 정보가 성공적으로 수정되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.NOT_FOUND_VET);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    /** Delete Vet Controller
     *
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVetById(@PathVariable(name = "vet_id") Long vetId) {
        try {
            vetService.deleteVetById(vetId);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    "수의사 정보가 성공적으로 삭제되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.NOT_FOUND_VET);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }
}
