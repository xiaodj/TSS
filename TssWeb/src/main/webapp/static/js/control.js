/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
}

layui.use(['layer','laydate', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.$;

    var onstarttime = "";
    var onendtime = "";
    var offstarttime = "";
    var offendtime = "";

    var index;
    $.ajax({
        async: false,
        cache:false,
        url:Host + "/v1/user/" +uid+"/set",
        type:"get",
        contentType:"application/json",
        dataType:"json",
        data:"",
        beforeSend:function () {
            index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        },
        success:function (msg) {
            if (msg.code == 0) {
                onstarttime = msg.onstarttime;
                onendtime = msg.onendtime;
                offstarttime = msg.offstarttime;
                offendtime = msg.offendtime;
                $('#timeout').val(msg.timeout);
            }else if (msg.code == 1){
                layer.msg(msg.Message.toString());
            }
        },
        complete:function () {
            layer.close(index);
        },
        error:function (msg) {
            layer.msg("网络异常");
        }
    });


    //时间范围
    laydate.render({
        elem: '#ontime'
        ,type: 'time'
        ,format:"HH:mm:ss"
        ,value:onstarttime + " - " + onendtime
        ,range: true
        ,done:function (value, date, endDate) {
            onstarttime = value.split(" - ")[0];//date.hours + ":" + date.minutes + ":" + date.seconds;
            onendtime = value.split(" - ")[1];//endDate.hours + ":" + endDate.minutes + ":" + endDate.seconds;
        }
    });

    //时间范围
    laydate.render({
        elem: '#offtime'
        ,type: 'time'
        ,format:"HH:mm:ss"
        ,value:offstarttime + " - " + offendtime
        ,range: true
        ,done:function (value, date, endDate) {
            offstarttime = value.split(" - ")[0];//date.hours + ":" + date.minutes + ":" + date.seconds;
            offendtime = value.split(" - ")[1];//endDate.hours + ":" + endDate.minutes + ":" + endDate.seconds;
        }
    });
    
    $('#SysSave').click(function () {
        var timeout = $('#timeout').val();
        var SysSetData = {
            "onstarttime":onstarttime,
            "onendtime":onendtime,
            "offstarttime":offstarttime,
            "offendtime":offendtime,
            "timeout":timeout
        };

        var index;
        $.ajax({
            //async: false,
            cache:false,
            url:Host + "/v1/user/" +uid+"/set",
            type:"put",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(SysSetData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    layer.msg("用户设置成功");
                }else if (msg.code == 1){
                    layer.msg(msg.Message.toString());
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