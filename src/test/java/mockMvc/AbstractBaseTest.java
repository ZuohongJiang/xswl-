package mockMvc;

import com.example.hotel.HotelApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @program: hotel
 * @description: 集成测试的基类
 * @author: Li Yongshao
 * @create: 2020-06-29 23:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HotelApplication.class)
@TestExecutionListeners({MockitoTestExecutionListener.class})
public class AbstractBaseTest extends AbstractTransactionalJUnit4SpringContextTests {
    //暂无代码
}