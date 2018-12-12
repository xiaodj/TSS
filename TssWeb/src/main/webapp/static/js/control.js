/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['laydate', 'form'], function () {
    var form = layui.form;
    var laydate = layui.laydate;

    //时间范围
    laydate.render({
        elem: '#test9'
        ,type: 'time'
        ,range: true
    });

    //时间范围
    laydate.render({
        elem: '#test8'
        ,type: 'time'
        ,range: true
    });
});