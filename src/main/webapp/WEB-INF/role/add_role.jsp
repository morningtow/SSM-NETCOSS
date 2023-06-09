<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>云思－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
        	var nameFlag;
        	function check_name() {
        		nameFlag = null;
        		
        		var name = $("#name").val();
        		var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
        		if(!reg.test(name)) {
        			$("#name_msg").text("不能为空，且为20长度的字母、数字和汉字的组合").addClass("error_msg");
        			nameFlag = false;
        			return;
        		}
        		
        		$.post(
        			"checkRoleName.do",
        			{"name":name},
        			function(data) {
        				if(data == "true") {
        					$("#name_msg").text("有效的名称.").removeClass("error_msg");
        					nameFlag = true;
        				} else {
        					$("#name_msg").text("该名称已存在.").addClass("error_msg");
        					nameFlag = false;
        				}
        			}
        		);
        		
        		/**
        		$.ajax({
        			type:"post",
        			url:"checkRoleName.do",
        			dataType:"json",
        			data:{"name":name},
        			async:false,
        			success:function(data) {
        				if(data) {
        					$("#name_msg").text("有效的名称.").removeClass("error_msg");
        					return true;
        				} else {
        					$("#name_msg").text("该名称已存在.").addClass("error_msg");
        					return false;
        				}
        			}
        		});
        		**/
        	}
        	function check_module() {
        		var moduleIds = $(":checkbox[name='moduleIds']:checked");
        		if(moduleIds.length == 0) {
        			$("#module_msg").text("请至少选择一个模块.").addClass("error_msg");
        			return false;
        		} else {
        			$("#module_msg").text("").removeClass("error_msg");
        			return true;
        		}
        	}
        	function save() {
        		check_name();
        		var timer = setInterval(function(){
        			if(nameFlag!=null) {
        				clearInterval(timer);
        				if(nameFlag ) {
		        			document.forms[0].submit();
		        		} else {
		        			alert("表单中存在不符合规则的数据，请检查.");
		        		}
        			}
        		},200);
        	}
        	
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
        	<jsp:include page="/WEB-INF/main/menu.jsp" />
            <!-- <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_on"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul> -->
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，角色名称重复！-->
            <form action="addRole.do" method="post" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" name="name" id="name" onblur="check_name();"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="name_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        江苏师范
<%--                        <ul>--%>
<%--                        	<c:forEach items="${modules }" var="m">--%>
<%--                            	<li><input type="checkbox" value="${m.module_id }" name="moduleIds" onclick="check_module();"/>${m.name }</li>--%>
<%--                            </c:forEach>--%>
<%--                        </ul>--%>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="module_msg"></div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="save();"/>
                    <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大云思IT培训集团公司 </span>
        </div>
    </body>
</html>