package decimal.test.controller;

import decimal.test.dto.ResponseDto;
import decimal.test.dto.RestCallingDto;
import decimal.test.dto.TestDTO;
import decimal.test.exception.TestException;
import decimal.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping(value = "save")
    public String saveTestData(@RequestBody TestDTO testDTO)
    {
        testService.saveTestData(testDTO);
        return "SUCCESS";
    }


    @PostMapping(value = "update")
    public String updateTestData(@RequestBody TestDTO testDTO)
    {
        testService.updateTestData(testDTO);
        return "SUCCESS";
    }

    @PostMapping(value = "getById")
    public ResponseEntity getById(@RequestBody TestDTO testDTO) throws TestException {
    ResponseDto responseDto= testService.getById(testDTO.getId());
    return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "getByName")
    public Object getByName(@RequestBody RestCallingDto dto) throws TestException {
     return testService.getByName(dto);
    }

}
