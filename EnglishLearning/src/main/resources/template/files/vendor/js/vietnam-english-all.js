//selecting all required elements
const start_btn = document.querySelector(".start_btn button");
const info_box = document.querySelector(".info_box");
const exit_btn = info_box.querySelector(".buttons .quit");
const continue_btn = info_box.querySelector(".buttons .restart");
const quiz_box = document.querySelector(".quiz_box");
const result_box = document.querySelector(".result_box");
const option_list = document.querySelector(".option_list");
const time_line = document.querySelector("header .time_line");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");


let timeValue =  15;
let que_count = 0;
let que_numb = 1;
let userScore = 0;
let counter;
let counterLine;
let widthValue = 0;
let orderNumber = 0;
// creating the new div tags which for icons
let tickIconTag = '<div class="icon tick" style="margin-left: auto;"><i class="fas fa-check"></i></div>';
let crossIconTag = '<div class="icon cross" style="margin-left: auto;"><i class="fas fa-times"></i></div>';


const restart_quiz = result_box.querySelector(".buttons .restart");
const quit_quiz = result_box.querySelector(".buttons .quit");


const next_btn = document.querySelector("footer .next_btn");
const bottom_ques_counter = document.querySelector("footer .total_que");
const parameter = () => {
	const queryString = window.location.search;

	const urlParams = new URLSearchParams(queryString);

	const cateId = urlParams.get('cate');
	return cateId;

}




// if restartQuiz button clicked
/*restart_quiz.onclick = ()=>{
    quiz_box.classList.add("activeQuiz"); //show quiz box
    result_box.classList.remove("activeResult"); //hide result box
    timeValue = 15; 
    que_count = 0;
    que_numb = 1;
    userScore = 0;
    widthValue = 0;
    showQuetions(que_count); //calling showQestions function
    queCounter(que_numb); //passing que_numb value to queCounter
    clearInterval(counter); //clear counter
    clearInterval(counterLine); //clear counterLine
    startTimer(timeValue); //calling startTimer function
    startTimerLine(widthValue); //calling startTimerLine function
    timeText.textContent = "Time Left"; //change the text of timeText to Time Left
    next_btn.classList.remove("show"); //hide the next button
}

// if quitQuiz button clicked
quit_quiz.onclick = ()=>{
    window.location.reload(); //reload the current window
}

const next_btn = document.querySelector("footer .next_btn");
const bottom_ques_counter = document.querySelector("footer .total_que");

// if Next Que button clicked
next_btn.onclick = ()=>{
    if(que_count < questions.length - 1){ //if question count is less than total question length
        que_count++; //increment the que_count value
        que_numb++; //increment the que_numb value
        showQuetions(que_count); //calling showQestions function
        queCounter(que_numb); //passing que_numb value to queCounter
        clearInterval(counter); //clear counter
        clearInterval(counterLine); //clear counterLine
        startTimer(timeValue); //calling startTimer function
        startTimerLine(widthValue); //calling startTimerLine function
        timeText.textContent = "Time Left"; //change the timeText to Time Left
        next_btn.classList.remove("show"); //hide the next button
    }else{
        clearInterval(counter); //clear counter
        clearInterval(counterLine); //clear counterLine
        showResult(); //calling showResult function
    }
}*/

// getting questions and options from array
function showQuetions(index){
    const que_text = document.querySelector(".que_text");

    //creating a new span and div tag for question and option and passing the value using array index
    let que_tag = '<span>'+ questions[index].numb + ". " + questions[index].question +'</span>';
    let testtingQuestion = questions[index].answer;
	console.log(testtingQuestion);
    let txtAnswer = `<div class="option" id="answerBox" style="padding:0px;width:50%">
    <input type="text" id="answerEnglish" style="border:1px solid; border-radius:20px; padding:5px; text-align:center"/>
    <input type="text" id="answerType" maxlength="4" style="margin-left:20px; width:50px; border:1px solid; border-radius:20px; padding:5px; text-align:center"/>
    </div>`;
    let btnSubmit = `<button id="submit" onclick="optionSelected()">Submit</button>`;
    
    que_text.innerHTML = que_tag; //adding new span tag inside que_tag
    option_list.innerHTML = txtAnswer+btnSubmit; //adding new div tag inside option_tag

	
         
}



