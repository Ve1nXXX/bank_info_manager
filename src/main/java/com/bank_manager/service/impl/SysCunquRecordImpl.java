package com.bank_manager.service.impl;

import com.bank_manager.mapper.SysCunquRecordMapper;
import com.bank_manager.pojo.SysCunquRecord;
import com.bank_manager.service.SysCunquRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
*   SysCunquRecord业务实现
*   @author xt
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysCunquRecordImpl extends ServiceImpl<SysCunquRecordMapper, SysCunquRecord> implements SysCunquRecordService {
}
