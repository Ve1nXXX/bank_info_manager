var $, tab, dataStr, layer;
layui.config({
    base: "js/"
}).extend({
    "bodyTab": "bodyTab"
})
layui.use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        element = layui.element;
    $ = layui.$;
    let common = layui.common
    layer = parent.layer === undefined ? layui.layer : top.layer;

    let loginType = localStorage.getItem("loginType")
    console.log('loginType', loginType)
    if (loginType === '0') {
        tab = layui.bodyTab({
            openTabNum: "50",  //最大可打开窗口数量
            url: "json/navs.json" //获取菜单json地址
        });
    } else if (loginType === '1') {
        tab = layui.bodyTab({
            openTabNum: "50",  //最大可打开窗口数量
            url: "json/navs1.json" //获取菜单json地址
        });
    }

    let username = app.getData("admin")
    if (username) {
        username = JSON.parse(username)
        $(".adminName").html(username.user.phone)
    } else {
        layer.msg("登录失效,请重新登录", {icon: 2})
        setTimeout(function () {
            window.location.href = 'login.html'
        }, 1000)

    }

    function getData(json) {
        $.getJSON(tab.tabConfig.url, function (data) {
            if (json == "contentManagement") {
                dataStr = data.contentManagement;
                //重新渲染左侧菜单
                tab.render();
            } else if (json == "memberCenter") {
                dataStr = data.memberCenter;
                //重新渲染左侧菜单
                tab.render();
            } else if (json == "systemeSttings") {
                dataStr = data.systemeSttings;
                //重新渲染左侧菜单
                tab.render();
            } else if (json == "seraphApi") {
                dataStr = data.seraphApi;
                //重新渲染左侧菜单
                tab.render();
            }
        })
    }

    //页面加载时判断左侧菜单是否显示
    //通过顶部菜单获取左侧菜单
    $(".topLevelMenus li,.mobileTopLevelMenus dd").click(function () {
        if ($(this).parents(".mobileTopLevelMenus").length != "0") {
            $(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        } else {
            $(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        }
        $(".layui-layout-admin").removeClass("showMenu");
        $("body").addClass("site-mobile");
        getData($(this).data("menu"));
        //渲染顶部窗口
        tab.tabMove();
    })

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        if ($(".topLevelMenus li.layui-this a").data("url")) {
            layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
            return false;
        }
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        tab.tabMove();
    })

    //通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用,实际开发中通过接口传参的方式获取导航数据
    getData("contentManagement");

    //手机设备的简单适配
    $('.site-tree-mobile').on('click', function () {
        $('body').addClass('site-mobile');
    });
    $('.site-mobile-shade').on('click', function () {
        $('body').removeClass('site-mobile');
    });

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function () {
        //如果不存在子级
        if ($(this).siblings().length == 0) {
            addTab($(this));
            $('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })
    $(".logout").click(function () {
        localStorage.clear()
        window.location.href = "login.html"
    })
})

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}
