'use strict';

const toDoCreate = document.querySelector('#toDoTitle');
const deleteToDo1 = document.querySelector('#toDoDeleteId');
const readAllPrint = document.querySelector('#readAllToDo');
const deleteToDoById = document.querySelector('#DeleteToDo');
const todoUpdateName = document.querySelector('#updatedname');
const todoUpdateId = document.querySelector('#UpdateId');
const readById = document.querySelector('#toDoReadId');
const confirmCreate = document.querySelector('#createConfirm');



//const jsonToString = (todo) => {
	//let id = todo.id;
	//let act = todo.name;
	//let myJSON = JSON.stringify(todo.taskList);
	//let priority = myJSON.priority;
	
	
	//let statement = `ID = ${id}, task = ${act}, priority = ${priority}`;
	//printToDoToScreen(statement);
//}

const printToDoToScreen = (todolist) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${todolist}`);
	user.appendChild(text);
	readAllPrint.appendChild(user);
}

const printDeleteToScreen = (deleted)=>{
	let user = document.createElement("p");
	let text = document.createTextNode(`${deleted}`);
	user.appendChild(text);
	deleteToDoById.appendChild(user);
}

const printCreateCon = (confirm) => {
		let user = document.createElement("p");
	let text = document.createTextNode(`${confirm}`);
	user.appendChild(text);
	confirmCreate.appendChild(user);
	
}



const readByIdToDo =() => {
	const readToDoId = readById.value;
	
	fetch(`http://localhost:8080/todo/read/${readToDoId}`)
	.then((response) => {
		if (response.status !=200){
			throw new Error(`i dont have a status of 200`);
			
		}else{
			console.log(response);
			console.log(`response is okay (200)`);
			response.json().then((infofromserver) => {
				console.log(infofromserver);
				
					printToDoToScreen(infofromserver.id + ". " +infofromserver.name);
					let myJSON = JSON.stringify(infofromserver.taskList);
						printToDoToScreen(myJSON);
						
				
			})
		}
	}).catch((err) => {
		console.error(err);
	})
}

const createToDo = () => {
	const toDoValue = toDoCreate.value;

	let data = {

		"name": toDoValue
	}

	fetch("http://localhost:8080/todo/create", {
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
			printCreateCon(`your task ${toDoValue} was created, use read all to find the id`);




			//let myJSON = JSON.stringify(tasks.taskList);
			//printTaskToScreen(myJSON);


		}
	}
	).catch((err) => {
		console.error(err);
	})
}
const toDoReadAll = () => {
	fetch("http://localhost:8080/todo/readAll")
		.then((response) => {
			if (response.status != 200) {
				throw new Error(`i dont have a status of 200`);
			} else {
				console.log(response);
				console.log(`response is okay (200)`);
				response.json().then((infofromserver) => {
					console.log(infofromserver);
					console.log(infofromserver.data);
					for(let todo of infofromserver){
						
						//printToDoToScreen(todo.id);
						console.table(todo);
						printToDoToScreen(todo.id + ". " +todo.name);
					let myJSON = JSON.stringify(todo.taskList);
						printToDoToScreen(myJSON);
						
					}

				})
			}
		}).catch((err) => {
			console.error(err);
		})
}

const deleteToDo = () => {
	const toDoDelete = deleteToDo1.value

	let data = {
		"id": toDoDelete

	}
	fetch(`http://localhost:8080/todo/delete/${toDoDelete}`, {

		method: "DELETE",
	}).then((response) => {
			if (response.status != 204) {
				throw new Error(`i dont have a status of 204`);
			} else {
				console.log(response);
				console.log(`response is okay (204)`);
				printDeleteToScreen(`your to do with id ${toDoDelete} was deleted `);
					
				}
			}
		).catch((err) => {
			console.error(err);
		})
}


//cosnt readById = () => {

//}


const updateToDo = () =>{
	
	const updateId =  todoUpdateId.value;
	const updateName = todoUpdateName.value;
	
	let data = {
		"name":updateName
		
	}
	fetch(`http://localhost:8080/todo/update/${updateId}`,{
		method:"PUT",

		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(response => response.json())
		.then(info => console.log(info))
		.catch(err => console.error(`something went wrong! ${err}`));
}