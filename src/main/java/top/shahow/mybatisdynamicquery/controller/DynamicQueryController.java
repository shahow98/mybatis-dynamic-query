package top.shahow.mybatisdynamicquery.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.shahow.mybatisdynamicquery.entity.CommonResponse;
import top.shahow.mybatisdynamicquery.entity.DynamicQueryRequest;
import top.shahow.mybatisdynamicquery.service.DynamicQueryService;


/**
 * @author shahow
 * @date 2023-02-18
 */
@RestController
@RequestMapping("/common/dynamic")
@RequiredArgsConstructor
public class DynamicQueryController {
    private final DynamicQueryService dynamicQueryService;

    @PostMapping("/query")
    public CommonResponse<Object> query(@Valid @RequestBody DynamicQueryRequest request) {
        return CommonResponse.success(dynamicQueryService.dynamicQuery(request));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResponse<Object> validationHandler(MethodArgumentNotValidException validationException) {
        return CommonResponse
                .fail("validation exception: " + validationException.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse<Object> exceptionHandler(Exception exception) {
        return CommonResponse.fail("sql query exception: " + exception.getMessage());
    }
}
