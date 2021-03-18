var request = new XMLHttpRequest();
var id = document.getElementById("pagenum").value;
function searchFunction(){
	request.open("GET","/board/CmtController?id="+id,true);
	request.onreadystatechange = searchProcess;
	request.send(null);
}
function searchProcess(){
	var table = document.getElementById("commentTable");
	table.innerHTML = "";
	if(request.readyState == 4 && request.status == 200){
		console.log(request);
		var object = eval('('+request.responseText+')'); //eval이 머?
		var result = object.result;
		for(var i = 0 ; i < result.length; i++){
			var row = table.insertRow(0); //<<행을 하나만드느것
			for(var j = 0; j<result[i].length;j++){
				var cell = row.insertCell(j); //하나의 셀을 만들어서 추가하는것 (j번째)
				cell.innerHTML = result[i][j].value; //i에 j번째있는 인덱스를 셀에 넣어쥬는것
			}
		}
	}
}
window.onload = function(){
	searchFunction();
}

function writerFunction(){
	var content = document.getElementById("content").value;
	var writer = document.getElementById("writer").value;
	event.preventDefault();
		if (this.writer.value == "") {
			alert("작성자를 입력해주세요");
			this.writer.focus();
			return;
		}
		else if (this.content.value == "") {
			alert("내용를 입력해주세요");
			this.content.focus();
			return;
		}else{
			console.log("하하");
			request.open("POST","/board/CmtController?id="+id+"&content="+content+"&writer="+writer,true);
			request.onreadystatechange = searchProcess;
				request.send(null);
		}
}
function searchProcess(){

	var table = document.getElementById("commentTable");
	table.innerHTML = "";
	if(request.readyState == 4 && request.status == 200){
		console.log(request);
		var object = eval('('+request.responseText+')'); //eval이 머?
		var result = object.result;
		for(var i = 0 ; i < result.length; i++){
			var row = table.insertRow(0); //<<행을 하나만드느것
			for(var j = 0; j<result[i].length;j++){
				var cell = row.insertCell(j); //하나의 셀을 만들어서 추가하는것 (j번째)
				cell.innerHTML = result[i][j].value; //i에 j번째있는 인덱스를 셀에 넣어쥬는것
			}
		}
	}
}