function addUser(){
	var param={
		username:$("#username").val(),
		name:$("#name").val(),
		surname:$("#surname").val(),
		email:$("#email").val(),
		password:$("#pass").val(),
		pass2:$("#pass2").val()
	}
	
	var ser_data=JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:'addUser',
		data:ser_data,
		success:function(data){
			if(data=='1'){
				alert("Sifreler Uyusmuyor Ikisinide Ayni Girmeniz Lazim.");
			}
			else if(data=='OK'){
				alert("Uyelik Islemleri Basarili");
				$(location).attr('href', 'login');
			}
			else if(data=='ERROR'){
				alert("Bir Hata Olustu");
			}
		},error:function(data){
			alert(data);
		}
	});
}