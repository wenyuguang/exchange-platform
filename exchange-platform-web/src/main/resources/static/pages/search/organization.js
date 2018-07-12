define([], function () {
        // 定义所有相关的vmodel
        var vm = avalon.define({
        $id: "organization",
        $optTimepicker: {
            //rules: 'null,0D'
        }
        });
        return avalon.controller(function ($ctrl) {
        
        // 视图渲染后，意思是avalon.scan完成
        $ctrl.$onRendered = function () {
                $("#zbutton").click(function(){
                    var jgmc = $("#jgmc").val();
                    if(jgmc==''){
                        layer.alert("请至少填写一个查询条件");
                        return
                    }
                    json = {jgmc:jgmc};
                    xmlString =encodeURI('systemMark=true&ticket=9b5bccf4930f963d8a201fa958f78d76&startPage=1&jgmc='+jgmc)                   
                    $.ajax({
                        type:"get",//Http请求方式                        
                        //url:"./api/getLsxx", //服务器端url地址  
                        url:"./api/getJgxx",
                        data: xmlString, //发送给服务器端的数据  
                        //dataType:"xml",//告诉JQuery返回的数据方式  
                        dataType:"xml",
                        success:function(data){//定义交互完成，并且服务器正确返回数据时调用的回调函数  
                            var xml = data;
                            var table = ' <div class="otable">'+
                                        '<table  class="table table-bordered">'+  
                                        '<tr>'+  
                                            '<td colspan="2" bgcolor="#9FCDEA"><h3>机构信息</h3></td>'+  
                                        '</tr>'+  
                                        '<tr>'+  
                                            ' <td>统一社会信用代码：</td>'+ 
                                            '<td>'+$(xml).find("tydm").text()+'</td>'+  
                                        '</tr>'+  
                                        '<tr>'+  
                                            ' <td>机构代码：</td>'+ 
                                            '<td>'+$(xml).find("jgdm").text()+'</td>'+
                                        '</tr>'+  
                                        '<tr>'+  
                                            '<td>企业名称：</td>'+
                                            '<td>'+$(xml).find("jgmc").text()+'</td>'+
                                        '</tr>'+      
                                    '</table></div>' 
                            $("#oTable").html(table); 
                        },
                        error: function(data){
                            layer.alert("查询失败")
                        }
                })
            }) 
        };
        // 进入视图
        $ctrl.$onEnter = function (param, rs, rj) {

        };
        // 对应的视图销毁前
        $ctrl.$onBeforeUnload = function () {

        };
        $ctrl.$vmodels = [vm];
        })
        });