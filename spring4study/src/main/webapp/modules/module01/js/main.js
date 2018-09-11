
$(document).ready(function() {

  $("#doLoginBtn").click(function(event){
    // event.preventDefault();
    // openModel();
    // modal.find('.modal-body input').val("登录中。。。");
	  ajaxLogin();
  })
  
//  $("#testAccessBtn").click(function(event){
//	  testAccessRefuse();
//  })
//  
  $("#logoutBtn").click(function(event){
	  ajaxLogout();
  })
  

  
})

// DO GET X-CSRF-TOKEN
function ajaxGetCsrfToken(){
	var url = '/spring4study/modules/user/getCsrf.jsp';
	var token = null;
	$.ajax({
  	   type: "GET",
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   // alert(data);
    	   token = data;
       },
       error : function(err) {
    	   // alert(err);
       }
    });
	return token;
}
  
// 'application/json;charset=utf-8' application/json
function ajaxLogin(){
  var data = ajaxGetCsrfToken();
  if(null == data){
	  alert("Get CsrfToken Error!");
	  return;
  }
  var queryParam = {};
  // alert($("#username").val());
  queryParam['username'] = $("#inputUsername").val();
  queryParam['password'] = $("#inputPassword").val();	
  //queryParam['targetUrl'] = "/html/modules/module02/loginSuccess.json";
  //'Accept-Language': 'utf-8, iso-8859-1;q=0.5, *;q=0.1'
  // 'Accept-Charset': 'utf-8, iso-8859-1;q=0.5'
  var url = '/spring4study/modules/user/login';
  $.ajax({
 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
  	headers    : {
  	             'X-CSRF-TOKEN': data
  	 },
  	dataType   : 'json',
  	type       : "POST",
  	timeout    : 20000,
  	data       : queryParam,
    url        : url,
    success    : function(result){
        // fill data to Modal Body
        // fillData(data);
        // alert(data);
    	if(result.success == false){
    		alert(result.message);
    	}else if (result.success == true){
        	//$("#showLoginBtn").text("注销");
            $("#showUserName").text("欢迎" + result.data.nickName);
            //$("#showLoginBtn").css("nav-link disabled");
            $('#loginModal').modal('hide');
            //$("#showLoginBtn").click(function(event){
            //	ajaxLogout();
            //})
    	}

     },
     error     : function(e) {
          	// fillData(null);
       }
   }); 
}
  
  
function ajaxLogout(){
	  var data = ajaxGetCsrfToken();
	  if(null == data){
		  alert("Get CsrfToken Error!");
		  return;
	  }
	  var queryParam = {};
	  var url = '/spring4study//modules/user/logout';
	  $.ajax({
	 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	  	headers    : {
	  	             'X-CSRF-TOKEN': data,
	  	 },
	  	dataType   : 'json',
	  	type       : "POST",
	  	timeout    : 20000,
	  	data       : queryParam,
	    url        : url,
	    success    : function(data){
	        // fill data to Modal Body
	        // fillData(data);
	        // alert(data);
	    	if(data.success == false){
	    		alert(data.message);
	    	}else if (data.success == true){
	    		$("#currentUser").text("您未登录");
	            alert(data.message);
	    	}

	     },
	     error     : function(e) {
	          	// fillData(null);
	       }
	   }); 
	}  
  
  
  
  
  
  
// //////////////////////////////////////////////////////////////////////////

function testAccessRefuse(){
  	var data = ajaxGetCsrfToken();
  	if(null == data){
		alert("Get CsrfToken Error!");
		return;
  	}
  	var queryParam = {};
  	// alert($("#username").val());
    // "/modules/module02/ActionTwo/aabbcc.do?${_csrf.parameterName}=${_csrf.token}";
  	var url = '/spring4study/modules/module02/ActionTwo/aabbcc.do';
  	$.ajax({
 		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
  		headers    : {
  	              	'X-CSRF-TOKEN': data,
  	    },
  	    dataType   : 'json',
  	    type       : "POST",
  	    timeout    : 20000,
  	    data       : queryParam,
        url        : url,
        success    : function(data){
        	if(data.success == false)
        		alert(data.message);
        },
        error     : function(err) {
        	alert(err);
        	if(err.success == false)
        		alert(err.message);
        }
    }); 
}
