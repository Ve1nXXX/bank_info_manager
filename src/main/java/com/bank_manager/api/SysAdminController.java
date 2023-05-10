package com.bank_manager.api;

import com.bank_manager.constant.CommonConstants;
import com.bank_manager.dto.PageDto;
import com.bank_manager.pojo.SysAdmin;
import com.bank_manager.pojo.SysCunquRecord;
import com.bank_manager.service.SysAdminService;
import com.bank_manager.service.SysCunquRecordService;
import com.bank_manager.utils.MD5Utils;
import com.bank_manager.vo.ResponseVo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sys_admin")
@RequiredArgsConstructor
@Api(tags = "管理员模块")
public class SysAdminController extends ResponseVo {

    private final SysAdminService service;
    private final SysCunquRecordService cunquRecordService;


    /**
     * 查询分页
     *
     * @param page
     * @param key
     * @return
     */
    @ApiOperation("查询分页")
    @GetMapping("/getInfoListPage")
    public ResponseVo getInfoListPage(PageDto page, String key) {
        SysAdmin pojo = SysAdmin.builder().build();
        if (StringUtils.isNotBlank(key)) {
//            pojo.setPhone(key);
        }
        final Page result = service.page(page.getPage(), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }

    /**
     * 管理员个人信息
     *
     * @param page
     * @param key
     * @return
     */
    @ApiOperation("管理员个人信息")
    @GetMapping("/getInfoListPageInfor")
    public ResponseVo getInfoListPageInfor(PageDto page, String key) {
        SysAdmin pojo = SysAdmin.builder().build();
        if (StringUtils.isNotBlank(key)) {
            pojo.setId(Integer.valueOf(key));
        }
        final Page result = service.page(page.getPage(), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }

    /**
     * 新增修改
     *
     * @return
     */
    @ApiOperation("新增修改")
    @PostMapping("/saveOrUpdateInfo")
    public ResponseVo saveOrUpdate(SysAdmin pojo) {
        if (pojo.getId() == null) {
            List<SysAdmin> list = service.lambdaQuery().eq(SysAdmin::getPhone, pojo.getPhone()).list();
            if (!list.isEmpty()) {
                return failed("手机号已存在，请更换!");
            }
            pojo.setMoney(BigDecimal.ZERO);
        }
        return ok(service.saveOrUpdate(pojo));
    }

    /**
     * 登陆
     *
     * @param phone    用户名
     * @param password 密码
     * @return
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public ResponseVo login(@RequestParam String phone, @RequestParam String password, @RequestParam String loginType) {
        HashMap<Object, Object> res = Maps.newHashMap();
        SysAdmin one = service.lambdaQuery().eq(SysAdmin::getPhone, phone).eq(SysAdmin::getPassword, password).eq(SysAdmin::getLoginType, loginType).one();
        if (one == null) {
            return failed("手机号或密码错误");
        }
        res.put("user", one);
        res.put("token", UUID.randomUUID().toString());
        return ok(res);
    }

    /**
     * 存取款
     *
     * @param pojo
     * @return
     */
    @ApiOperation("存取款")
    @PostMapping("/cunqu")
    public ResponseVo cunqu(SysAdmin pojo) {
        SysAdmin byId = service.getById(pojo.getId());
        BigDecimal money = byId.getMoney();
        BigDecimal money1 = pojo.getMoney();
        BigDecimal add = money.add(money1);
        if (add.doubleValue() < 0) {
            return failed("超过余额，取款失败!");
        }
        pojo.setMoney(add);

        //保存存取款记录
        SysCunquRecord sysCunquRecord = new SysCunquRecord();
        sysCunquRecord.setName(byId.getName());
        sysCunquRecord.setUserId(pojo.getId());
        sysCunquRecord.setType(money1.doubleValue() < 0 ? "取款" : "存款");
        sysCunquRecord.setMoney(money1.abs());
        cunquRecordService.save(sysCunquRecord);

        return ok(service.saveOrUpdate(pojo));
    }


    /**
     * 删除
     *
     * @return
     */
    @ApiOperation("删除")
    @GetMapping("/deleteInfoList/{id}")
    public ResponseVo deleteInfoList(@PathVariable(name = "id") Integer id) {
        return ok(service.removeById(id));
    }


}
