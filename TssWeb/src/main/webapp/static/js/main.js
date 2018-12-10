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

    var page = document.getElementById("page");
    element.on('nav(main)', function (data) {
        if (data.context.id == "1"){
            page.innerHTML="";
            var tbEmn = document.createElement("table");
            tbEmn.className = "layui-hide";
            tbEmn.id = "RealTime";
            page.appendChild(tbEmn);

            var RTData = [
                {"username":"张三", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"李四", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"王五", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"}
                ];

            table.render({
                elem: '#RealTime',
                data:RTData,
                cols: [[
                    {field:'id', width:'10%', title: '序号', type:'numbers'}
                    ,{field:'username', width:'15%', title: '姓名'}
                    ,{field:'sex', width:'15%', title: '进入时间'}
                    ,{field:'city', width:'20%', title: '绿卡到期日期'}
                    ,{field:'experience', width:'20%', title: '密卡到期日期'}
                    ,{field:'score', width:'20%', title: 'CP到期日期'}
                ]]
                ,page: true
            });

        }else if (data.context.id == "2"){
            page.innerHTML="";
            var html = '<script type="text/html" id="toolbarDemo">\n' +
                '  <div class="layui-btn-container">\n' +
                '    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>\n' +
                '  </div>\n' +
                '</script>';
            page.innerHTML = html;
            var tbEmn = document.createElement("table");
            tbEmn.className = "layui-hide";
            tbEmn.id = "RealTime";
            page.appendChild(tbEmn);

            var RTData = [
                {"username":"张三", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"李四", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"王五", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"}
            ];

            table.render({
                elem: '#RealTime',
                data:RTData,
                toolbar: '#toolbarDemo',
                cols: [[
                    {field:'id', width:'10%', title: '序号', type:'numbers'}
                    ,{field:'username', width:'15%', title: '姓名'}
                    ,{field:'sex', width:'15%', title: '进入时间'}
                    ,{field:'city', width:'20%', title: '绿卡到期日期'}
                    ,{field:'experience', width:'20%', title: '密卡到期日期'}
                    ,{field:'score', width:'20%', title: 'CP到期日期'}
                ]]
                ,page: true
            });
        }else if (data.context.id == "3"){
            $("page").load("updateworker.html");
        }else if (data.context.id == "4"){
            page.innerHTML="";
            var html = '<script type="text/html" id="toolbarDemo">\n' +
                '  <div class="layui-btn-container">\n' +
                '    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>\n' +
                '  </div>\n' +
                '</script>';
            page.innerHTML = html;
            var tbEmn = document.createElement("table");
            tbEmn.className = "layui-hide";
            tbEmn.id = "RealTime";
            page.appendChild(tbEmn);

            var RTData = [
                {"username":"张三", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"李四", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
                {"username":"王五", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"}
            ];

            table.render({
                elem: '#RealTime',
                data:RTData,
                toolbar: '#toolbarDemo',
                cols: [[
                    {field:'id', width:'10%', title: '序号', type:'numbers'}
                    ,{field:'username', width:'15%', title: '姓名'}
                    ,{field:'sex', width:'15%', title: '进入时间'}
                    ,{field:'city', width:'20%', title: '绿卡到期日期'}
                    ,{field:'experience', width:'20%', title: '密卡到期日期'}
                    ,{field:'score', width:'20%', title: 'CP到期日期'}
                ]]
                ,page: true
            });
        }else if (data.context.id == "5"){
            document.getElementById("page").innerHTML="5";
        }
    });

    // laypage.render({
    //     elem: 'demo7'
    //     ,count: 1000
    //     ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
    //     ,jump: function(obj){
    //         console.log(obj)
    //     }
    // });
    //
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