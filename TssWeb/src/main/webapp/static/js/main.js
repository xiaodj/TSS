/**
 * Created by xiaodj on 2018/12/5.
 */
var uid = null;
window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";

    //连接服务端websocket
    var websocket;
    //判断浏览器是否支持websocket
    if ('WebSocket' in window){
        websocket = new WebSocket(wsHost + "/websocket/message");
    } else {
        websocket = new SockJS(wsHost + "/sockjs/message");
    }

    //连接建立时触发该消息
    websocket.onopen = function(evnt){
        alert("连接成功");
    }

    //服务端关闭连接时触发该消息
    websocket.onclose = function (evnt) {
        alert("关闭成功");
    }

    //网络发生错误时触发该消息
    websocket.onerror = function (evnt) {
        alert("发生错误");
    }

    //接收服务端发送的消息时触发该消息
    websocket.onmessage = function(evnt){
        alert("接收到消息：" + evnt.data);
    }
    //主动关闭连接
    //主动发送消息
}

layui.use(['layer','element'],function () {
    var layer = layui.layer;
    var element = layui.element;
    var $ = layui.$;

    document.getElementById("nickname").innerHTML = sessionStorage.getItem("nickname");
    element.on('nav(main)', function (data) {
        if (data.context.id == "realtime"){
            document.getElementById("iframe").src = '../view/realtime.html';
        }else if (data.context.id == "recordquery"){
            document.getElementById("iframe").src = '../view/record.html';
        }else if (data.context.id == "workeradd"){
            document.getElementById("iframe").src = '../view/wkadd.html';
        }else if (data.context.id == "workerquery"){
            document.getElementById("iframe").src = '../view/wkquery.html';
        }else if (data.context.id == "controlcentre"){
            document.getElementById("iframe").src = '../view/control.html';
        }
    });
    
    $('#exit').click(function () {
        sessionStorage.removeItem("uid");
        window.location.href = "../view/login.html";
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