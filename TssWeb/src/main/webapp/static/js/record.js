/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['table', 'laydate'], function () {
    var table = layui.table;
    var laydate = layui.laydate;

    table.render({
        elem: '#records',
        toolbar: '#toolbar',
        //data:RTData,
        cols: [[
            {field:'date', width:'10%', title: '日期'}
            ,{field:'wid', width:'10%', title: '员工编号'}
            ,{field:'chname', width:'10%', title: '中文姓名'}
            ,{field:'surname', width:'10%', title: '英文姓'}
            ,{field:'enname', width:'10%', title: '英文名'}
            ,{field:'intime', width:'10%', title: '进入时间'}
            ,{field:'outtime', width:'10%', title: '离开时间'}
            ,{field:'city', width:'10%', title: '绿卡到期日期'}
            ,{field:'experience', width:'10%', title: '密卡到期日期'}
            ,{field:'score', width:'10%', title: 'CP到期日期'}
        ]]
        ,page: true
    });

    //日期范围
    laydate.render({
        elem: '#test6'
        ,range: true
    });
});