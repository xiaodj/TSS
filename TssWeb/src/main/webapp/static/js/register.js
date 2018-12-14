/**
 * Created by xiaodj on 2018/12/5.
 */

layui.use(['layer','form'],function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;

    $('#Register').click(function () {
        var nickname = $('#NickName').val();
        var username = $('#UserName').val();
        var password = $('#PassWord').val();
        var rpassword = $('#RPassWord').val();
        if (password != rpassword){
            layer.msg("两次密码不匹配");
            return false;
        }

        var RegisterData = {
            "nickname":nickname,
            "username":username,
            "password":password
        };

        var index;
        $.ajax({
            async: false,
            cache:false,
            url:Host + "/v1/user/register",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(RegisterData),
            beforeSend:function () {
                index  = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    layer.msg("注册成功, 请点击右上角登陆进行跳转");
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
});