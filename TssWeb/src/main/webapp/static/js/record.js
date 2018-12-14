/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

// window.onload = function () {
//     uid = sessionStorage.getItem("uid");
//     if (uid == null)
//         window.location.href = "/static/view/login.html";
// }

layui.use(['layer','table', 'laydate'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    var RTData = [
        {"date":"2018-12-10","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"08:06:00","outtime":"11:30:00","city":"2018-12-11","experience":"2019-03-17","score":"2019-02-10",},
        {"date":"2018-12-10","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"13:10:00","outtime":"17:32:00","city":"2018-12-11","experience":"2019-03-17","score":"2019-02-10",},
        {"date":"2018-12-10","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:20:00","outtime":"17:20:00","city":"2019-01-11","experience":"2018-11-11","score":null,},
        {"date":"2018-12-10","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"17:55:00","city":"2019-05-11","experience":"2019-01-10","score":null,},
        {"date":"2018-12-11","wid":"001","chname":"张三","surname":"Zhang","enname":"San","intime":"08:06:00","outtime":"17:32:00","city":"2018-12-11","experience":"2019-03-17","score":"2019-02-10",},
        {"date":"2018-12-11","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:20:00","outtime":"17:20:00","city":"2019-01-11","experience":"2018-11-11","score":null,},
        {"date":"2018-12-11","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"11:30:00","city":"2019-05-11","experience":"2019-01-10","score":null,},
        {"date":"2018-12-11","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"13:10:00","outtime":"17:55:00","city":"2019-05-11","experience":"2019-01-10","score":null,},
        {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"08:06:00","outtime":"11:30:00","city":"2019-01-11","experience":"2018-11-11","score":null,},
        {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"13:10:00","outtime":"15:32:00","city":"2019-01-11","experience":"2018-11-11","score":null,},
        {"date":"2018-12-12","wid":"002","chname":"李四","surname":"Li","enname":"Si","intime":"15:50:00","outtime":"17:32:00","city":"2019-01-11","experience":"2018-11-11","score":null,},
        {"date":"2018-12-12","wid":"003","chname":"王五","surname":"Wang","enname":"Wu","intime":"08:35:00","outtime":"17:55:00","city":"2019-05-11","experience":"2019-01-10","score":null,}
    ];

    table.render({
        elem: '#records',
        toolbar: '#toolbar',
        data:RTData,
        cols: [[
            {field:'date', width:'8%', title: '日期'}
            ,{field:'wid', width:'6%', title: '编号'}
            ,{field:'chname', width:'10%', title: '中文姓名'}
            ,{field:'surname', width:'8%', title: '英文姓'}
            ,{field:'enname', width:'8%', title: '英文名'}
            ,{field:'intime', width:'12%', title: '进入时间'}
            ,{field:'outtime', width:'12%', title: '离开时间'}
            ,{field:'city', width:'12%', templet: '#sexTp1', title: '绿卡到期日期'}
            ,{field:'experience', width:'12%', templet: '#sexTp2', title: '密卡到期日期'}
            ,{field:'score', width:'12%', templet: '#sexTp3', title: 'CP到期日期'}
        ]]
        ,page: true
    });


    //日期范围
    laydate.render({
        elem: '#daterange'
        ,value:GetDateStr(-7).valueOf() + " - " + getNowFormatDate().valueOf()
        ,range: true
        ,done:function (date, endDate) {

        }
    });
});