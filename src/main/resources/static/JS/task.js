'use strict';
const act1 = document.querySelector('#act');
const priority1 = document.querySelector('#priority');
const date1 = document.querySelector('#date');
const notes1 = document.querySelector('#notes');
const completed1 = document.querySelector('#bool');
const toDo1 = document.querySelector('#createToDoTask');
const taskDeleteId1 = document.querySelector('#taskDeleteId')


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
        "toDO": {
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


const taskReadAll = () => {
    fetch("http://localhost:8080/task/readAll")
        .then(response => response.json())
        .then(info => {
            for (let task of info) {
                console.log(task);
            }
        })
        .catch(err => console.error('error ${err}'));

}
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

    fetch('http://localhost:8080/task/delete/${taskDeleteId}', {
        method: "DELETE",
    }).then(response => console.log(`Task with ID {taskDeleteId} deleted`)
    ).catch(err => console.error(`Stop!! ${err}`));

}