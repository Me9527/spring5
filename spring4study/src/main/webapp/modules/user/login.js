$( document ).ready(function() {
    
	//var url = window.location;
	$("#loginbtn").click(function(event){
        event.preventDefault();
        // Open Bootstrap Modal
        openModel();
        // get data from Server
        ajaxGet();
	})
	
    // Open Bootstrap Modal
    function openModel(){
    	$("#modalId").modal();
    }
    
    // DO GET X-CSRF-TOKEN
    function ajaxGet(){
    	var url = '/spring4study/modules/user/getCsrf.jsp';
    	$.ajax({
    	    type: "GET",
    	    timeout : 20000,
            url : url,
            success: function(data){
            	//alert(data);
            	ajaxPost(data);
            },
            error : function(e) {
            	alert(e);
            }
        }); 
    }
    
    function ajaxPost(data){
    	var queryParam = {};
    	//alert($("#username").val());
    	queryParam['username'] = $("#username").val();
    	queryParam['password'] = $("#password").val();	
    	var url = '/spring4study/modules/user/login';
    	$.ajax({
    		headers : {
    	              'X-CSRF-TOKEN': data
    	    },
    	    type: "POST",
    	    timeout : 20000,
    	    data:queryParam,
            url : url,
            success: function(data){
            	// fill data to Modal Body
                fillData(data);
            },
            error : function(e) {
            	fillData(null);
            }
        }); 
    }
    
    function fillData(data){
    	if(data!=null){
            $(".modal-body #greetingId").text(data);
    	}else{
            $(".modal-body #greetingId").text("Can Not Get Data from Server!");
    	}
    }
})