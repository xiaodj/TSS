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

    var rdTable = table.render({
        elem: '#realtime',
        data:JSON.parse(sessionStorage.getItem("realTimeData")),
        cols: [[
            {field:'id', width:'10%', title: '序号', type:'numbers'}
            ,{field:'username', width:'15%', title: '姓名'}
            ,{field:'intime', width:'15%', title: '进入时间'}
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