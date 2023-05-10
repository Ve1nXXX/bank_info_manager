let loginType = '0';
layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form, layer = parent.layer === undefined ? layui.layer : top.layer
    $ = layui.jquery;


    form.on('radio(loginType)', function (data) {
        loginType = data.value
    });

    layui.form.on('select(mySelect)', function (data) {
        console.log(data);
        loginType = data.value
    })
    layui.form.render('select', 'myDiv');
    //登录按钮
    form.on("submit(login)", function (data) {
        let url = app.serverUrl + "/sys_admin/login"
        localStorage.setItem("loginType", loginType)
        $.post(url, {
            phone: $("#userName").val(), password: $("#password").val(), loginType: loginType
        }, function (res) {
            if (res.code == 0) {
                console.log(res)
                app.setData("admin", JSON.stringify(res.data))
                if (loginType === '0') {
                    //跳转操作
                    window.location.href = "index.html";
                } else {
                    //跳转操作
                    window.location.href = "index-user.html";
                }

            } else {
                layer.msg(res.msg, {icon: 2})
            }
        })
        return false;
    })
//注册按钮
    form.on("submit(register)", function (data) {
        let url = app.serverUrl + "/sys_admin/saveOrUpdateInfo"
        localStorage.setItem("loginType", loginType)
        $.post(url, {
            phone: $("#userName").val(), password: $("#password").val(), name: $("#name").val(),loginType:loginType
        }, function (res) {
            if (res.code === 0) {
                layer.msg('注册成功,请登录', {icon: 2})
                setTimeout(function () {
                    window.location.href = 'login.html'
                }, 1500)
            } else {
                layer.msg(res.msg, {icon: 2})
            }
        })
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
