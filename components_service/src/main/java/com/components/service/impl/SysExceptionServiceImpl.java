package com.components.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.components.mapper.SysExceptionMapper;
import com.components.model.domain.SysException;
import com.components.service.SysExceptionService;
import org.springframework.stereotype.Service;

/**
 * @author dvbn
 * @description 针对表【sys_exception(异常日志表)】的数据库操作Service实现
 * @createDate 2024-05-08 16:01:11
 */
@Service
public class SysExceptionServiceImpl extends ServiceImpl<SysExceptionMapper, SysException>
        implements SysExceptionService {

}




