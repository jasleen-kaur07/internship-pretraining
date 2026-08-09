const todoInput = document.getElementById("todoInput");
const addBtn = document.getElementById("addBtn");
const todoList = document.getElementById("todoList");
const totalCount = document.getElementById("totalCount");
const completedCount = document.getElementById("completedCount");

let totalTasks = 0;
let completedTasks = 0;

// Add task when button is clicked
addBtn.addEventListener("click", addTodo);

// Add task when Enter key is pressed
todoInput.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        addTodo();
    }
});

function addTodo() {

    const task = todoInput.value.trim();

    if (task === "") {
        alert("Please enter a task.");
        return;
    }

    const todo = document.createElement("div");
    todo.classList.add("todo");

    todo.innerHTML = `
        <span class="todo-text">${task}</span>

        <div class="actions">

            <button class="complete-btn">
                <i class="fa-solid fa-check"></i>
            </button>

            <button class="delete-btn">
                <i class="fa-solid fa-trash"></i>
            </button>

        </div>
    `;

    const text = todo.querySelector(".todo-text");
    const completeBtn = todo.querySelector(".complete-btn");
    const deleteBtn = todo.querySelector(".delete-btn");

    // Complete task
    completeBtn.addEventListener("click", function () {

        if (!text.classList.contains("completed")) {

            text.classList.add("completed");
            completedTasks++;

        } else {

            text.classList.remove("completed");
            completedTasks--;

        }

        updateCounts();

    });

    // Delete task
    deleteBtn.addEventListener("click", function () {

        if (text.classList.contains("completed")) {
            completedTasks--;
        }

        totalTasks--;

        todo.remove();

        updateCounts();

    });

    todoList.appendChild(todo);

    totalTasks++;

    updateCounts();

    todoInput.value = "";

    todoInput.focus();

}

function updateCounts() {

    totalCount.textContent = totalTasks;

    completedCount.textContent = completedTasks;

}