/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['table', 'layer'], function () {
    var table = layui.table;
    var layer = layui.layer;

    var RTData = [
        {"wid":"001", "chname":"张三","surname":"Zhang","enname":"san"},
        {"wid":"002", "chname":"李四","surname":"Li","enname":"si"},
        {"wid":"003", "chname":"王五","surname":"Wang","enname":"wu"}
    ];

    table.render({
        elem: '#wkquery'
        ,data:RTData
        ,toolbar: '#toolbar'
        ,title: '用户数据表'
        ,cols: [[
            {field:'wid', title:'员工编号', width:"20%", edit: 'text'}
            ,{field:'chname', title:'中文姓名', width:"20%"}
            ,{field:'surname', title:'英文姓', width:"20%"}
            ,{field:'enname', title:'英文名', width:"20%"}
            ,{field: 'right', title:'操作', toolbar: '#operate', width:"20%"}
        ]]
        ,page: true
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
        };
    });

    //监听行工具事件
    table.on('tool(wkquery)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('是否确认删除员工'+ data.chname, function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            window.parent.location.href = "../view/wkupdate.html" + "?wid=" + data.wid;
        } else if (obj.event === 'detail'){
            window.parent.location.href = "../view/wkdetail.html" + "?wid=" + data.wid;
        }
    });
});
