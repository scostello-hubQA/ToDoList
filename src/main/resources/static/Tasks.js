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
const printDelete = document.querySelector('#confirmationOfDelete');
const confirmCreate = document.querySelector('#createConfirmed');
const clearPage = document.querySelector('#clearPage');
const removeRead = document.querySelector('#forRemoveP');



const printTaskToScreen = (tasks) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${tasks}`);
	user.appendChild(text);
	taskprint.appendChild(user);

}

const printDeleteToScreen = (deleted) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`your task with id ${deleted} was deleted`);
	user.appendChild(text);
	printDelete.appendChild(user);
}

const deleteFailed = (notDeleted) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`your task with id ${notDeleted} was not deleted`);
	user.appendChild(text);
	printDelete.appendChild(user);
}

const printCreateCon = (created) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${created}`);
	user.appendChild(text);
	confirmCreate.appendChild(user);
	
	
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
console.log(data);
console.log(JSON.stringify(data));

	fetch("http://localhost:8080/task/create", {
		method: "POST",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).then((response) => {
			if (response.status != 201) {
				throw new Error(`i dont have a status of 201`);
			} else {
				console.log(response);
				console.log(`response is okay (201)`);
				printCreateCon(`your task ${actValue} was created, use read all to find the id` );
				
					
				
						
						//let myJSON = JSON.stringify(tasks.taskList);
						//printTaskToScreen(myJSON);
					
					
				}
			}
		).catch((err) => {
			console.error(err);
		})
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
	}).then((response) => {
			if (response.status != 204) {
				throw new Error(`i dont have a status of 204`);
			} else {
				console.log(response);
				console.log(`response is okay (204)`);
				printDeleteToScreen(`your task with id ${taskDeleteId} `);
				
					
				
						
						//let myJSON = JSON.stringify(tasks.taskList);
						//printTaskToScreen(myJSON);
					
					
				}
			}
		).catch((err) => {
			console.error(err);
		})
}



const updateTask = () => {

	const taskIdValue = updateId1.value;
	
	const actValue = updateTask1.value;
	const priorityValue = updatePriority1.value;
	const dateValue = updateDate1.value;
	const notesValue = updateAddInfo1.value;
	const completedValue = updateTaskCompletion1.value;
	const updatedToDoValue = updateToDoTask1.value;

	let data = {
		
		"act": actValue,
		"priority": priorityValue,
		"date": dateValue,
		"notes": notesValue,
		"completed": completedValue ? false : true,
		"toDo": {"id": updatedToDoValue
		}
	}
	
	console.log()
	
console.log(data);
console.log(JSON.stringify(data));

	fetch(`http://localhost:8080/task/update/${taskIdValue}`, {
		method: "PUT",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).then((response) => {
			if (response.status != 200) {
				throw new Error(`i dont have a status of 200`);
			} else {
				console.log(response);
				console.log(`response is okay (200)`);
				//printCreateCon(`your task ${actValue} was created, use read all to find the id` );
				
					
				
						
						//let myJSON = JSON.stringify(tasks.taskList);
						//printTaskToScreen(myJSON);
					
					
				}
			}
		).catch((err) => {
			console.error(err);
		})
}


