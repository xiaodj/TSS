/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
}

layui.use(['table', 'layer'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.$;

    var index;
    $.ajax({
        async: false,
        cache:false,
        url:Host + "/v1/user/"+uid+"/workers",
        type:"get",
        contentType:"application/json",
        dataType:"json",
        data:"",
        beforeSend:function () {
            index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        },
        success:function (msg) {
            if (msg.code == 0) {
                sessionStorage.removeItem("member");
                sessionStorage.setItem("member", JSON.stringify(msg.member));
            }else if (msg.code == 1){
                sessionStorage.removeItem("member");
                layer.msg(msg.message.toString());
            }
        },
        complete:function () {
            layer.close(index);
        },
        error:function (msg) {
            layer.msg("网络异常");
        }
    });

    table.render({
        elem: '#wkquery'
        ,data:JSON.parse(sessionStorage.getItem("member"))
        ,toolbar: '#toolbar'
        ,title: '用户数据表'
        ,cols: [[
            {field:'wid', title:'员工编号', width:"20%"}
            ,{field:'chname', title:'中文姓名', width:"20%"}
            ,{field:'surname', title:'英文姓', width:"20%"}
            ,{field:'enname', title:'英文名', width:"20%"}
            ,{field: 'right', title:'操作', toolbar: '#operate', width:"20%"}
        ]]
        ,page: true
    });

    //头工具栏事件
    table.on('toolbar(wkquery)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'Query':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
        };
    });

    //监听行工具事件
    table.on('tool(wkquery)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('是否确认删除员工'+ data.chname, function(index){
                var loading;
                $.ajax({
                    async: false,
                    url:Host + "/v1/user/"+uid+"/worker/"+data.wid,
                    type:"delete",
                    contentType:"application/json",
                    dataType:"json",
                    data:"",
                    beforeSend:function () {
                        loading = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                    },
                    success:function (msg) {
                        if (msg.code == 0) {
                            layer.msg("删除成功");
                            obj.del();
                        }else if (msg.code == 1){
                            layer.msg(msg.message.toString());
                        }
                    },
                    complete:function () {
                        layer.close(loading);
                    },
                    error:function (msg) {
                        layer.msg("网络异常");
                    }
                });
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            window.parent.location.href = "/static/view/wkupdate.html" + "?wid=" + data.wid;
        } else if (obj.event === 'detail'){
            window.parent.location.href = "/static/view/wkdetail.html" + "?wid=" + data.wid;
        }
    });
});
