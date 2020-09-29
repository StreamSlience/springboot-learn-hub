package com.streamslience.mapstruct.officialtutorial.retrievingamapper;

import com.streamslience.mapstruct.officialtutorial.OfficialTutorialApplication;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper.IUserBasicMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

/**
 * @author StreamSlience
 * @date 2020-09-29 16:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class CdiTest extends BasicTest {

    @Inject
    private IUserBasicMapper iUserBasicMapper;

    @Test
    public void run() {
        convert(iUserBasicMapper);
    }
}
