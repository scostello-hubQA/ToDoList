'use strict';

const toDoCreate = document.querySelector('#toDoTitle');
const deleteToDo1 = document.querySelector('#toDoDeleteId');
const readAllPrint = document.querySelector('#readAllToDo');


const printToDoToScreen = (todolist) => {
	let user = document.createElement("p");
	let text = document.createTextNode(`${todolist}`);
	user.appendChild(text);
	readAllPrint.appendChild(user);
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
	}).then(response => response.json())
		.then(info => console.log(info))
		.catch(err => console.error('something went wrong! ${err}'));

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
						printToDoToScreen(todo.name);
						printToDoToScreen(todo.id);
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
	}).then(response => console.log(`to do with id ${toDoDelete} deleted`))
		.catch(err => console.error(`Stop!! ${err}`));
}


//cosnt readById = () => {

//}
