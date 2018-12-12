/**
 * Created by xiaodj on 2018/12/11.
 */
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

    form.render('select');

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
        var index;
        $.ajax({
            //async: false,
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
                    layer.msg("员工添加成功");
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