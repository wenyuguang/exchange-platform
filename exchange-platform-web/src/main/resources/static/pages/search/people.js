define([], function () {
        // 定义所有相关的vmodel
        var vm = avalon.define({
        $id: "people",
        $optTimepicker: {
            //rules: 'null,0D'
        }
        });
        return avalon.controller(function ($ctrl) {
        
        // 视图渲染后，意思是avalon.scan完成
        $ctrl.$onRendered = function () {
                //获取ip
                if(window.ActiveXObject){
                    var hsip = '';
                    if(GetXpLocalIPAddr()!=null || GetXpLocalIPAddr()!=''){
                        hsip = GetXpLocalIPAddr();
                    }else if(GetWin7LocalIPAddr()!=null || GetWin7LocalIPAddr()!='') {
                        hsip =  GetWin7LocalIPAddr();
                    }
                    $("#hsip").html(hsip)          
                }else{
                    findIP(addIP);
                }
                //提交查询    
                $("#pbutton").click(function(){
                    var ip = $('#hsip').val();
                    //var ip = document.getElementById("hsip").innerText; 
                    var xm = $("#xm").val();
                    var gmsfhm = $("#gmsfhm").val();
                    if(xm == '' || gmsfhm==''){
                            layer.alert("都不能为空");
                            return 
                    }
                    json = {XM:xm,GMSFHM:gmsfhm};
                    var strxml = '<?xml version="1.0" encoding="utf-8"?><ROWS><INFO><SBM>'+encodeURI("XXX法院")+'</SBM></INFO><ROW><GMSFHM>'+encodeURI("公民身份号码")+'</GMSFHM><XM>'+encodeURI("姓名")+'</XM></ROW><ROW FSD="110105" YWLX="'+encodeURI("测试")+'"></ROW></ROWS>';
                    var objxml;
                    var xmlString;
                    var fydm = "6&";
                    var xtmc = "sjxx&";
                    var user = "zhangsan&";
                    //var ip = "150.0.32.31&";
                    // for IE
                    if (window.ActiveXObject) {
                    objxml = new ActiveXObject("Microsoft.XMLDOM");
                    objxml.async = "false";
                    objxml.loadXML(strxml);
                    for ( key in json) {
                        if(json[key]!=''){
                        //创建节点
                        var newel=objxml.createElement(key);
                        //创建文本
                        var newtext=objxml.createTextNode(json[key]);
                        //将文本添加到节点
                        newel.appendChild(newtext);
                        //将节点添加到父节点
                        objxml.childNodes[1].childNodes[2].appendChild(newel);
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
                        //$(objxml).find("ROW").append('<'+key+'>'+json[key]+'</'+key+'>')
                            $(objxml).find("ROW").each(function(){
                                var ROW = $(this);
                                if(ROW.attr("FSD") !='' && ROW.attr("FSD") != undefined){
                                    $(this).append('<'+key+'>'+json[key]+'</'+key+'>')
                                }  
                            })
                        }
                    }
                    xmlString="xml="+(new XMLSerializer()).serializeToString(objxml);
                    }
                    console.log(xmlString); 
                    xmlString = 'xtmc='+encodeURI("XXX系统")+'&ip='+ip+'&user=zhangsan&fydm=1&ah='+encodeURI("XXX系统")+'&'+xmlString
                    console.log(xmlString); 
                    xmlString=encodeURI(xmlString)
                    $.ajax({
                        type:"get",//Http请求方式                        
                        //url:"./api/getLsxx", //服务器端url地址  
                        url:"/api/getRyxx",
                        data: xmlString, //发送给服务器端的数据  
                        //dataType:"xml",//告诉JQuery返回的数据方式  
                        //dataType:"text",
                        success:function(data){//定义交互完成，并且服务器正确返回数据时调用的回调函数 
                            console.log(data)
                            var data = data ;
                            console.log(data.length);
                            var xmlstr = data.substring(1,data.length-1);
                            console.log(xmlstr);
                            var xml = $.parseXML(xmlstr);             
                            // var xml =  data;
                            var table = ' <div class="ptable">'+
                                '<table  class="table table-bordered">'+  
                                '<tr>'+  
                                    '<td colspan="5" bgcolor="#9FCDEA"><h3>公民信息</h3></td>'+  
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>姓 名：</td>'+ 
                                    '<td>'+$(xml).find("ITEM").find("xm").text()+'</td>'+
                                    '<td>身份号码：</td>'+  
                                    '<td>'+$(xml).find("ITEM").find("gmsfhm").text()+'</td>'+                               
                                    '<td style="height: 100px;width: 100px;" rowspan="5" colspan="5" align="center" valign="middle">'+"<img  src=data:image/jpg;base64,"+$(xml).find("ITEM").find("xp").text()+">"+'</td>'+  
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>民 族：</td>'+ 
                                    '<td>'+$(xml).find("ITEM").find("mz").text()+'</td>'+
                                    '<td>出生日期：</td>'+    
                                    '<td>'+$(xml).find("ITEM").find("csrq").text()+'</td>'+
                                '</tr>'+
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>性 别：</td>'+
                                    '<td>'+$(xml).find("ITEM").find("xb").text()+'</td>'+
                                    '<td>服务处所：</td>'+ 
                                    '<td>'+$(xml).find("ITEM").find("fwcs").text()+'</td>'+
                                    
                                '</tr>'+      
                                '<tr>'+
                                    '<td>地 址：</td>'+  
                                    '<td colspan="3">'+$(xml).find("ITEM").find("zz").text()+'</td>'+                                 
                                '</tr>'+  
                                '<tr>'+  
                                    '<td>籍贯省市县：</td>'+
                                    '<td  colspan="3">'+$(xml).find("ITEM").find("jgssx").text()+'</td>'+
                                '</tr>'+     
                            '</table></div>' 
                             $("#ptable").html(table); 
                        },
                        error:function(data){
                            alert("2222")
                          console.log(data)
                          layer.alert("查询失败")
                        }
                    })
            })
            //ie win7下
            function GetWin7LocalIPAddr(){ 
                var ip;
                var locator = new ActiveXObject("WbemScripting.SWbemLocator");
                var service = locator.ConnectServer(".");
                console.log("service")
                var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");
                console.log("properties");
                var e = new Enumerator(properties); {
                    var p = e.item();
                    ip = p.IPAddress;
                }  
                return ip   
            }
            //ie xp下
            function GetXpLocalIPAddr(){ 
                var oSetting = null; 
                var ip = null; 
                try{ 
                oSetting = new ActiveXObject("rcbdyctl.Setting"); 
                ip = oSetting.GetIPAddress; 
                alert(ip); 
                if (ip.length == 0){ 
                return "没有连接到Internet"; 
                } 
                oSetting = null; 
                }catch(e){ 
                return ip; 
                } 
                return ip; 
            } 
            function findIP(onNewIP) { //  onNewIp - your listener function for new IPs
                var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection; //compatibility for firefox and chrome
                var pc = new myPeerConnection({iceServers: []}), // 空的ICE服务器（STUN或者TURN）
                  noop = function() {},
                  localIPs = {}, //记录有没有被调用到onNewIP这个listener上
                  ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
                  key;
                function ipIterate(ip) {
                  if (!localIPs[ip]){
                    onNewIP(ip);
                  } 
                  localIPs[ip] = true;
                }
                pc.createDataChannel(""); //create a bogus data channel
                pc.createOffer().then(function(sdp) {
                  sdp.sdp.split('\n').forEach(function(line) {
                    if (line.indexOf('candidate') < 0) return;
                    line.match(ipRegex).forEach(ipIterate);
                  });
                  pc.setLocalDescription(sdp, noop, noop);
                }); // create offer and set local description
                pc.onicecandidate = function(ice) { //listen for candidate events
                  if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
                  ice.candidate.candidate.match(ipRegex).forEach(ipIterate);
                };
            }
            function addIP(ip) {
                console.log('got ip: ', ip);
                $("#hsip").html(ip)  
            }
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