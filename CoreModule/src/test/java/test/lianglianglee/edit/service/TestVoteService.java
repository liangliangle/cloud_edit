package test.lianglianglee.edit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.lianglianglee.edit.TestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestVoteService {



  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testQuest() throws Exception {


  }
}
