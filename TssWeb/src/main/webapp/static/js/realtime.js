/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use('table', function(){
    var table = layui.table;

       var RTData = [
           {"username":"张三", "sex":"08:02:11","city":"2018-12-11","experience":"2019-03-17", "score":"2019-02-10"},
           {"username":"李四", "sex":"08:02:11","city":"2019-01-11","experience":"2018-11-11", "score":null},
           {"username":"王五", "sex":"08:02:11","city":"2019-05-11","experience":"2019-01-10", "score":null}
       ];

    table.render({
        elem: '#realtime',
        data:RTData,
        cols: [[
            {field:'id', width:'10%', title: '序号', type:'numbers'}
            ,{field:'username', width:'15%', title: '姓名'}
            ,{field:'sex', width:'15%', title: '进入时间'}
            ,{field:'city', width:'20%', templet: '#sexTpl', title: '绿卡到期日期'}
            ,{field:'experience', width:'20%', templet: '#sexTpl1', title: '密卡到期日期'}
            ,{field:'score', width:'20%', templet: '#sexTpl2', title: 'CP到期日期'}
        ]]
        ,page: true
    });

});