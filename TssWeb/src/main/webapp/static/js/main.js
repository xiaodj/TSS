/**
 * Created by xiaodj on 2018/12/5.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
}

layui.use(['layer','element'],function () {
    var layer = layui.layer;
    var element = layui.element;
    var $ = layui.$;

    document.getElementById("nickname").innerHTML = sessionStorage.getItem("nickname");
    element.on('nav(main)', function (data) {
        if (data.context.id == "realtime"){
            document.getElementById("iframe").src = '/static/view/realtime.html';
        }else if (data.context.id == "recordquery"){
            document.getElementById("iframe").src = '/static/view/record.html';
        }else if (data.context.id == "workeradd"){
            document.getElementById("iframe").src = '/static/view/wkadd.html';
        }else if (data.context.id == "workerquery"){
            document.getElementById("iframe").src = '/static/view/wkquery.html';
        }else if (data.context.id == "controlcentre"){
            document.getElementById("iframe").src = '/static/view/control.html';
        }
    });
    
    $('#exit').click(function () {
        sessionStorage.removeItem("uid");
        window.location.href = "/static/view/login.html";
    });

    // //添加一行
    // var oldData = table.cache["test"];
    // var data1 = {"username": "dj"};
    // oldData.push(data1);
    // table.reload('test', {data: oldData});
    //
    // //删除CC这一行
    // var oldData1 = table.cache["test"];
    // oldData1.forEach(function (item, index) {
    //     if (item.username == "cc")
    //         oldData1.splice(index, 1);
    // });
    //
    // table.reload('test', {data: oldData1});
});