package com.streamslience.mapstruct.officialtutorial.datatypeconversions;

import com.streamslience.mapstruct.officialtutorial.OfficialTutorialApplication;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.*;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper.CarMapper;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper.FishTankMapperWithVolume;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper.IConvertMapper;
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
    private CarMapper carMapper;

    @Autowired
    private FishTankMapperWithVolume fishTankMapperWithVolume;

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
        fishTankWithVolumeDto = fishTankMapperWithVolume.map(new FishTank());
        System.err.println(fishTankWithVolumeDto);
    }

    /**
     * 映射类型转换-调用其他映射器(Invoking other mappers)
     */
    @Test
    public void convert6() {
        CarDto carDto = new CarDto();
        System.err.println(carDto);
        carDto = carMapper.carToCarDto(new Car(new Date(), "2020-10-17 10:10:10"));
        System.err.println(carDto);
    }

}
