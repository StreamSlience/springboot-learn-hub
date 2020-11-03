package com.streamslience.mapstruct.officialtutorial.datatypeconversions;

import com.streamslience.mapstruct.officialtutorial.OfficialTutorialApplication;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain.*;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.mapper.ICarMapper;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.mapper.AbstractFishTankMapperWithVolume;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.mapper.IConvertMapper;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain.GermanFirst;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain.OriginalFirst;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.mapper.MovieFirstMapper;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain.GermanSecond;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain.OriginalSecond;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.mapper.MovieSecondMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-10-10 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class DatatypeTest {

    @Autowired
    private IConvertMapper iConvertMapper;

    @Autowired
    private ICarMapper ICarMapper;

    @Autowired
    private AbstractFishTankMapperWithVolume abstractFishTankMapperWithVolume;

    @Autowired
    private MovieFirstMapper movieFirstMapper;

    @Autowired
    private MovieSecondMapper movieSecondMapper;

    @Test
    public void convert1() {
        Target target = new Target();
        System.err.println(target);
        target = iConvertMapper.targetFromSource(new Source());
        System.err.println(target);
    }

    @Test
    public void convert2() {
        List<Target> targets = new ArrayList<>();
        System.err.println(targets);
        targets = iConvertMapper.targetFromSource(new ArrayList<Source>() {{
            add(new Source());
            add(new Source());
            add(new Source());
        }});
        System.err.println(targets);
    }

    @Test
    public void convert3() {
        List<String> targets = new ArrayList<>();
        System.err.println(targets);
        targets = iConvertMapper.stringsFromIntegers(new ArrayList<Integer>() {{
            add(100);
            add(200);
            add(300);
        }});
        System.err.println(targets);
    }

    @Test
    public void convert4() {
        List<String> targets = new ArrayList<>();
        System.err.println(targets);
        targets = iConvertMapper.stringsFromDate(new ArrayList<Date>() {{
            add(new Date());
            add(new Date());
            add(new Date());
        }});
        System.err.println(targets);
    }

    /**
     * 映射类型转换-调用其他映射方法(Invoking custom mapping method)
     */
    @Test
    public void convert5() {
        FishTankWithVolumeDto fishTankWithVolumeDto = new FishTankWithVolumeDto();
        System.err.println(fishTankWithVolumeDto);
        fishTankWithVolumeDto = abstractFishTankMapperWithVolume.map(new FishTank());
        System.err.println(fishTankWithVolumeDto);
    }

    /**
     * 映射类型转换-调用其他映射器(Invoking other mappers)
     */
    @Test
    public void convert6() {
        CarDto carDto = new CarDto();
        System.err.println(carDto);
        carDto = ICarMapper.carToCarDto(new Car(new Date(), "2020-10-17 10:10:10"));
        System.err.println(carDto);
    }

    /**
     * 映射类型转换-基于限定词的映射方法选择(Mapping method selection based on qualifiers)
     */
    @Test
    public void convert7() {
        GermanFirst germanFirst = new GermanFirst();
        System.err.println(germanFirst);
        germanFirst = movieFirstMapper.toGerman(new OriginalFirst("hahaha"));
        Assert.assertEquals("映射结果错误", "GermanToEnglish", germanFirst.getTitle());
        System.err.println(germanFirst);

        System.err.println("---------------------------");
        GermanSecond germanSecond = new GermanSecond();
        System.err.println(germanSecond);
        germanSecond = movieSecondMapper.toGerman(new OriginalSecond("hahaha"));
        Assert.assertEquals("映射结果错误", "EnglishToGerman", germanSecond.getTitle());
        System.err.println(germanSecond);
    }

}












































