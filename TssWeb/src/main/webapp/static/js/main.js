/**
 * Created by xiaodj on 2018/12/5.
 */
layui.define(['layer','form', 'element', 'table', 'laypage', 'layer'],function (exports) {
    var layer = layui.layer;
    var form = layui.form;
    var element = layui.element;
    var table = layui.table;
    var laypage = layui.laypage;
    var $ = layui.$;

    //document.getElementById("nickname").innerHTML = sessionStorage.getItem("nickname");
    $('#nickname').innerHTML = sessionStorage.getItem("nickname");

    //var page = document.getElementById("page");
    element.on('nav(main)', function (data) {
        if (data.context.id == "realtime"){
            document.getElementById("iframe").src = 'realtime.html';
        }else if (data.context.id == "recordquery"){
            document.getElementById("iframe").src = 'record.html';
        }else if (data.context.id == "workeradd"){
            document.getElementById("iframe").src = 'wkadd.html';
        }else if (data.context.id == "workerquery"){
            document.getElementById("iframe").src = 'wkquery.html';
        }else if (data.context.id == "controlcentre"){
            document.getElementById("iframe").src = 'control.html';
        }
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

    exports('main', {});
});