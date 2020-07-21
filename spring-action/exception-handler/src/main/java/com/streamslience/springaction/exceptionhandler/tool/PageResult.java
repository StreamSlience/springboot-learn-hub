package com.streamslience.springaction.exceptionhandler.tool;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-07-21 11:38
 */
@Data
public class PageResult<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> restPage(List<T> list) {
        PageResult<T> result = new PageResult<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> restPage(Page<T> pageInfo) {
        PageResult<T> result = new PageResult<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }

    /**
     * 将mybatis分页后的数据转为分页信息
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> restPage(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setTotalPage((int) page.getPages());
        result.setPageNum((int) page.getCurrent());
        result.setPageSize((int) page.getSize());
        result.setTotal(page.getTotal());
        result.setList(page.getRecords());
        return result;
    }
}
