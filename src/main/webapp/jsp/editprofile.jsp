<!-- 
Edit profile page
@author Samu Uunonen
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Profile</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
		crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/9e29fb8a8c.js"
		crossorigin="anonymous"></script>
	<link href="../css/profilePageStyle.css" rel="stylesheet">
	<script src="../js/script-profile-page.js" defer></script>
</head>
<body>
	<!--  Navigation bar -->
	<%@ include file="../jsp/components/navigationBar.jsp"%>
	<!-- Main content -->
	<div class="container main-container">
		<h1 class="page-header">PROFILE</h1>
		<!-- Profile bio -->
		<form class="bio-form" id="bio-form" name="bio-form" action="/UpdateProfile" method="POST">
		<div class="row main-profile-row">
			<div class="col-lg-7">
				<div class="container container-profile">
					
					<h2 id="candidate-name" name="candidate-name">${fullName}</h2>
					
					<div class="row profile-row">
						<div class="container-fluid">
							
								<label for="nation">Nation: 
									<br>
									<input type="text" class="bio-textfields" id="nationTextField" name="nationTextField" value="${nation}" maxlength="150" required> *
								</label> 
								<label for="city">City: 
									<br> 
									<input type="text" class="bio-textfields" id="cityTextField" name="cityTextField" value="${city}" maxlength="50" required> *
								</label> 
								<label for="Age">Age: 
									<br> 
									<input type="number" class="bio-textfields" id="ageTextField" name="ageTextField" value="${age}" min="18" max="130" required> *
								</label> 
								<label for="sex">Sex: 
									<br> 
									<input type="text" class="bio-textfields" id="sexTextField" name="sexTextField" value="${sex}" maxlength="50">
								</label> 
								<label for="martal-status">Party: 
									<br> 
									<input type="text" class="bio-textfields" id="maritalTextField" name="maritalTextField" value="${party}" maxlength="50">
								</label>
							
						</div>
					</div>
					<!-- Candidate description-->
					<div class="row">
						<textarea class="profile-textarea" id="profile-textarea" name="profile-text" rows="10" form="bio-form" maxlength="250" placeholder="Short description about you..">${description}</textarea>
					</div>

				</div>
			</div>
			<!-- profile image -->
			<div class="col-lg-5 profile-image-col">
				<div class="container container-profile-image">
					<img class="img-fluid profile-image" src="${picture}" max-width="227px" max-height="305" alt="profile image" onerror=this.src="../images/no-image-icon.png"> 
					<span class="container-fluid">
					<input type="number" class="candidate-number-input" id="candidate-number-input" name="candidateNumber" min="0" max="1000" value="${politicNumber}"></span>
				</div>
				<input type="file" class="file-form bio-textfields" id="file-textField" name="file-textField" form="bio-form" value="${picture}" accept="image/*"  maxlength="250">
			</div>
		</div>
		
		
		
		<hr>
		<!-- Email and pass section -->
		<div class="container container-profile">
			<div class="row profile-row">
				<div class="col-md-6">
					<label for="email">Email: 
						<br>
						<input form="bio-form" type="email" class="bio-textfields" id="email-textField" name="email-textField" value="${email}"  maxlength="255" required> *
					</label>
				</div>
				<div class="col-md-6">
					<label for="password">Password: 
						<br> 
						<input form="bio-form" type="password" class="bio-textfields" id="password-textField" name="password-textField" value="${pssword}" maxlength="50" required> *
					</label>
				</div>
			</div>

		</div>
		<hr>
		<!-- Questions section -->
		<div class="container container-profile">
			
			<div class="row profile-row">
				<div class="col-md-6 question-col">
					<h2 class="profile-heading">Questions</h2>
				</div>
				<div class="col-md-6 question-col">
					<h2 class="profile-heading">Answers</h2>
				</div>
			</div>
			
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-1">1. <span class="profile-question" id="span-question-1">${q1}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-1" class="answer-radio"
							id="answer-radio-1" value="1" ${checked11} > 
						<label for="answer-radio-1" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-1" class="answer-radio"
							id="answer-radio-2" value="2" ${checked21} > 
						<label for="answer-radio-2" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-1" class="answer-radio"
							id="answer-radio-3" value="3" ${checked31} > 
						<label for="answer-radio-3" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-1" class="answer-radio"
							id="answer-radio-4" value="4" ${checked41} > 
						<label for="answer-radio-4" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-1" class="answer-radio"
							id="answer-radio-5" value="5" ${checked51} > 
						<label for="answer-radio-5" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
	
	
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-2">2. <span class="profile-question" id="span-question-2">${q2}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio"  name="answer-radio-2" class="answer-radio"
							id="answer-radio-6" value="1" ${checked12}> 
						<label for="answer-radio-6" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-2" class="answer-radio"
							id="answer-radio-7" value="2" ${checked22}> 
						<label for="answer-radio-7" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-2" class="answer-radio"
							id="answer-radio-8" value="3" ${checked32}> 
							<label for="answer-radio-8" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-2" class="answer-radio"
							id="answer-radio-9" value="4" ${checked42}> 
						<label for="answer-radio-9" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-2" class="answer-radio"
							id="answer-radio-10" value="5" ${checked52}> 
						<label for="answer-radio-10" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-3">3. <span class="profile-question" id="span-question-3">${q3}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-3" class="answer-radio"
							id="answer-radio-11" value="1" ${checked13} > <label
							for="answer-radio-11" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-3" class="answer-radio"
							id="answer-radio-12" value="2" ${checked23} > <label
							for="answer-radio-12" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-3" class="answer-radio"
							id="answer-radio-13" value="3" ${checked33} > <label
							for="answer-radio-13" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-3" class="answer-radio"
							id="answer-radio-14" value="4" ${checked43} > <label
							for="answer-radio-14" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-3" class="answer-radio"
							id="answer-radio-15" value="5" ${checked53} > <label
							for="answer-radio-15" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-4">4. <span class="profile-question" id="span-question-4">${q4}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-4" class="answer-radio"
							id="answer-radio-16" value="1" ${checked14}> <label
							for="answer-radio-16" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-4" class="answer-radio"
							id="answer-radio-17" value="2" ${checked24}> <label
							for="answer-radio-17" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-4" class="answer-radio"
							id="answer-radio-18" value="3" ${checked34}> <label
							for="answer-radio-18" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-4" class="answer-radio"
							id="answer-radio-19" value="4" ${checked44}> <label
							for="answer-radio-19" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-4" class="answer-radio"
							id="answer-radio-20" value="5" ${checked54}> <label
							for="answer-radio-20" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-5">5. <span class="profile-question" id="span-question-5">${q5}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-5" class="answer-radio"
							id="answer-radio-21" value="1" ${checked15}> <label
							for="answer-radio-21" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-5" class="answer-radio"
							id="answer-radio-22" value="2" ${checked25}> <label
							for="answer-radio-22" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-5" class="answer-radio"
							id="answer-radio-23" value="3" ${checked35}> <label
							for="answer-radio-23" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-5" class="answer-radio"
							id="answer-radio-24" value="4" ${checked45}> <label
							for="answer-radio-24" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-5" class="answer-radio"
							id="answer-radio-25" value="5" ${checked55}> <label
							for="answer-radio-25" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-6">6. <span class="profile-question" id="span-question-6">${q6}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-6" class="answer-radio"
							id="answer-radio-26" value="1" ${checked16}> <label
							for="answer-radio-26" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-6" class="answer-radio"
							id="answer-radio-27" value="2" ${checked26}> <label
							for="answer-radio-27" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-6" class="answer-radio"
							id="answer-radio-28" value="3" ${checked36}> <label
							for="answer-radio-28" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-6" class="answer-radio"
							id="answer-radio-29" value="4" ${checked46}> <label
							for="answer-radio-29" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-6" class="answer-radio"
							id="answer-radio-30" value="5" ${checked56}> <label
							for="answer-radio-30" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-7">7. <span class="profile-question" id="span-question-7">${q7}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-7" class="answer-radio"
							id="answer-radio-31" value="1" ${checked17}> <label
							for="answer-radio-31" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-7" class="answer-radio"
							id="answer-radio-32" value="2" ${checked27}> <label
							for="answer-radio-32" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-7" class="answer-radio"
							id="answer-radio-33" value="3" ${checked37}> <label
							for="answer-radio-33" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-7" class="answer-radio"
							id="answer-radio-34" value="4" ${checked47}> <label
							for="answer-radio-34" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-7" class="answer-radio"
							id="answer-radio-35" value="5" ${checked57}> <label
							for="answer-radio-35" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-8">8. <span class="profile-question" id="span-question-8">${q8}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-8" class="answer-radio"
							id="answer-radio-36" value="1" ${checked18}> <label
							for="answer-radio-36" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-8" class="answer-radio"
							id="answer-radio-37" value="2" ${checked28}> <label
							for="answer-radio-37" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-8" class="answer-radio"
							id="answer-radio-38" value="3" ${checked38}> <label
							for="answer-radio-38" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-8" class="answer-radio"
							id="answer-radio-39" value="4" ${checked48}> <label
							for="answer-radio-39" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-8" class="answer-radio"
							id="answer-radio-40" value="5" ${checked58}> <label
							for="answer-radio-40" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-9">9. <span class="profile-question" id="span-question-9">${q9}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-9" class="answer-radio"
							id="answer-radio-41" value="1" ${checked19}> <label
							for="answer-radio-41" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-9" class="answer-radio"
							id="answer-radio-42" value="2" ${checked29}> <label
							for="answer-radio-42" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-9" class="answer-radio"
							id="answer-radio-43" value="3" ${checked39}> <label
							for="answer-radio-43" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-9" class="answer-radio"
							id="answer-radio-44" value="4" ${checked49}> <label
							for="answer-radio-44" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-9" class="answer-radio"
							id="answer-radio-45" value="5" ${checked59}> <label
							for="answer-radio-45" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
				<div class="row question-row">
					<div class="col-md-6 question-col container-answer-mark">
						<p id="p-question-10">10. <span class="profile-question" id="span-question-10">${q10}</span>
					</div>
					<div class="col-md-6 question-col container-answer-mark">
						<input type="radio" name="answer-radio-10" class="answer-radio"
							id="answer-radio-46" value="1" ${checked110}> <label
							for="answer-radio-46" class="answer-mark" id="answer-mark-1"></label>
	
						<input type="radio" name="answer-radio-10" class="answer-radio"
							id="answer-radio-47" value="2" ${checked210}> <label
							for="answer-radio-47" class="answer-mark" id="answer-mark-2"></label>
	
						<input type="radio" name="answer-radio-10" class="answer-radio"
							id="answer-radio-48" value="3" ${checked310}> <label
							for="answer-radio-48" class="answer-mark" id="answer-mark-3"></label>
	
						<input type="radio" name="answer-radio-10" class="answer-radio"
							id="answer-radio-49" value="4" ${checked410}> <label
							for="answer-radio-49" class="answer-mark" id="answer-mark-4"></label>
	
						<input type="radio" name="answer-radio-10" class="answer-radio"
							id="answer-radio-50" value="5" ${checked510}> <label
							for="answer-radio-50" class="answer-mark" id="answer-mark-5"></label>
					</div>
					<hr class="question-row-hr">
				</div>
			
		</div>

		</form>
		<div class="container-fluid">
			<div class="row row-confirm-delete hide" id="confirm-delete-profile">
				<label>Are you sure you want to delete your profile?</label>
				<a class="btn btn-yes" id="btn-yes" href="/DeleteCandidate">YES</a>	<!-- By clicking this button, delete profile -->
				<button class="btn btn-no" id="btn-no">NO</button>
			</div>
			<div class="row">
				<div class="container-button">
					<button class="btn btn-delete" id="btn-delete">DELETE PROFILE</button>

					<button type="submit" form="bio-form" class="btn btn-save" id="btn-save">SAVE PROFILE</button> <!-- By clicking this button, save data into database -->
				</div>
			</div>
		</div>
		
	</div>
	<!-- Implementing jquery for bootsrap -->
	<%@ include file="../scripts/bootstrap_jq.html"%>
</body>
</html>