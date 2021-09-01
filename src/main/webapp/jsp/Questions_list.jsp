<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="data.CountOfQuestions"%>
<%@page import="data.AllQuestions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="../css/styles1.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/12ffd7b83e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>Question list</title>
	<script>
		$(document).ready(function(){
	//Limiting questions to 25 
			if ($("#nrofque").val() >= 25) {
				document.getElementById("addbtn123").disabled= true;
			}	
		});
	// Function for inputs and buttonns to show/hide in questionslist
		function editquestion(caller) {
			var callid = caller.id;
			const slicer = callid.split('-');
			console.log(slicer);
			document.getElementById("qfeild-"+slicer[1]).hidden=true;
			document.getElementById("input-"+slicer[1]).hidden=false;
			document.getElementById("delete-"+slicer[1]).hidden=false;
			document.getElementById("edit-"+slicer[1]).hidden=true;
			document.getElementById("edit1-"+slicer[1]).hidden=false;
			
		};
		
	</script>
</head>
<body>
<!-- Navigation bar -->
<%@ include file="../jsp/components/navigationBar.jsp"%>
	<div class="container-fluid">
		<div class="row mainRow">
			<div class="col-md-12 mainColumn">
				<div class="row">
					<div class="col-md-12">
						<article class="questionArticle">
							<p class="topLine">QUESTIONS</p>
						</article>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 mainMainColumn">
						<article class="forMainBox">
							<div class="mainBox">
								<div class="row">
									<div class="col-md-12">
									<!-- Adding a new question -->
										<p class="aTitle">ADD A QUESTION TO THE LIST</p>
									</div>
								</div>
								<form class="row formRow" method="POST" action="addquestion">
									<div class="col-md-6 inputColumns">
										<input class="form-control questionInput" type="text" name="question" placeholder="Type a question here...">
									</div>
									<% List<CountOfQuestions> numberOfQuestions = (ArrayList<CountOfQuestions>) request.getAttribute("numberofquestions");
									for(CountOfQuestions numberofquestions : numberOfQuestions){
										out.print("<input type='text' id='nrofque' hidden='' value='"+numberofquestions.getQcoun()+"'>");
									}
									%>
									
									<div class="col-md-6 submitBtnColumns">
										<button type="submit" id="addbtn123" class="btn submitQuestion">add</button>
									</div>
								</form><!-- END Adding a new question -->
								<hr class="firstLine">
								<div class="row">
									<div class="col-md-12">
										<p class="aTitle">ALL QUESTIONS INSIDE THE QUIZ (<%
										for(CountOfQuestions numberofquestions : numberOfQuestions){
											out.print(numberofquestions.getQcoun());
										}
										%>)</p>
									</div>
								</div>
								<!-- Question list with editing, saving and deleting button -->
								<% 
								List<AllQuestions> questionList =  (ArrayList<AllQuestions>) request.getAttribute("allQuestions");
								int i = 1;
								for (AllQuestions individualque : questionList){
								out.print("<div class='row' style='padding-right: 32px;'>");
									out.print("<div class='col-md-6'>");
									out.print("<form action='savequestionchanges' method='POST' id='edit" + individualque.getId() + "'>");
									out.print("<input name='questionid' value='"+ individualque.getId() + "' hidden=''>");
										out.print("<p class='questionParag' id='qfeild-" + individualque.getId() + "'>"+i+". "+individualque.getQuestion()+"</p>");
										out.print("<input class='form-control questionInput' name='newquestion' id='input-" + individualque.getId() + "' value='"+individualque.getQuestion()+"' hidden='true'>");
										out.print("</form>");
									out.print("</div>");
									
									out.print("<div class='col-md-2'></div>");
									
									out.print("<div class='col-md-2 buttonsColumns editBtnCol'>");
										out.print("<button class='btn editBtn' id='edit-" + individualque.getId() + "' type='button' onclick='editquestion(this);'>edit</button>");
										out.print("<button class='btn editBtn' id='edit1-" + individualque.getId() + "' type='submit' onclick='editquestion(this);' form='edit" + individualque.getId() + "' hidden=''>save</button>");
									out.print("</div>");
									
																											
									out.print("<div class='col-md-2 buttonsColumns delBtnCol'>");
										out.print("<form action='deletequestion' method='POST' id='delquestion" + individualque.getId() + "'>");
										out.print("<input name='inputquestion' value='"+ individualque.getId() + "' hidden=''>");
											out.print("<button class='btn deleteBtn' id='delete-" + individualque.getId() + "' type='submit' hidden=''>delete</button>");
										out.print("</form>");
									out.print("</div>");									
								out.print("</div>");
							
									out.print("<hr class='betweenLine'>");
									i++;
								}
								%>
							</div>
						</article>
					</div>
					
				</div>
				
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>
