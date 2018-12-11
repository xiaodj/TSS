/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use(['form','laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#LID1'
    });
    laydate.render({
        elem: '#LID2'
    });
    laydate.render({
        elem: '#LID3'
    });
});