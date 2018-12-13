/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
}

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