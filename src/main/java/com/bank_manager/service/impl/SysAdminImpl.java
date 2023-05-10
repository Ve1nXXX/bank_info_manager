package com.bank_manager.service.impl;

import com.bank_manager.mapper.SysAdminMapper;
import com.bank_manager.pojo.SysAdmin;
import com.bank_manager.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
*   SysAdmin业务实现
*   @author xt
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysAdminImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {
}