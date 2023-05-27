package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat.successData;
import static kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat.successMessage;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * Create Owner Controller
     */
    @PostMapping
    public ResponseFormat<Void> createOwner(@Validated @RequestBody OwnerReqDTO.CREATE create) {

        ownerService.createOwner(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                create.getFirstName() + "님 소유자 정보가 성공적으로 생성되었습니다");
    }

    /**
     * Get Owner By id Controller
     */
    @GetMapping("/{owner_id}")
    public ResponseFormat<OwnerResDTO.READ> getOwnerById(@PathVariable(name = "owner_id") Long ownerId) {

        return successData(
                ErrorCode.SUCCESS_EXECUTE,
                ownerService.getOwnerById(ownerId));
    }

    /**
     * Get Owners By Date Controller
     */
    @GetMapping("/date")
    public ResponseFormat<List<OwnerResDTO.READ>> getOwnersByDate(@RequestParam(name = "start_date") LocalDate startDate,
                                                                  @RequestParam(name = "end_date") LocalDate endDate) {

        return ResponseFormat.successData(
                ErrorCode.SUCCESS_EXECUTE,
                ownerService.getOwnersByDate(startDate, endDate));
    }

    /**
     * Update Owner Controller
     */
    @PutMapping
    public ResponseFormat<Void> updateOwner(@Validated @RequestBody OwnerReqDTO.UPDATE update) {

        ownerService.updateOwner(update);

        return successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                update.getFirstName() + "님 소유자 정보가 성공적으로 수정되었습니다");
    }

    /**
     * Delete Owner Controller
     */
    @DeleteMapping("/{owner_id}")
    public ResponseFormat<Void> deleteOwnerById(@PathVariable(name = "owner_id") Long ownerId) {

        ownerService.deleteOwnerById(ownerId);

        return successMessage(
                ErrorCode.SUCCESS_EXECUTE,
                "소유자 정보가 성공적으로 삭제되었습니다");
    }
}
