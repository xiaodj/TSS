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

    //连接服务端websocket
    var websocket;
    //判断浏览器是否支持websocket
    if ('WebSocket' in window){
        websocket = new WebSocket(wsHost + "/webSocketServer");
    } else {
        websocket = new SockJS(wsHost + "/sockjs/webSocketServer");
    }

    //连接建立时触发该消息
    websocket.onopen = function(evnt){
        $('#msg').addClass("layui-bg-green");
    }

    //服务端关闭连接时触发该消息
    websocket.onclose = function (evnt) {
        $('#msg').removeClass("layui-bg-green");
        sessionStorage.setItem("realTimeData", "[]");
        nframe.window.Refresh();
    }

    //网络发生错误时触发该消息
    websocket.onerror = function (evnt) {
        $('#msg').removeClass("layui-bg-green");
        sessionStorage.setItem("realTimeData", "[]");
        nframe.window.Refresh();
    }

    //接收服务端发送的消息时触发该消息
    websocket.onmessage = function(evnt){
        sessionStorage.setItem("realTimeData", evnt.data);
        nframe.window.Refresh();
    }

    //发送消息
    function send() {
        var message = "111111";
        websocket.send(message);
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

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
        var index;
        $.ajax({
            //async: false,
            cache:false,
            url:Host + "/v1/user/" + uid + "/exit",
            type:"post",
            contentType:"application/json",
            dataType:"json",
            data:"",
            beforeSend:function () {
                index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            },
            success:function (msg) {
                if (msg.code == 0) {
                    sessionStorage.clear();
                    window.location.href = "../view/login.html";
                }else if (msg.code == 1){
                    //layer.msg(msg.message.toString());
                    Message(msg.message.toString());
                }
            },
            complete:function () {
                layer.close(index);
            },
            error:function (msg) {
                //layer.msg("网络异常");
                Message("网络异常");
            }
        });
    });

    function Message(data) {
        layer.open({
            title: '提示'
            ,content: '<div style="text-align: center">'+ data +'</div>'
            ,btnAlign: 'c' //按钮居中
        });
    }

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