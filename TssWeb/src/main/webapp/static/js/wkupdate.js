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
        form.render('select', 'lcname1');
    }

    $('#Save').click(function () {
        var WID = $('#wid').val();
        var ChName = $('#chname').val();
        var SurName = $('#surname').val();
        var ENName = $('#enname').val();
        var WKCard = $('#wkcard').val();
        var CCRSID = $('#ccrsid').val();
        var WKOther = $('#other').val();
        var LCName1 = $('#lcname1').val();
        var OutDate1 = $('#outdate1').val();
        var LCName2 = $('#lcname2').val();
        var OutDate2 = $('#outdate2').val();
        var LCName3 = $('#lcname3').val();
        var OutDate3 = $('#outdate3').val();
        var TagName1 = $('#tagname1').val();
        var TID1 = $('#tid1').val();

        var WKUpdateData = {
            "wid":WID,
            "chname":ChName,
            "surname":SurName,
            "enname":ENName,
            "wkcard":WKCard,
            "ccrsid":CCRSID,
            "other":WKOther,
            "licences":[
                {"lcname":LCName1,"lcdate":OutDate1},
                {"lcname":LCName2,"lcdate":OutDate2},
                {"lcname":LCName3,"lcdate":OutDate3}
            ],
            "tags":[
                {"tagname":TagName1,"tid":TID1}
            ]
        };

        var index;
        $.ajax({
            async: false,
            cache:false,
            url:Host + "/v1/user/"+uid+"/worker/"+wid,
            type:"put",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(WKUpdateData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    //layer.msg("修改成功");
                    Message("修改成功");
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

    function getQueryString(name) {
        var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {
            return "";
        }
        return result[1];
    }

    function Message(data) {
        layer.open({
            title: '提示'
            ,content: '<div style="text-align: center">'+ data +'</div>'
            ,btnAlign: 'c' //按钮居中
        });
    }
});
