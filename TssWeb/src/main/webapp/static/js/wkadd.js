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
        elem: '#OutDate1'
    });
    laydate.render({
        elem: '#OutDate2'
    });
    laydate.render({
        elem: '#OutDate3'
    });

    // var LCName1, LCName2, LCName3;
    // form.on('select(LCName1)', function (data) {
    //     LCName1 = data.elem[data.elem.selectedIndex].text;
    //     form.render();
    // });
    // form.on('select(LCName2)', function (data) {
    //     LCName2 = data.elem[data.elem.selectedIndex].text;
    //     form.render();
    // });
    // form.on('select(LCName3)', function (data) {
    //     LCName3 = data.elem[data.elem.selectedIndex].text;
    //     form.render();
    // });
    // form.on('select(LCName3)', function (data) {
    //     LCName3 = data.elem[data.elem.selectedIndex].text;
    //     form.render();
    // });

    $('#WKAdd').click(function () {
        var WID = $('#WID').val();
        var ChName = $('#ChName').val();
        var SurName = $('#SurName').val();
        var ENName = $('#ENName').val();
        var WKCard = $('#WKCard').val();
        var CCRSID = $('#CCRSID').val();
        var WKOther = $('#WKOther').val();
        var LCName1 = $('#LCName1').val();
        var OutDate1 = $('#OutDate1').val();
        var LCName2 = $('#LCName2').val();
        var OutDate2 = $('#OutDate2').val();
        var LCName3 = $('#LCName3').val();
        var OutDate3 = $('#OutDate3').val();
        var TagName1 = $('#TagName1').val();
        var TID1 = $('#TID1').val();

        var WKAddData = {
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

        //window.parent.WKAdd(WKAddData);
        var index;
        $.ajax({
            async: false,
            cache:false,
            url:Host + "/v1/user/"+uid+"/worker",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(WKAddData),
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    //layer.msg("员工添加成功");
                    Message("员工添加成功");
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