package com.bank_manager.api;

import com.bank_manager.constant.CommonConstants;
import com.bank_manager.dto.PageDto;
import com.bank_manager.pojo.SysCunquRecord;
import com.bank_manager.service.SysCunquRecordService;
import com.bank_manager.vo.ResponseVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys_cunqu_record")
@RequiredArgsConstructor
public class SysCunquRecordController extends ResponseVo {

    private final SysCunquRecordService service;


    /**
     * 查询分页
     *
     * @param page
     * @param key
     * @return
     */
    @GetMapping("/getInfoListPage")
    public ResponseVo getInfoListPage(PageDto page, String key) {
        SysCunquRecord pojo = SysCunquRecord.builder().build();
        if (StringUtils.isNotBlank(key)) {
//            pojo.setId(Integer.valueOf(key));
        }
        final Page result = service.page(page.getPage(), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "");
    }

    /**
     * 新增修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdateInfo")
    public ResponseVo saveOrUpdate(SysCunquRecord pojo) {
        return ok(service.saveOrUpdate(pojo));
    }

    /**
     * 删除
     *
     * @return
     */
    @GetMapping("/deleteInfoList/{id}")
    public ResponseVo deleteInfoList(@PathVariable(name = "id") Integer id) {
        return ok(service.removeById(id));
    }


}
