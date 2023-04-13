package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.smkpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseFormat<String> createVisit(@RequestBody @Validated VisitReqDTO.CREATE create) {
        try {
            visitService.createVisit(create);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_CREATED,
                    "방문자 정보가 성공적으로 생성되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @GetMapping("/{pet_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitByPetId(@PathVariable(name = "pet_id") Long petId) {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    visitService.getVisitByPetId(petId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @GetMapping("/{visit_id}")
    public ResponseFormat<VisitResDTO.READ> getVisitByVisitId(@PathVariable Long visitId) {
        try {
            return ResponseFormat.successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    visitService.getVisitByVisitId(visitId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @PutMapping
    public ResponseFormat<String> updateVisit(@RequestBody @Validated VisitReqDTO.UPDATE update) {
        try {
            visitService.updateVisit(update);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    "방문자 정보가 성공적으로 수정되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @DeleteMapping("/{visit_id}")
    public ResponseFormat<String> deleteVisitById(@PathVariable(name = "visit_id") Long visitId) {
        try {
            visitService.deleteVisitById(visitId);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    "방문자 정보가 성공적으로 삭제되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (RuntimeException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }
}
