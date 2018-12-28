/**
 * Created by xiaodj on 2018/12/11.
 */

var uid = null;
window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "../view/login.html";
    sessionStorage.setItem("realTimeData", "[]");
}

layui.use('table', function(){
    var table = layui.table;

       // var RTData = [
       //     {"username":"张三", "sex":"08:02:11","city":"2018-12-11","experience":"2019-03-17", "score":"2019-02-10"},
       //     {"username":"李四", "sex":"08:02:11","city":"2019-01-11","experience":"2018-11-11", "score":""},
       //     {"username":"王五", "sex":"08:02:11","city":"2019-05-11","experience":"2019-01-10", "score":""}
       // ];

    var rdTable = table.render({
        elem: '#realtime',
        data:JSON.parse(sessionStorage.getItem("realTimeData")),
        cols: [[
            {field:'id', width:'10%', title: '序号', type:'numbers'}
            ,{field:'tid', width:'0%', title:'标签编码', style:'display:none;'}
            ,{field:'username', width:'15%', title: '姓名'}
            ,{field:'time', width:'15%', title: '进入时间'}
            ,{field:'lc1', width:'20%', templet: '#Licel', title: '绿卡到期日期'}
            ,{field:'lc2', width:'20%', templet: '#Lice2', title: '密卡到期日期'}
            ,{field:'lc3', width:'20%', templet: '#Lice3', title: 'CP到期日期'}
        ]]
        ,page: true
    });
    
    function Refresh() {
        rdTable.reload({data:JSON.parse(sessionStorage.getItem("realTimeData"))});
    }

});