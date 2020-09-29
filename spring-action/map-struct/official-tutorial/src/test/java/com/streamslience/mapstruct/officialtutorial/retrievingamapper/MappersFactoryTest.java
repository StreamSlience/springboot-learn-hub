package com.streamslience.mapstruct.officialtutorial.retrievingamapper;

import com.streamslience.mapstruct.officialtutorial.OfficialTutorialApplication;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper.IMappersFactoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author StreamSlience
 * @date 2020-09-29 16:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class MappersFactoryTest extends BasicTest {

    @Test
    public void run() {
        convert(IMappersFactoryMapper.INSTANCE);
    }
}
