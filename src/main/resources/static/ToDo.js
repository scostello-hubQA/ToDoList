'use strict';

const toDoCreate = document.querySelector('#toDoTitle');


const createToDo = () => {
	const toDoValue = toDoCreate.value;
	
	let data = {
		
		"name": toDoValue
	}
	
	fetch("http://localhost:8080/todo/create", {
		method : "POST",
		body: JSON.stringify(data),
		headers: {
			"Content-Type" :"application/json"
		}
	}).then(response => response.json())
	.then(info => console.log(info))
	.catch(err => console.error('something went wrong! ${err}'));
	
}

const toDoReadAll =() => {
	fetch("http://localhost:8080/todo/readAll")
			.then((response) => {
			if (response.status != 200) {
				throw new Error(`i dont have a status of 200`);
			} else {
				console.log(response);
				console.log(`response is okay (200)`);
				response.json().then((infofromserver) => {
					console.log(infofromserver);
					//     console.log(infofromserver.data);
				})
			}
		}).catch((err) => {
			console.error(err);
		})
}
