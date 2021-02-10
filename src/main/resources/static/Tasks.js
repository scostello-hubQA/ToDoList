'use strict';
const act1 = document.querySelector('#act');
const priority1 = document.querySelector('#priority');
const date1 = document.querySelector('#date');
const notes1 = document.querySelector('#notes');
const completed1 = document.querySelector('#bool');
const toDo1 = document.querySelector('#addToDoTask');
const taskDeleteId1 = document.querySelector('#taskDeleteId');
const updateId1 = document.querySelector('#updateId');
const updateTask1 = document.querySelector('#updateTask');
const updatePriority1 = document.querySelector('#updatePriority');
const updateDate1 = document.querySelector('#updateDate');
const updateAddInfo1 = document.querySelector('#updateAddInfo');
const updateTaskCompletion1 = document.querySelector('#updatebool');
const updateToDoTask1 = document.querySelector('#updateToDoTask');
const taskprint = document.querySelector('#printToScreenTask');


const printTaskToScreen = (tasklist) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${tasklist}`);
	user.appendChild(text);
	taskprint.appendChild(user);

}





const createTask = () => {
	const actValue = act1.value;
	const priorityValue = priority1.value;
	const dateValue = date1.value;
	const notesValue = notes1.value;
	const completedValue = completed1.value;
	const toDoValue = toDo1.value;



	let data = {

		"act": actValue,
		"priority": priorityValue,
		"date": dateValue,
		"notes": notesValue,
		"completed": completedValue,
		"toDo": {
			"id": toDoValue
		}
	}


	fetch("http://localhost:8080/task/create", {
		method: "POST",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.then(info => console.log(info))
		.catch(err => console.error('something went wrong! ${err}'));

}


//const taskReadAll = () => {
// fetch("http://localhost:8080/task/readAll")
//      .then(response => response.json())
//       .then(info => {
//          for (let task of info) {
//                console.log(task);
//            }
//       })
//        .catch(err => console.error('error ${err}'));

//}



const taskRead = () => {
	fetch("http://localhost:8080/task/readAll")
		.then((response) => {
			if (response.status != 200) {
				throw new Error(`i dont have a status of 200`);
			} else {
				console.log(response);
				console.log(`response is okay (200)`);
				
				response.json().then((infofromserver) => {
					console.log(infofromserver);
					console.log(infofromserver.data);
					console.table(infofromserver);
					
					for(let tasks of infofromserver){
						printTaskToScreen(tasks.taskId+ " | " + tasks.act+ " | " + tasks.notes);
						
						//let myJSON = JSON.stringify(tasks.taskList);
						//printTaskToScreen(myJSON);
					}
					
				})
			}
		}).catch((err) => {
			console.error(err);
		})
}



const deleteTask = () => {
	const taskDeleteId = taskDeleteId1.value;

	let data = {
		"taskId": taskDeleteId
	}

	fetch(`http://localhost:8080/task/delete/${taskDeleteId}`, {
		method: "DELETE",
	}).then(response => console.log(`task with id ${taskDeleteId} deleted`))
		.catch(err => console.error(`Stop!! ${err}`));

}


const updateTask = () => {

	const actIdValue = updateId1.value;
	const actValue = updateTask1.value;
	const priorityValue = updatePriority1.value;
	const dateValue = updateDate1.value;
	const notesValue = updateAddInfo1.value;
	const completedValue = updateTaskCompletion1.value;
	const updatedToDoValue = updateToDoTask1;

	let data = {
		"taskId": actIdValue,
		"act": actValue,
		"priority": priorityValue,
		"date": dateValue,
		"notes": notesValue,
		"completed": completedValue ? false : true,
		"toDo": {
			"id": updatedToDoValue
		}
	}

	fetch(`http://localhost:8080/task/update/${actIdValue}`, {
		method: "PUT",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.then(info => console.log(info))
		.catch(err => console.error(`something went wrong ${err}`));

}


