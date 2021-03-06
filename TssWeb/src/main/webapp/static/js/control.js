/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['layer','laydate', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.$;

    var uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.parent.location.href = "../view/login.html";

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
        //contentType:"application/json",
        dataType:"json",
        data:"",
        beforeSend:function () {
            index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        },
        success:function (msg) {
            if (msg.code == 0) {
                $('#RFDistance').val(msg.sensitivity);
                $('#TagStatus').val(msg.tagstatus);
                form.render();
                onstarttime = msg.onstarttime;
                onendtime = msg.onendtime;
                offstarttime = msg.offstarttime;
                offendtime = msg.offendtime;
                $('#timeout').val(msg.timeout);
            }else if (msg.code == 1){
                //layer.msg(msg.message.toString());
                Message(msg.message.toString());
            }
        },
        complete:function () {
            layer.close(index);
        },
        error:function (msg) {
            //layer.msg("网络异常");
            console.log(uid);
            Message("网络异常");
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
                    //layer.msg("用户设置成功");
                    Message("用户设置成功");
                }else if (msg.code == 1){
                    //layer.msg(msg.Message.toString());
                    Message(msg.Message.toString());
                }
            },
            complete:function () {
                layer.close(index);
            },
            error:function (msg) {
                //layer.msg("网络异常");
                Message("网络异常");
            }
        });
    });

    form.on('select(TagStatus)', function (data) {
        var tagData = {
            "tagidentify":data.value
        };
        var index;
        $.ajax({
            //async: false,
            cache:false,
            url:Host + "/v1/user/" +uid+"/device/tagidentify",
            type:"put",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(tagData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    //layer.msg("标签识别状态更改成功");
                    Message("标签识别状态更改成功");
                }else if (msg.code == 1){
                    //layer.msg(msg.message.toString());
                    Message(msg.message.toString());
                }
            },
            complete:function () {
                layer.close(index);
            },
            error:function (msg) {
                //layer.msg("网络异常");
                Message("网络异常");
            }
        });
    });
    form.on('select(RFDistance)', function (data) {
        var senData = {
            "sensitivity":data.value
        };
        var index;
        $.ajax({
            //async: false,
            cache:false,
            url:Host + "/v1/user/" +uid+"/device/sensitivity",
            type:"put",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(senData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    //layer.msg("灵敏度成功");
                    Message("灵敏度更改成功");
                }else if (msg.code == 1){
                    //layer.msg(msg.message.toString());
                    Message(msg.message.toString());
                }
            },
            complete:function () {
                layer.close(index);
            },
            error:function (msg) {
                //layer.msg("网络异常");
                Message("网络异常");
            }
        });
    });

    function Message(data) {
        layer.open({
            title: '提示'
            ,content: '<div style="text-align: center">'+ data +'</div>'
            ,btnAlign: 'c' //按钮居中
        });
    }
});