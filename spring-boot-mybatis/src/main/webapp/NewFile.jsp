<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
$(document).ready(function(){
    jQuery.ajax({
            url : "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0f21f8f72a9dade9&redirect_uri=http%3a%2f%2flangwei.vipgz1.idcfengye.com%2fwechat%2fuser&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect",
            dataType : "json",
            async : false,
            data :{},
            error : function(data) {
                 console.log(data);
            },
            success : function(data) {
                console.log(data);
            }
        });
});

</script>
<body>

</body>
</html>