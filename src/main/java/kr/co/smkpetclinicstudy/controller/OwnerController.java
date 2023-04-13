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

import static kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat.successData;
import static kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat.successMessage;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseFormat<String> createOwner(@Validated @RequestBody OwnerReqDTO.CREATE create) {
        try {
            ownerService.createOwner(create);
            return ResponseFormat.successMessage(
                    ErrorCode.SUCCESS_CREATED,
                    create.getFirstName() + "님 소유자 정보가 성공적으로 생성되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (DuplicatedException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @GetMapping("/{owner_id}")
    public ResponseFormat<OwnerResDTO.READ> getOwnerById(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            return successData(
                    ErrorCode.SUCCESS_EXECUTE,
                    ownerService.getOwnerById(ownerId));
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @PutMapping
    public ResponseFormat<String> updateOwner(@Validated @RequestBody OwnerReqDTO.UPDATE update) {
        try {
            ownerService.updateOwner(update);
            return successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    update.getFirstName() + "님 소유자 정보가 성공적으로 수정되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        } catch (DuplicatedException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }

    @DeleteMapping("/{owner_id}")
    public ResponseFormat<String> deleteOwnerById(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            ownerService.deleteOwnerById(ownerId);
            return successMessage(
                    ErrorCode.SUCCESS_EXECUTE,
                    "소유자 정보가 성공적으로 삭제되었습니다");
        } catch (NotFoundException e) {
            return ResponseFormat.fail(ErrorCode.FAIL);
        }
    }
}