//if user clicked on option
function optionSelected(){
    let answerEnglish =  document.getElementById('answerEnglish').value;
    let answerType = document.getElementById('answerType').value;
    let answerBox =  document.getElementById('answerBox');
    let optionBox = document.querySelector('.option_list');
    let submitBtn = document.getElementById('submit');
    clearInterval(counter); //clear counter
    clearInterval(counterLine); //clear counterLine
    let userAnsEnglish = answerEnglish; //getting user selected option
    let userAnsType = answerType; //getting user selected option
    let correcAnsEnglish = questions[que_count].answer; //getting correct answer from array
    let correcAnsType = questions[que_count].answerType;
   
    
    
    if(userAnsEnglish.toUpperCase() == correcAnsEnglish.toUpperCase() && userAnsType.toUpperCase() == correcAnsType.toUpperCase() ){ //if user selected option is equal to array's correct answer
        userScore += 1; //upgrading score value with 1
        
	    answerBox.removeAttribute("style");
        answerBox.classList.add("correct");
        
        answerBox.insertAdjacentHTML('beforeend', tickIconTag);
        
        optionBox.removeChild(submitBtn);
        
        
	console.log('correct answer');
    }else{
        
	    answerBox.removeAttribute("style");
        answerBox.classList.add("incorrect");
       
        answerBox.insertAdjacentHTML('beforeend', crossIconTag);
        optionBox.removeChild(submitBtn);
        console.log('Wrong Answer');
    }
  
    next_btn.classList.add("show"); //show the next button if user selected any option
}

function showResult(){
    info_box.classList.remove("activeInfo"); //hide info box
    quiz_box.classList.remove("activeQuiz"); //hide quiz box
    result_box.classList.add("activeResult"); //show result box
    const scoreText = result_box.querySelector(".score_text");
    if (userScore > 3){ // if user scored more than 3
        //creating a new span tag and passing the user score number and total question number
        let scoreTag = '<span>and congrats! 🎉, You got <p>'+ userScore +'</p> out of <p>'+ questions.length +'</p></span>';
        scoreText.innerHTML = scoreTag;  //adding new span tag inside score_Text
    }
    else if(userScore > 1){ // if user scored more than 1
        let scoreTag = '<span>and nice 😎, You got <p>'+ userScore +'</p> out of <p>'+ questions.length +'</p></span>';
        scoreText.innerHTML = scoreTag;
    }
    else{ // if user scored less than 1
        let scoreTag = '<span>and sorry 😐, You got only <p>'+ userScore +'</p> out of <p>'+ questions.length +'</p></span>';
        scoreText.innerHTML = scoreTag;
    }
}

function startTimer(time){
    counter = setInterval(timer, 1000);
    let answerBox =  document.getElementById('answerBox');
    let answerEnglish =  document.getElementById('answerEnglish');
    let answerType = document.getElementById('answerType');
    let optionBox = document.querySelector('.option_list');
    let submitBtn = document.getElementById('submit');
    function timer(){
        timeCount.textContent = time; //changing the value of timeCount with time value
        time--; //decrement the time value
        if(time < 9){ //if timer is less than 9
            let addZero = timeCount.textContent; 
            timeCount.textContent = "0" + addZero; //add a 0 before time value
        }
        if(time < 0){ //if timer is less than 0
            clearInterval(counter); //clear counter
            timeText.textContent = "Time Off"; //change the time text to time off
            let correcAnsEnglish = questions[que_count].answer; //getting correct answer from array
            let correcAnsType = questions[que_count].answerType;
            
            answerEnglish.value = correcAnsEnglish;
            answerType.value = correcAnsType;

            answerBox.removeAttribute("style");
            answerBox.classList.add("correct");
            optionBox.removeChild(submitBtn);
           
            answerBox.insertAdjacentHTML('beforeend', tickIconTag);

            next_btn.classList.add("show"); //show the next button if user selected any option
        }
    }
}

function startTimerLine(time){
    counterLine = setInterval(timer, 29);
    function timer(){
        time += 1; //upgrading time value with 1
        time_line.style.width = time + "px"; //increasing width of time_line with px by time value
        if(time > 549){ //if time value is greater than 549
            clearInterval(counterLine); //clear counterLine
        }
    }
}

