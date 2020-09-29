package com.streamslience.mapstruct.officialtutorial.retrievingamapper;

import com.streamslience.mapstruct.officialtutorial.OfficialTutorialApplication;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper.ISpringMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author StreamSlience
 * @date 2020-09-29 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class SpringMapperTest extends BasicTest {

    @Autowired
    private ISpringMapper iSpringMapper;

    @Test
    public void run() {
        convert(iSpringMapper);
    }

}
