/**
 * Created by xiaodj on 2018/12/5.
 */
layui.define(['layer','form', 'element', 'table', 'laypage', 'layer'],function (exports) {
    var layer = layui.layer;
    var form = layui.form;
    var element = layui.element;
    var table = layui.table;
    var laypage = layui.laypage;
    var layer = layui.layer;
    var $ = layui.$;

    element.on('nav(main)', function (data) {
        if (data.context.id == "1"){
            document.getElementById("page").innerHTML="1";
        }else if (data.context.id == "2"){
            document.getElementById("page").innerHTML="2";
        }else if (data.context.id == "3"){
            document.getElementById("page").innerHTML="3";
        }else if (data.context.id == "4"){
            document.getElementById("page").innerHTML="4";
        }else if (data.context.id == "5"){
            document.getElementById("page").innerHTML="5";
        }
    });

    table.render({
        elem: '#test'
        //,url:'/demo/table/user/'
        ,cols: [[
            {field:'id', width:80, title: 'ID', sort: true}
            ,{field:'username', width:80, title: '用户名'}
            ,{field:'sex', width:80, title: '性别', sort: true}
            ,{field:'city', width:80, title: '城市'}
            ,{field:'sign', title: '签名', minWidth: 150}
            ,{field:'experience', width:80, title: '积分', sort: true}
            ,{field:'score', width:80, title: '评分', sort: true}
            ,{field:'classify', width:80, title: '职业'}
            ,{field:'wealth', width:135, title: '财富', sort: true}
        ]]
        ,page: true
    });

    laypage.render({
        elem: 'demo7'
        ,count: 1000
        ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
        ,jump: function(obj){
            console.log(obj)
        }
    });


    exports('main', {});
});