function queCounter(index){
    //creating a new span tag and passing the question number and total question
    let totalQueCounTag = '<span><p>'+ index +'</p> of <p>'+ questions.length +'</p> Questions</span>';
    bottom_ques_counter.innerHTML = totalQueCounTag;  //adding new span tag inside bottom_ques_counter
}

//Create Random Array
function shuffle(array) {
  var tmp, current, top = array.length;
  if(top) while(--top) {
    current = Math.floor(Math.random() * (top + 1));
    tmp = array[current];
    array[current] = array[top];
    array[top] = tmp;
  }
  return array;
}







//Gọi API thực hiện chức năng kiểm tra
axios.get(`/EnglishLearning/exam/api/vocabulary/all`)
  .then(function (response) {
   // if restartQuiz button clicked
	let vocabulary = [];
	let dataShuffle = shuffle(response.data);
	
	dataShuffle.forEach(el => vocabulary.push(el.vietnamese));
	
	
	dataShuffle.forEach(el => {
		
		let newQuestion =   {
	    	numb: orderNumber + 1,
	    	question: el.vietnamese,
	   		answer: el.english,
			answerType:el.type
  		};
	
			questions.push(newQuestion);
			orderNumber++;
	
	
});

	
restart_quiz.onclick = ()=>{
	if(questions.length >= 3){
	quiz_box.classList.add("activeQuiz"); //show quiz box
    result_box.classList.remove("activeResult"); //hide result box
    timeValue = 15; 
    que_count = 0;
    que_numb = 1;
    userScore = 0;
    widthValue = 0;
	shuffle(questions);
    showQuetions(que_count); //calling showQestions function
    queCounter(que_numb); //passing que_numb value to queCounter
    clearInterval(counter); //clear counter
    clearInterval(counterLine); //clear counterLine
    startTimer(timeValue); //calling startTimer function
    startTimerLine(widthValue); //calling startTimerLine function
    timeText.textContent = "Time Left"; //change the text of timeText to Time Left
    next_btn.classList.remove("show"); //hide the next button
	}else{
		alert('Your questions must be large than 3 to begin the test');
	}
	

}

// if quitQuiz button clicked
quit_quiz.onclick = ()=>{
    window.location.reload(); //reload the current window
}


// if Next Que button clicked
next_btn.onclick = ()=>{
    if(questions.length >= 3){
	 if(que_count < questions.length - 1){ //if question count is less than total question length
        que_count++; //increment the que_count value
        que_numb++; //increment the que_numb value
        showQuetions(que_count); //calling showQestions function
        queCounter(que_numb); //passing que_numb value to queCounter
        clearInterval(counter); //clear counter
        clearInterval(counterLine); //clear counterLine
        startTimer(timeValue); //calling startTimer function
        startTimerLine(widthValue); //calling startTimerLine function
        timeText.textContent = "Time Left"; //change the timeText to Time Left
        next_btn.classList.remove("show"); //hide the next button
    }else{
        clearInterval(counter); //clear counter
        clearInterval(counterLine); //clear counterLine
        showResult(); //calling showResult function
    }
   }else{
	alert('Your questions must be large than 3 to begin the test');
}
	
   
}
// if startQuiz button clicked
start_btn.onclick = ()=>{
    info_box.classList.add("activeInfo"); //show info box
}

// if exitQuiz button clicked
exit_btn.onclick = ()=>{
    info_box.classList.remove("activeInfo"); //hide info box
}

// if continueQuiz button clicked
continue_btn.onclick = ()=>{
	if(questions.length >= 3) {
    info_box.classList.remove("activeInfo"); //hide info box
    quiz_box.classList.add("activeQuiz"); //show quiz box
    showQuetions(0); //calling showQestions function
    queCounter(1); //passing 1 parameter to queCounter
    startTimer(15); //calling startTimer function
    startTimerLine(0); //calling startTimerLine function
	}else{
		alert('Your questions must be large than 3 to begin the test');
	}
  
}
console.log(questions);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
  })
  .then(function () {
    // always executed
  });