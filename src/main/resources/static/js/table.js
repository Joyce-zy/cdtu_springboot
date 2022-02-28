layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function() {
    var laydate = layui.laydate, //日期
        laypage = layui.laypage, //分页
        layer = layui.layer, //弹窗
        table = layui.table, //表格
        upload = layui.upload, //上传
        element = layui.element, //元素操作
        slider = layui.slider; //滑块


    //执行一个 table 实例
    table.render({
        elem: '#demo',
        height: 600,
        url: 'http://localhost:8081/myblogssm/articles/getAll' //数据接口
        ,
        title: '文章表',
        page: true //开启分页
        ,
        toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,
        parseData:function(res){
            console.log(res)
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            }
        },
        cols: [
            [ //表头
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                field: 'id',
                title: 'ID',
                sort: true,
                align: 'center'
            }, {
                field: 'title',
                title: '发布者',
                align: 'center'
            }, {
                field: 'author',
                title: '描述',
                align: 'center'
            }, {
                field: 'sort',
                title: '视频内容',
                align: 'center',
            }, {
                field: 'time',
                title: '时长',
                templet:function(d){
                    return  showTime(d.time)

                },
                sort: true,
                align: 'center'
            }, {
                field: 'star',
                title: '封面图',
                align: 'center'
            }, {
                field: 'comment',
                title: '点赞次数',
                sort: true,
                align: 'center'
            },{
                field: 'visit',
                title: '创建时间',
                sort: true,
                align: 'center'
            },{
                title:'操作',
                align: 'center',
                width:150,
                toolbar: '#barDemo'
            }
            ]
        ]
    });
    //表格按钮
    table.on('tool(test)', function(obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if (layEvent === 'del') { //删除
            layer.confirm('真的删除该行数据么？', function(index) {
                $.ajax({
                    type: "post",
                    url: "http://localhost:8081/myblogssm/articles/del",
                    dataType: 'json',
                    data: {
                        "id":data.id
                    },
                    dataType: "json",
                    success: function(da) {
                        var message = da.message;
                        var code = da.status;
                        if (code == 0) {
                            obj.del();
                            layer.close(index);
                            layer.alert(message, {
                                icon: 1,
                                time: 2000
                            });
                            //重新刷新
                            // tableObj.reload({
                            // 	url: '/cd/workPlatform/tool/queryTools.afca'
                            // });
                        } else {
                            layer.alert(message, {
                                icon: 2,
                                title: '提示'
                            });
                            return;
                        }
                    },
                })
            });
        } else if(layEvent === 'edit'){
                layer.open({
                    type: 2,
                    // title: '编辑脚本定义',
                    title:data.title,
                    //遮罩层
                    shadeClose: true,
                    shade: 0.8,
                    area: ['80%', '80%'],
                    content: 'edit.html'
                });
            }
    })
    //






})