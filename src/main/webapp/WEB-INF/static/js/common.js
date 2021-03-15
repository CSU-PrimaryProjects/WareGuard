var table;
var layer;
layui.use(['table',"layer",'laydate'], function(){
    table = layui.table;
    layer = layui.layer;
    //执行一个laydate实例
    var laydate = layui.laydate;
    laydate.render({
        elem: '#datetime' //指定元素
    });
});