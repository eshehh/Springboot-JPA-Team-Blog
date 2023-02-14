let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ // function(){}, ()=>{}를 사용한 이유는 this를 바인딩하기 위해서!!
			this.save();
		});
		$("#btn-delete").on("click",()=>{ // function(){}, ()=>{}를 사용한 이유는 this를 바인딩하기 위해서!!
			this.deleteBoard();
		});
		$("#btn-update").on("click",()=>{ // function(){}, ()=>{}를 사용한 이유는 this를 바인딩하기 위해서!!
			this.update();
		});
		$("#btn-reply-save").on("click",()=>{ // function(){}, ()=>{}를 사용한 이유는 this를 바인딩하기 위해서!!
			if($("#reply-content").val()==""){
			alert("내용을 입력해주세요");
			$("#reply-content").focus();
			return false;
			}else{this.replySave();}
			
			
		});
		
	},

	save:function(){
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			
		};
		
		 
		$.ajax({
			type: "POST",
			url: "/api/board",
			data:JSON.stringify(data),  //http body 데이터
			contentType:"application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	
	deleteBoard:function deleteBoard(seq){
			Swal.fire({
			  title: '글을 삭제하시겠습니까???',
			  text: "삭제하시면 다시 복구시킬 수 없습니다.",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '삭제',
			  cancelButtonText: '취소'
			}).then((result) => {
			  if (result.value) {
	              //"삭제" 버튼을 눌렀을 때 작업할 내용을 이곳에 넣어주면 된다. 
					let id = $("#id").text();
					
					$.ajax({
						type: "DELETE",
						url: "/api/board/"+id,
						dataType: "json" 
					}).done(function(resp){
						alert("삭제가 완료되었습니다.");
						location.href = "/";
					}).fail(function(error){
						alert(JSON.stringify(error));
					}); 
	
			  }
			})
	},
	
	update:function(){
		let id =$("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			
		};
		
		 
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data:JSON.stringify(data),  //http body 데이터
			contentType:"application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	replySave:function(){
		//alert('user의 save함수 호출됨');
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()		
		};
		
		 
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data:JSON.stringify(data),  //http body 데이터
			contentType:"application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	replyDelete: function(boardId, replyId){
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" 
		}).done(function(resp){
			alert("댓글삭제 성공");
			location.href = `/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	}



index.init();