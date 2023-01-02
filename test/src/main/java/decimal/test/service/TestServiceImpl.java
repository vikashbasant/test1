package decimal.test.service;

import decimal.test.domain.Test;
import decimal.test.dto.ResponseDto;
import decimal.test.dto.RestCallingDto;
import decimal.test.dto.TestDTO;
import decimal.test.exception.TestException;
import decimal.test.repository.TestRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service

public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepo testRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void saveTestData(TestDTO testDTO) {

        Test test = new Test();
        test.setName(testDTO.getName());
        test.setAddress(testDTO.getAddress());
        testRepo.save(test);
    }

    @Override
    public ResponseDto getById(Integer id) throws TestException {
        Optional<Test> byId = testRepo.findById(id);
        TestDTO testDTO =  new TestDTO();
        if(!byId.isPresent())
            throw new TestException("TEST_500","User doesn't exist",null);

        byId.ifPresent( test -> {
            testDTO.setName(test.getName());
            testDTO.setAddress(test.getAddress());
        });

        return new ResponseDto("SUCCESS","200","User Details Fetched Successfully",testDTO);
    }

    @Override
    public Object getByName(RestCallingDto dto) throws TestException {


        HttpHeaders headers=new HttpHeaders();
       // headers.setAccept((Arrays.asList( MediaType.APPLICATION_JSON)));

        headers.setContentType(MediaType.APPLICATION_JSON);

       // RestCallingDto dto=new RestCallingDto("hritik","agg");

        HttpEntity<RestCallingDto> entity=new HttpEntity<>(dto,headers);

        System.out.println(entity);

        ResponseEntity<Object> exchange1 = restTemplate.exchange("http://localhost:8081/get", HttpMethod.POST, entity, Object.class);

        return exchange1;

        /*
        List<Test> testList = testRepo.findByName(name);
            if(testList.isEmpty()){
                throw new TestException("500","FAILURE","Some Error Occured");
            }
            List<TestDTO> testDTOList = new ArrayList<>();
            testList.forEach(test -> {
                TestDTO testDTO = new TestDTO();
                testDTO.setId(test.getId());
                testDTO.setName(test.getName());
                testDTO.setAddress(test.getAddress());
                testDTOList.add(testDTO);
            });
            return testDTOList;


         */

    }

    @Override
    public void updateTestData(TestDTO testDTO) {
        Optional<Test> byId = testRepo.findById(testDTO.getId());

        byId.ifPresent( test -> {
            test.setName(testDTO.getName());
            test.setAddress(testDTO.getAddress());
            testRepo.save(test);
                });

    }
}
