/**
 * Created by xiaodj on 2018/12/5.
 */

layui.use(['layer','element','form', 'laydate'],function () {
    var layer = layui.layer;
    var form = layui.form;
    var element = layer.element;
    var laydate = layui.laydate;
    var $ = layui.$;

    $('#Login').click(function () {
        var username = $('#UserName').val();
        var password = $('#PassWord').val();
        var LoginData = {
            "username":username,
            "password":password
        };
        $.ajax({
            //async: false,
            url:Host + "/v1/user/login",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(LoginData),
            beforeSend:function () {
                var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    sessionStorage.setItem("uid", msg.uid);
                    sessionStorage.setItem("nickname", msg.nickname);
                    window.location.href = "../view/main.html";
                }else if (msg.code == 1){
                    layer.msg(msg.Message.toString());
                }
            },
            error:function (msg) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                layer.msg("网络异常");
            }
        });

        // var index = layer.load(1, {
        //     shade: [0.1,'#fff'] //0.1透明度的白色背景
        // });
    });
});