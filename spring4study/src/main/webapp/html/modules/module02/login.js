
$(document).ready(function() {
  $('#loginModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('whatever') // Extract info from data-* attributes
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  //var modal = $(this)
	  //modal.find('.modal-title').text('New message to ' + recipient)
	  //modal.find('.modal-body input').val(recipient)
  })

  $("#doLoginBtn").click(function(event){
    // event.preventDefault();
    //openModel();
    //modal.find('.modal-body input').val("登录中。。。");
    ajaxGet();
  })
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
  	queryParam['username'] = $("#inputUsername").val();
  	queryParam['password'] = $("#inputPassword").val();	
  	var url = '/spring4study/modules/user/login';
  	$.ajax({
 		contentType: 'application/json;charset=utf-8',
  		headers : {
  	              'X-CSRF-TOKEN': data
  	    },
  	    type: "POST",
  	    timeout : 20000,
  	    data:queryParam,
          url : url,
          success: function(data){
          	// fill data to Modal Body
            //fillData(data);
        	$("#showLoginBtn").text("注销");
        	$("#logedUser").text("孙悟空");
        	
        	alert(data);
          },
          error : function(e) {
          	//fillData(null);
          }
      }); 
  }
  
})