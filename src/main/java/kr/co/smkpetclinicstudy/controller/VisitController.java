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

    /** Create Visit SampleController
     *
     */
    @PostMapping
    public ResponseFormat<Void> createVisit(@RequestBody @Validated VisitReqDTO.CREATE create) {

        visitService.createVisit(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                "방문자 정보가 성공적으로 생성되었습니다");
    }

    /** Get Visit By PetId SampleController
     *
     */
    @GetMapping("/{pet_id}/pets")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitByPetId(@PathVariable(name = "pet_id") Long petId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                visitService.getVisitByPetId(petId));
    }

    /** Get Visit By VisitId SampleController
     *
     */
    @GetMapping("/{visit_id}")
    public ResponseFormat<VisitResDTO.READ> getVisitByVisitId(@PathVariable(name = "visit_id") Long visitId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                visitService.getVisitByVisitId(visitId));
    }

    /** Get Visit By OwnerId SampleController
     *
     */
    @GetMapping("/owners/{owner_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getAllVisitByOwnerId(@PathVariable(name = "owner_id") Long ownerId) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                visitService.getAllVisitByOwnerId(ownerId));
    }

    /** Update Visit SampleController
     *
     */
    @PutMapping
    public ResponseFormat<Void> updateVisit(@RequestBody @Validated VisitReqDTO.UPDATE update) {

        visitService.updateVisit(update);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "방문자 정보가 성공적으로 수정되었습니다");
    }

    /** Delete Visit SampleController
     *
     */
    @DeleteMapping("/{visit_id}")
    public ResponseFormat<Void> deleteVisitById(@PathVariable(name = "visit_id") Long visitId) {

        visitService.deleteVisitById(visitId);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "방문자 정보가 성공적으로 삭제되었습니다");
    }
}
