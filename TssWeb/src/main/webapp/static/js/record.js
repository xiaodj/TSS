/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.parent.location.href = "../view/login.html";
    sessionStorage.setItem("recordData", "[]");
}

layui.use(['layer','table', 'laydate'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ = layui.$;

    // var RTData = [
    //     {"date":"2018-12-10","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"08:06:00","outtime":"11:30:00","lc1":null,"lc2":"2019-03-17","lc3":"2019-02-10",},
    //     {"date":"2018-12-10","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"13:10:00","outtime":"17:32:00","lc1":"2018-12-11","lc2":"2019-04-17","lc3":"2019-02-10",},
    //     {"date":"2018-12-10","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:20:00","outtime":"17:20:00","lc1":"2019-01-11","lc2":"2018-11-11","lc3":"2018-11-11",},
    //     {"date":"2018-12-10","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"17:55:00","lc1":"2019-05-11","lc2":"2019-01-10","lc3":"2019-04-17",},
    //     {"date":"2018-12-11","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"08:06:00","outtime":"17:32:00","lc1":"2018-12-11","lc2":"2019-03-17","lc3":"2019-02-10",},
    //     {"date":"2018-12-11","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:20:00","outtime":"17:20:00","lc1":"2019-01-11","lc2":"2018-11-11","lc3":null,},
    //     {"date":"2018-12-11","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"11:30:00","lc1":"2019-05-11","lc2":"2019-01-10","lc3":null,},
    //     {"date":"2018-12-11","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"13:10:00","outtime":"17:55:00","lc1":"2019-05-11","lc2":"2019-01-10","lc3":null,},
    //     {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:06:00","outtime":"11:30:00","lc1":"2019-01-11","lc2":"2018-11-11","lc3":null,},
    //     {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"13:10:00","outtime":"15:32:00","lc1":"2019-01-11","lc2":"2018-11-11","lc3":null,},
    //     {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"15:50:00","outtime":"17:32:00","lc1":"2019-01-11","lc2":"2018-11-11","lc3":null,},
    //     {"date":"2018-12-12","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"17:55:00","lc1":"2019-05-11","lc2":"2019-01-10","lc3":null,}
    // ];

    var recordTable = table.render({
        elem: '#records'
        ,data:JSON.parse(sessionStorage.getItem("recordData"))
        ,toolbar: '#toolbar'
        ,defaultToolbar: [/*'filter', 'print', 'exports'*/]
        ,cols: [[
            {field:'date', width:'11%', title: '日期'}
            ,{field:'wid', width:'6%', title: '编号'}
            ,{field:'chname', width:'9%', title: '中文姓名'}
            ,{field:'surname', width:'8%', title: '英文姓'}
            ,{field:'enname', width:'8%', title: '英文名'}
            ,{field:'intime', width:'11%', title: '进入时间'}
            ,{field:'outtime', width:'11%', title: '离开时间'}
            ,{field:'lc1', width:'12%', templet: '#Lice1', title: '绿卡到期日期'}
            ,{field:'lc2', width:'12%', templet: '#Lice2', title: '密卡到期日期'}
            ,{field:'lc3', width:'12%', templet: '#Lice3', title: 'CP到期日期'}
        ]]
        ,page: true
    });

    //查询的开始日期
    laydate.render({
        elem: '#startdate'
        //,value:GetDateStr(-7).valueOf() + " - " + getNowFormatDate().valueOf()
        //,range: true
        ,done:function (date, endDate) {

        }
    });
    //查询的截至日期
    laydate.render({
        elem: '#enddate'
        //,value:GetDateStr(-7).valueOf() + " - " + getNowFormatDate().valueOf()
        //,range: true
        ,done:function (date, endDate) {

        }
    });

    //头工具栏事件
    table.on('toolbar(records)', function(obj){
        var URL = "";
        switch(obj.event){
            case 'Query':
                sdate = $('#startdate').val();
                edate = $('#enddate').val();
                if (sdate > edate){
                    layer.msg("开始日期不能在截止日期之后");
                    return;
                }
                wid = $('#wid').val();
                if (wid == ""){
                    if (sdate != "" && edate != ""){
                        URL = Host + "/v1/user/"+uid +"/records/date/"+sdate+"/"+edate;
                    }else {
                        layer.msg("时间范围任何一个不能为空");
                        return;
                    }
                }else{
                    if (sdate == "" && edate == ""){
                        URL = Host + "/v1/user/"+uid+"/worker/" + wid +"/records";
                    }else if (sdate != "" && edate != ""){
                        URL = Host + "/v1/user/"+uid+"/worker/" + wid + "/records/date/"+sdate+"/"+edate;
                    }else {
                        layer.msg("时间范围任何一个不能为空");
                        return;
                    }
                }

                var loading;
                $.ajax({
                    //async: false,
                    cache:false,
                    url:URL,
                    type:"get",
                    contentType:"application/json",
                    dataType:"json",
                    data:"",
                    beforeSend:function () {
                        loading = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                    },
                    success:function (msg) {
                        if (msg.code == 0) {
                            sessionStorage.setItem("recordData", JSON.stringify(msg.records));
                            recordTable.reload({data:JSON.parse(sessionStorage.getItem("recordData"))});
                            laydate.render({elem: '#startdate'});
                            laydate.render({elem: '#enddate'});
                        }else if (msg.code == 1){
                            sessionStorage.setItem("recordData", "[]");
                            recordTable.reload({data:JSON.parse(sessionStorage.getItem("recordData"))});
                            laydate.render({elem: '#startdate'});
                            laydate.render({elem: '#enddate'});
                            layer.msg(msg.message.toString());
                        }
                    },
                    complete:function () {
                        layer.close(loading);
                    },
                    error:function (msg) {
                        laydate.render({elem: '#startdate'});
                        laydate.render({elem: '#enddate'});
                        layer.msg("网络异常");
                    }
                });
                break;
        };
    });
});