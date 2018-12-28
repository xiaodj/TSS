/**
 * Created by xiaodj on 2018/12/11.
 */
var uid = null;

window.onload = function () {
    uid = sessionStorage.getItem("uid");
    if (uid == null)
        window.location.href = "/static/view/login.html";
    sessionStorage.setItem("member", "[]");
}

layui.use(['table', 'layer'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.$;

    var wktable = table.render({
        elem: '#wkquery'
        ,data:JSON.parse(sessionStorage.getItem("member"))
        ,toolbar: '#toolbar'
        ,defaultToolbar: [/*'filter', 'print', 'exports'*/]
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
        var wkurl = "";
        switch(obj.event){
            case 'Query':
                swid = $('#swid').val();
                ewid = $('#ewid').val();
                tid = $('#tid').val();
                if (tid == ""){
                    if (swid != "" && ewid != ""){
                        wkurl = Host + "/v1/user/"+uid+"/workers/wid/"+swid+"/"+ewid;
                    }else {
                        layer.msg("员工编号范围任何一个不能为空");
                        return;
                    }
                }else{
                    if (swid != "" && ewid != ""){
                        wkurl = Host + "/v1/user/"+uid+"/workers/tid/"+tid;
                    }else if (swid == "" && ewid == ""){
                        wkurl = Host + "/v1/user/"+uid+"/workers/wid/"+swid+"/"+ewid+"/tid/"+tid;
                    }else {
                        layer.msg("员工编号范围任何一个不能为空");
                        return;
                    }
                }

                var loading;
                $.ajax({
                    //async: false,
                    cache:false,
                    url:wkurl,
                    type:"get",
                    contentType:"application/json",
                    dataType:"json",
                    data:"",
                    beforeSend:function () {
                        loading = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                    },
                    success:function (msg) {
                        if (msg.code == 0) {
                            sessionStorage.setItem("member", JSON.stringify(msg.member));
                            wktable.reload({data:JSON.parse(sessionStorage.getItem("member"))});
                        }else if (msg.code == 1){
                            sessionStorage.setItem("member", "[]");
                            wktable.reload({data:JSON.parse(sessionStorage.getItem("member"))});
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
                    cache:false,
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
            window.location.href = "../view/wkupdate.html" + "?wid=" + data.wid;
        } else if (obj.event === 'detail'){
            window.location.href = "../view/wkdetail.html" + "?wid=" + data.wid;
        }
    });


});
