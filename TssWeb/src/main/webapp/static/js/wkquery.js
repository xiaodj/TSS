/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['table'], function () {
    var table = layui.table;

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
    table.on('tool(test)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });
});
