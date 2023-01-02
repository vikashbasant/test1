package decimal.test.service;

import decimal.test.dto.ResponseDto;
import decimal.test.dto.RestCallingDto;
import decimal.test.dto.TestDTO;
import decimal.test.exception.TestException;

import java.util.List;

public interface TestService {

    void saveTestData(TestDTO testDTO);

    ResponseDto getById(Integer id) throws TestException;

    Object getByName(RestCallingDto dto) throws TestException;

    void updateTestData(TestDTO testDTO);
}
