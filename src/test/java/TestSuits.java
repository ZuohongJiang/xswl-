import com.example.hotel.junits.*;
import mockMvc.MockMvcTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @program: hotel
 * @description: 打包测试类
 * @author: Li Yongshao
 * @create: 2020-06-27 22:33
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HotelInfoTests.class,
        RoomInfoTests.class,
        AccountTests.class,
        OrderTests.class,
        CouponTests.class,
        MockMvcTests.class
})
public class TestSuits {
    //打包类不需要写代码
}