/**
 * Created by xiaodj on 2018/12/5.
 */

layui.define(['layer','element','form', 'laydate'],function (exports) {
    var layer = layui.layer;
    var form = layui.form;
    var element = layer.element;
    var laydate = layui.laydate;
    var $ = layui.$;

    laydate.render({
        elem: '#LID1'
    });
    laydate.render({
        elem: '#LID2'
    });
    laydate.render({
        elem: '#LID3'
    });

    exports('user', {});
});