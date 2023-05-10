window.app = {
    serverUrl: "http://localhost:9998",

    logout: function () {
        localStorage.clear()
        window.location.reload()
    },

    getToken: function () {
        return localStorage.getItem("token")
    },

    setData: function (key, data) {
        return localStorage.setItem(key, data)
    },

    getData: function (key) {
        return localStorage.getItem(key)
    },

    removeData: function (key) {
        return localStorage.removeItem(key)
    },

    showBottom() {
        $(window).on('scroll', function () {
            let docHeight = $(document).height(); // 获取整个页面的高度(不只是窗口,还包括为显示的页面)
            let winHeight = $(window).height(); // 获取当前窗体的高度(显示的高度)
            let winScrollHeight = $(window).scrollTop(); // 获取滚动条滚动的距离(移动距离)
            //还有30像素的时候,就查询
            if (docHeight === winHeight + winScrollHeight) {
                //到底(一般是离到底还有一段距离就查询的)
                console.log(docHeight)
                $('.fly-footer').hide(500)
            } else {
                $('.fly-footer').show(500)
            }
        });
    },

    checkClientLogin() {
        let token = localStorage.getItem("tokenClient")
        if (!token) {
            $(".noLogin").show()
            $(".isLogin").hide()
            return false
        } else {
            $(".noLogin").hide()
            $(".isLogin").show()
            let u = localStorage.getItem("userClient")
            $(".loginName").html(JSON.parse(u).username)
            return true
        }
    },
    checkLogin: function () {
        let token = this.getToken()
        if (!token) {
            layer.msg("客官,请先登录", {icon: 1})
            setTimeout(function () {
                window.location.href = "login.html"
            }, 1500)
        }
    },

    openLogin: function () {
        layui.layer.open({
            title: "登陆",
            type: 2,
            content: "tuser/tuserLogin.html",
            area: ['300px', '220px'],
            success: function (layero, index) {
                let body = layui.layer.getChildFrame('body', index);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回首页', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 200)
            }
        })
    },

    openRegister: function () {
        layui.layer.open({
            title: "注册",
            type: 2,
            content: "tuser/tuserRegister.html",
            area: ['500px', '400px'],
            success: function (layero, index) {
                let body = layui.layer.getChildFrame('body', index);
                setTimeout(function () {
                    layui.layer.tips('点击此处返回首页', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 200)
            }
        })
    },

    logout: function () {
        layer.confirm('确定退出吗？', {
            btn: ['确定', '取消']//按钮
        }, function (index) {
            layer.close(index);
            //此处请求后台程序,下方是成功后的前台处理……
            index = layer.load(0, {shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格,支持0-2
            setTimeout(function () {
                layer.close(index);
                app.removeData("userClient")
                app.removeData("tokenClient")
                window.location.reload()
            }, 1500)

        });
    },

    checkOnline: function () {
        let user
        if (app.checkClientLogin()) {
            user = JSON.parse(app.getData("userClient"))
            $.get(app.serverUrl + '/t_user/detail/' + user.id, function (res) {
                if (res.code == 0) {
                    if (res.data.status == '下线') {

                        layui.use(['laypage', 'table'], function () {
                            laypage = layui.laypage,
                                $ = layui.$;
                            layer.msg("此用户已被强制下线", {icon: 2})
                        });
                        setTimeout(function () {
                            app.removeData("userClient")
                            app.removeData("tokenClient")
                            window.location.reload()
                        }, 1500)
                    }
                }
            })
        }

    },

    focus: function (houseId) {
        window.location.href = "focus.html"
    },

    myNews() {
        window.location.href = "myNews.html"
    },

    getQueryString: function () {
        let qs = location.search.substr(1), // 获取url中"?"符后的字串
            args = {}, // 保存参数数据的对象
            items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
            item = null,
            len = items.length;

        for (var i = 0; i < len; i++) {
            item = items[i].split("=");
            var name = decodeURIComponent(item[0]),
                value = decodeURIComponent(item[1]);
            if (name) {
                args[name] = value;
            }
        }
        return args;
    },

    checkUser: function (client_user) {
        if (client_user) {
            $(".loginName").html(client_user.user.username)
            $("#avatar").attr("src", app.serverUrl + "/" + client_user.user.avatar)
            $(".noLogin").hide()
            $(".isLogin").show()
        }
    },

    getLoginUser: function () {
        return JSON.parse(localStorage.getItem("admin")).user
    }

}
