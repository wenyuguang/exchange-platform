var avalon = require('avalon')
var jquery = require('jquery')
var dt = require( 'datatables.net' )
require('../../vendor/layer/layer')
require('../../vendor/layer/theme/default/layer.css')
require('../../vendor/layer/mobile/need/layer.css')
define([], function () {
    // 定义所有相关的vmodel
    var vm = avalon.define({
      $id: "search",
      $optTimepicker: {
        //rules: 'null,0D'
      }
    });
    return avalon.controller(function ($ctrl) {
       
      // 视图渲染后，意思是avalon.scan完成
      $ctrl.$onRendered = function () {
        $("#lbutton").click(function(){
            var lvmc = $("#lvmc").val();
            var lszyxkzh = $("#lszyxkzh").val();
            var lszyzgzh = $("#lszyzgzh").val();
            var sfzhm = $("#sfzhm").val();
            var xm = $("#xm").val();
            if(lvmc == '' && lszyxkzh=='' && lszyzgzh=='' && sfzhm=='' && xm==''){
                layer.alert("请至少填写一个查询条件");
                return 
            }
            var json = {lvmc:lvmc,lszyxkzh:lszyxkzh,lszyzgzh:lszyzgzh,sfzhm:sfzhm,xm:xm}
            var strxml = '<?xml version="1.0" encoding="utf-8"?><Condition></Condition>';
            var objxml;
            var xmlString;
            // for IE
            if (window.ActiveXObject) {
              alert("ie")
              objxml = new ActiveXObject("Microsoft.XMLDOM");
              objxml.async = "false";
              objxml.loadXML('<?xml version="1.0" encoding="utf-8"?><Condition></Condition>');
              for ( key in json) {
                if(json[key]!=''){
                  //创建节点
                  var newel=objxml.createElement(key);
                  //创建文本
                  var newtext=objxml.createTextNode(json[key]);
                  //将文本添加到节点
                  newel.appendChild(newtext);
                  //将节点添加到父节点
                  objxml.childNodes[1].appendChild(newel);
                }
             }
             //将xml对象转换为string
             xmlString="xml="+objxml.xml;
            }else {//其他浏览器
              var parser = new DOMParser();
              var xmlobject = parser.parseFromString(strxml, "text/xml");
              objxml = xmlobject;
              for ( key in json) {
                if(json[key]!=''){
                   $(objxml).find("Condition").append('<'+key+'>'+json[key]+'</'+key+'>')
                }
              }
              xmlString="xml="+(new XMLSerializer()).serializeToString(objxml);
            }
            console.log("xmlString"+xmlString);         
            //var xml = 'xml=<?xml version="1.0" encoding="UTF-8"?><Condition><xm>阴颖晖</xm></Condition>'
            xmlString=encodeURI(xmlString)
            $.ajax({
                type:"get",//Http请求方式                        
                url:"./api/getLsxx",
                data: xmlString, //发送给服务器端的数据  
                dataType:"text",//告诉JQuery返回的数据方式  
                success:function(data){//定义交互完成，并且服务器正确返回数据时调用的回调函数
                  console.log("data"+data) 
                  xml = $.parseXML(data);
                  console.log($(xml).find("xb").text())
                  var table = ' <div class="ptable">'+
                                '<table  class="table table-bordered">'+  
                                '<tr>'+  
                                    '<td colspan="5" bgcolor="#9FCDEA"><h3>律师信息</h3></td>'+  
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>姓 名：</td>'+ 
                                    '<td>'+$(xml).find("xm").text()+'</td>'+
                                    '<td>性 别：</td>'+  
                                    '<td>'+$(xml).find("xb").text()+'</td>'+                               
                                    '<td>地 址：</td>'+  
                                    '<td>'+$(xml).find("address").text()+'</td>'+ 
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>民 族：</td>'+ 
                                    '<td>'+$(xml).find("mz").text()+'</td>'+
                                    '<td>办公电话：</td>'+    
                                    '<td>'+$(xml).find("bgdh").text()+'</td>'+
                                    '<td>从业时间：</td>'+    
                                    '<td>'+$(xml).find("cysj").text().substring(0,11)+'</td>'+
                                '</tr>'+
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>传 真：</td>'+
                                    '<td>'+$(xml).find("cz").text()+'</td>'+
                                    '<td>电子邮箱：</td>'+ 
                                    '<td>'+$(xml).find("dzyx").text()+'</td>'+
                                    '<td>律所名称：</td>'+ 
                                    '<td>'+$(xml).find("lsmc").text()+'</td>'+
                                    
                                '</tr>'+      
                                '<tr>'+
                                    '<td>律所职务：</td>'+  
                                    '<td>'+$(xml).find("lszw").text()+'</td>'+ 
                                    '<td>执业许可证号：</td>'+  
                                    '<td>'+$(xml).find("lszyxkzh").text()+'</td>'+     
                                    '<td>职业职格证号：</td>'+  
                                    '<td>'+$(xml).find("lszyzgzh").text()+'</td>'+                                     
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>身份证号码：</td>'+
                                    '<td>'+$(xml).find("sfzhm").text()+'</td>'+
                                    '<td>邮 编：</td>'+
                                    '<td>'+$(xml).find("yb").text()+'</td>'+
                                    '<td>职格证取得日期：</td>'+
                                    '<td>'+$(xml).find("zgzqdrq").text()+'</td>'+
                                '</tr>'+  
                                '<tr>'+  
                                  '<td>执业证取得日期：</td>'+
                                  '<td>'+$(xml).find("zyzqdrq").text()+'</td>'+
                                  '<td>执业状态：</td>'+
                                  '<td>'+$(xml).find("zyzt").text()+'</td>'+
                                  '<td></td>'+
                                  '<td></td>'+
                                '</tr>'+       
                            '</table></div>' 
                $("#ltable").html(table); 
                },
                error:function(data){
                  console.log(data.responseText)
                  layer.alert("查询失败")
                }
            })
        });
        
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