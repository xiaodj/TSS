/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
}

layui.use(['form','laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.$;

    laydate.render({
        elem: '#LID1'
    });
    laydate.render({
        elem: '#LID2'
    });
    laydate.render({
        elem: '#LID3'
    });

    var wid = getQueryString("wid");
    var index;
    $.ajax({
        async: false,
        url:Host + "/v1/user/"+uid+"/worker/"+wid,
        type:"get",
        contentType:"application/json",
        dataType:"json",
        data:"",
        beforeSend:function () {
            index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        },
        success:function (msg) {
            if (msg.code == 0) {
                layer.msg(JSON.stringify(msg));
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

    function getQueryString(name) {
        var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {
            return "";
        }
        return result[1];
    }
});
