package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.smkpetclinicstudy.service.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @PostMapping()
    public ResponseFormat<String> createVet(@RequestBody @Validated VetReqDTO.CREATE create) {
        try {
            vetService.createVet(create);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_CREATED,
                    create.getFirstName() + "님 수의사 정보가 성공적으로 생성되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @GetMapping("{/vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    vetService.getVetById(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @PutMapping
    public ResponseFormat<String> updateVet(@RequestBody @Validated VetReqDTO.UPDATE update) {
        try {
            vetService.updateVet(update);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    update.getFirstName() + "님 수의사 정보가 성공적으로 수정되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @DeleteMapping("{/vet_id}")
    public ResponseFormat<String> deleteVetById(@PathVariable(name = "vet_id") Long vetId) {
        try {
            vetService.deleteVetById(vetId);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    "수의사 정보가 성공적으로 삭제되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }
}
