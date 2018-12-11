/**
 * Created by xiaodj on 2018/12/11.
 */
layui.use('table', function(){
    var table = layui.table;

       var RTData = [
           {"username":"张三", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"},
           {"username":"李四", "sex":"08:02:11","city":"2019-09-11","experience":"2018-12-11", "score":"2018-12-19"},
           {"username":"王五", "sex":"08:02:11","city":"2019-09-11","experience":"2019-01-10", "score":"2018-12-19"}
       ];

    table.render({
        elem: '#realtime',
        data:RTData,
        cols: [[
            {field:'id', width:'10%', title: '序号', type:'numbers'}
            ,{field:'username', width:'15%', title: '姓名'}
            ,{field:'sex', width:'15%', title: '进入时间'}
            ,{field:'city', width:'20%', templet: '#sexTpl', title: '绿卡到期日期'}
            ,{field:'experience', width:'20%', templet: '#sexTpl1', title: '密卡到期日期'}
            ,{field:'score', width:'20%', templet: '#sexTpl2', title: 'CP到期日期'}
        ]]
        ,page: true
    });

});

function DateDiff(sDate1, sDate2) {
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("-")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
    aDate  =  sDate2.split("-")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}