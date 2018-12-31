/**
 * Created by xiaodj on 2018/12/11.
 */

var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.parent.location.href = "../view/login.html";
}

layui.use(['form','laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var $ = layui.$;

    laydate.render({
        elem: '#outdate1'
    });
    laydate.render({
        elem: '#outdate2'
    });
    laydate.render({
        elem: '#outdate3'
    });

    var wid = getQueryString("wid");
    var index;
    $.ajax({
        async: false,
        cache:false,
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
                Show(msg);
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

    //赋值到对应的控件上
    function Show(data) {
        $('#wid').val(data.wid);
        $('#chname').val(data.chname);
        $('#surname').val(data.surname);
        $('#enname').val(data.enname);
        $('#wkcard').val(data.wkcard);
        $('#ccrsid').val(data.ccrsid);
        $('#other').val(data.other);
        $('#lcname1').val(data.licences[0].lcname);
        $('#outdate1').val(data.licences[0].lcdate);
        $('#lcname2').val(data.licences[1].lcname);
        $('#outdate2').val(data.licences[1].lcdate);
        $('#lcname3').val(data.licences[2].lcname);
        $('#outdate3').val(data.licences[2].lcdate);
        $('#tagname1').val(data.tags[0].tagname);
        $('#tid1').val(data.tags[0].tid);
        form.render();
    }

    function getQueryString(name) {
        var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {
            return "";
        }
        return result[1];
    }
});
