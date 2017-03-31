import com.home.User;
import com.home.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private User user = new User("test_name", "test_password");


    @Test
    public void saveAndReadByNameTest(){

        userDao.save(user);

        User actualResult = userDao.getByName(user.getName());

        assertEquals(user, actualResult);

    }

}
