<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/register/">
    <title>门诊查询</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
	 
		
    </script>
</head>
<body>

<form action="${path}register" method="post" class="definewidth m20">
<input name="method" value="findRegisterByPage" type="hidden"/>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" id="rid" name="rid" value="${rid}"/></td>
		
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" id="name" name="name" value="${name}"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td>
        	<select name="department" id="department">
	        	<option value="0" >==请选择==</option>
	        	<option value="1" >急诊科</option>
	        	<option value="2" >儿科</option>
	        	<option value="3" >妇科</option>
	        	<option value="4" >皮肤科</option>
	        	<option value="5" >内分泌科</option>
	        	<option value="6" >牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
		  <td colspan="6">
		  <center>
            <input id="find" name="find" type="submit" class="btn btn-primary" value="查询"/>
			<input name="ret" id="ret" type="button" class="btn btn-primary" value="清空"/>
            </center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall"></th>
        <th>病例号</th>
        <th>病人姓名</th>
        <th>主治医生</th>
        <th>挂号时间</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${rlist}" var="rl">
    <tr>
    	<td><input type="checkbox"  name="chk"  value="${rl.rid }"></td>
        <td>${rl.rid }</td>
        <td>${rl.name }</td>
        <td>${rl.doctor.name }</td>
        <td>${rl.registerDate}</td>
        <td>
		        <c:if test="${rl.department==1 }">急诊科</c:if>
		        <c:if test="${rl.department==2 }">儿科</c:if>
		        <c:if test="${rl.department==3 }">妇科</c:if>
		        <c:if test="${rl.department==4 }">皮肤科</c:if>
		        <c:if test="${rl.department==5 }">内分泌科</c:if>
		        <c:if test="${rl.department==6 }">牙科</c:if>
        </td>
        <td>
		        <c:if test="${rl.status==1 }">挂号</c:if>
		        <c:if test="${rl.status==2 }">住院</c:if>
		        <c:if test="${rl.status==3 }">出院</c:if>
		        <c:if test="${rl.status==4 }">结算</c:if>
        </td>
      
        <td>
        <a href="${path }register?method=ShowfindByIdRegist&rid=${rl.rid}">详情</a>
         <c:if test="${rl.status==1}">
        		<a href="${path }register?method=showupdateRegist&rid=${rl.rid}">修改</a>
        </c:if>
        </td>
    </tr>
    </c:forEach>
    </thead>
    <tbody>
    	
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  		<div class="inline pull-right page">
	          <a href="${path}register?method=findRegisterByPage&currentPage=1" >首页</a> 
	          <a href="${path}register?method=findRegisterByPage&currentPage=${page.prePage}">上一页</a>     
	          <a href="${path}register?method=findRegisterByPage&currentPage=${page.nextPage}" >下一页</a> 
	          <a href="${path}register?method=findRegisterByPage&currentPage=${page.totalPages}" >尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${page.totalCount} </span>条记录
			     <span class='current'>${page.totalPages} </span>页
		</div>
		<div>
		   <button type="button" class="btn btn-success" id="newNav">门诊挂号</button>&nbsp;&nbsp;&nbsp;
		   <button type="button" class="btn btn-success" id="delRegister">批量删除</button>
		</div>
	</th></tr>
  </table>
  <script type="text/javascript">
  
  $("#ret").click(function(){
	 	 $("#rid").val("");
		 $("#name").val("");
		 $("#department").val(0);
  });
  $("#newNav").click(function(){
	  window.location="${path}register/add.jsp";
  });
  //全选
  $("#checkall").click(function(){
	  alert(1)
	  $('[name="chk"]').prop("checked",$(this).prop("checked"));
  })
  //删除
  $("#delRegister").click(function(){
	  //alert(1)
	  //保存所有被选中的编号
	  var dids="";
	  //获取所有被选中的复选框
	  var chks=$('[name="chk"]:checked');
	  //循环变量
	  for (var i = 0; i < chks.length; i++) {
		  dids+="'"+chks[i].value+"',";
	}
	  if(dids==""){
		  alert("请选择你要删除的项!");
		  return
	  }
	  //将拼接的字符串截取逗号
	  dids=dids.substring(0,dids.length-1);
	  //提示
	  if(confirm("确认要 删除吗？")){
		  alert(dids)
		  //提示框跳转到deleRegisterById()
		  window.location="${path}register?method=deleRegisterById&dids="+dids;
	  }
	  
  })
  </script>
</body>
</html>
