<%--
  Created by IntelliJ IDEA.
  User: Admin John
  Date: 2020/9/16
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>信息发送</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">单条信息发送</li>
        <li>上传文件发送</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <%--<div class="layuimini-container">--%>
            <%--    <div class="layuimini-main">--%>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>单条信息发送</legend>
            </fieldset>
            
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">信息标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="messageTitle" lay-verify="required" autocomplete="off"
                               placeholder="请输入标题" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">发送对象</label>
                    <div class="layui-inline">
                        <input type="text" name="toUser" lay-verify="required" lay-reqtext="信息" placeholder="工号"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">发送频道</label>
                    <div class="layui-input-block">
                        <select name="messageType" lay-filter="aihao">
                            <option value=""></option>
                            <option value="3506000001" selected="">通知</option>
                            <option value="3507000001">广东销售支持</option>
                            <option value="4402999911">东莞专区</option>
                            <option value="4402999903">e职场</option>
                            <option value="4400000002">云会办</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">信息形式</label>
                    <div class="layui-input-block">
                        <input type="radio" name="contentType" value="text" title="文本" checked="">
                        <input type="radio" name="contentType" value="news" title="新闻">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label class="layui-form-label">是否可复制</label>
                    <div class="layui-input-block">
                        <input type="radio" name="copy" value="1" title="是" checked="">
                        <input type="radio" name="copy" value="0" title="否">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">信息内容</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容" lay-verify="title" class="layui-textarea"
                                  name="messageContent"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="demo1">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-tab-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>上传excel文件发送</legend>
            </fieldset>
            <div class="layui-row">
                    <button type="button" class="layui-btn" id="test1">
                        <i class="layui-icon">&#xe67c;</i>上传Excel文件
                    </button>
                <a href="${pageContext.request.contextPath}/download">
                    <button type="button" class="layui-btn">模板下载</button>
                </a>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            </fieldset>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">解析结果</label>
                <div class="layui-input-block">
            <textarea id="feedback" placeholder="" lay-verify="title" class="layui-textarea" name="messageContent"
                      disabled="disabled"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn batch" id="pushBatchMessage" lay-filter="demo1">确认发送</button>
                    <button type="button" id="clearContent" class="layui-btn layui-btn-primary batch">清空</button>
                </div>
            </div>
            <!-- 示例-970 -->
            <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px"
                 data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
            <%--        </div>--%>
            <%--    </div>--%>
        </div>
    </div>
</div>
        <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
        <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
        <script>
            layui.use(['element','form', 'layedit', 'laydate', 'upload'], function () {
                var element = layui.element
                    , form = layui.form
                    , layer = layui.layer
                    , layedit = layui.layedit
                    , laydate = layui.laydate
                    , upload = layui.upload;
                    
                //, layupload = layui.upload;
                //更新全部组件
                form.render();
                //日期
                laydate.render({
                    elem: '#date'
                });
                laydate.render({
                    elem: '#date1'
                });
                element.render();
                var uploadInst = upload.render({
                    elem: '#test1' //绑定元素
                    , url: '${pageContext.request.contextPath}/upload' //上传接口
                    , accept: 'file'
                    , exts: 'xls||xlsx'
                    , field: 'excelFile'
                    , done: function (res) {
                        //上传完毕回调
                        layer.msg(res.msg)
                        if (res.code == "1") {
                            $('#feedback').text(res.data);
                        }
                    }
                    , error: function () {
                        //请求异常回调
                        layer.msg('解析失败');
                    }
                });


                //创建一个编辑器
                var editIndex = layedit.build('LAY_demo_editor');

                //自定义验证规则
                form.verify({
                    title: function (value) {
                        if (value.length == 0) {
                            return '信息内容为空';
                        }
                    }
                    , pass: [
                        /^[\S]{6,12}$/
                        , '密码必须6到12位，且不能出现空格'
                    ]
                    , content: function (value) {
                        layedit.sync(editIndex);
                    }
                });


                //弹窗提示
                //监听提交
                form.on('submit(demo1)', function (data) {
                    // var message = JSON.stringify(data.field);
                    layer.open({
                        title: '提示'
                        , content: '确认要发送吗'
                        , btn: ['确认', '取消']
                        , yes: function (index, layero) {
                            //按钮【按钮一】的回调
                            $.post('${pageContext.request.contextPath}/admin/pushOneMessage', data.field, function (result) {
                                if (result.success == 1) {
                                    //发送成功
                                    layer.config({content: '发送成功'});
                                    return false;
                                } else {
                                    layer.config({content: '发送失败'});
                                }

                            }, 'json')
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {
                            //按钮【按钮二】的回调

                        }
                        , cancel: function () {
                            //右上角关闭回调
                            //    return false 开启该代码可禁止点击该按钮关闭
                        }
                    });
                    return false;
                });
                $('.batch').on('click', function () {
                    var status = {status: 1}
                    if ($(this).attr('id') === 'clearContent') {
                        // 点击清空将status赋值0传到后台
                        status = {status: 0}
                        $('#feedback').text('');

                    }
                    $.post('${pageContext.request.contextPath}/admin/pushBatchMessage', status, function (res) {
                        //如果点击的不是清除按钮，提示信息， 成功提示发送信息，
                        if (res.clear === "0") {
                            layer.msg(res.msg);
                            $('#feedback').text('');
                        } else {
                            //清除textarea；什么都不做
                        }

                    }, 'json');
                })
            });
        
        
        </script>

</body>
</html>