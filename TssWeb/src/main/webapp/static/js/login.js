/**
 * Created by xiaodj on 2018/12/5.
 */

layui.use(['layer','form'],function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;

    $('#Login').click(function () {
        var username = $('#UserName').val();
        var password = $('#PassWord').val();
        var LoginData = {
            "username":username,
            "password":password
        };
        var index;
        $.ajax({
            //async: false,
            cache:false,
            url:Host + "/v1/user/login",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(LoginData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    sessionStorage.setItem("uid", msg.uid);
                    sessionStorage.setItem("nickname", msg.nickname);
                    window.location.href = "/static/view/main.html";
                }else if (msg.code == 1){
                    layer.msg(msg.message.toString());
                }
            },
            complete:function () {
                layer.close(index);
            },
            error:function (msg) {
                layer.msg("网络异常");
            }
        });
    });

    $("body").bind("keypress",function(e){
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            e.preventDefault();
            $("#Login").click();
        }
    });
});