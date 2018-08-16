

    //退出登录，清空所有子系统的cookie
    $("#loginout").click (function (event) {
        $.ajax({
            url: "http://futuregroup.club:8080/sso/loginout",
            type: "get",
            jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
            jsonpCallback:"success_jsonpCallback",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
            dataType: "jsonp", //指定服务器返回的数据类型
            success: function (data) {
                window.location.href="/loginout";
                eachUrl(data);//循环清理掉所有子系统cookie
            },error:function (data) {
                console.log(data.jqXHR+" "+data.status+" "+data.error);
            }
        });
    });

    function eachUrl(arrDomain) {
        jQuery.each(arrDomain, function () {  // this 指定值
            //循环访问
            $.ajax({
                url: this,
                type: "get",
                dataType: "jsonp" //指定服务器返回的数据类型
            });
        });
    }
