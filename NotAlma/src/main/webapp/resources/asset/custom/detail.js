$(document).ready(function(){
	getNote();

});


function getNote(){
		$("#note_title").attr("disabled",true);
		$("#note_detail").attr("disabled",true);
		$("#updateBtn").html("Guncelle");
		
	$.ajax({
		type:"POST",
		url:'./../getNote',
		contentType:'text/plain',
		data:$("#id").val(),
		success:function(data){
			$("#note_title").val(data.title);
			$("#note_detail").html(data.content); //.html yerine .val'da kullanilabiliyor sorun olmuyor.
			 
		},
		error:function(data){
			alert(data);
		}
	});

}

var updatem=false;
function update(){
	if(!updatem){
		$("#note_title").attr("disabled",false);
		$("#note_detail").attr("disabled",false);
	
		$("#updateBtn").html("Kaydet");
		updatem=true;
	}
	else{
		updateNote();
		updatem=false;
	}
}

function deleteNote(){
	var param={
		id:$("#id").val()
	}
	
	var ser_data=JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:'./../deleteNote',
		data:ser_data,
		success:function(data){
			alert(data);
			window.history.back(); //bir onceki sayfaya gitmek icin bir jquery kodu.
		},error:function(data){
			alert(data);
		}
	});
}

function updateNote(){
	var param={
		id:$("#id").val(),
		title:$("#note_title").val(),
		content:$("#note_detail").val()
	}
	
	var ser_data=JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:'./../updateNote',
		data:ser_data,
		success:function(data){
			alert(data);
			getNote();
		},error:function(data){
			alert(data);
		}
	});
}



