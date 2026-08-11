const API_URL = "http://localhost:8080/todos";

const todoInput = document.getElementById("todoInput");
const addBtn = document.getElementById("addBtn");
const todoList = document.getElementById("todoList");
const totalCount = document.getElementById("totalCount");
const completedCount = document.getElementById("completedCount");

// Load todos when page opens
window.onload = loadTodos;

// Add button
addBtn.addEventListener("click", addTodo);

// Enter key
todoInput.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        addTodo();
    }
});

// ===================== LOAD TODOS =====================
async function loadTodos() {
    try {
        const response = await fetch(API_URL);
        const todos = await response.json();

        todoList.innerHTML = "";

        let completed = 0;

        todos.forEach(todo => {
            createTodoCard(todo);

            if (todo.completed) {
                completed++;
            }
        });

        totalCount.textContent = todos.length;
        completedCount.textContent = completed;

    } catch (error) {
        console.error("Error loading todos:", error);
    }
}

// ===================== ADD TODO =====================
async function addTodo() {

    const task = todoInput.value.trim();

    if (task === "") {
        alert("Please enter a task.");
        return;
    }

    const todo = {
        task: task,
        completed: false
    };

    try {
        await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(todo)
        });

        todoInput.value = "";
        loadTodos();

    } catch (error) {
        console.error("Error adding todo:", error);
    }
}

// ===================== CREATE TODO CARD =====================
function createTodoCard(todo) {

    const card = document.createElement("div");
    card.className = "todo";

    const text = document.createElement("span");
    text.className = "todo-text";

    if (todo.completed) {
        text.classList.add("completed");
    }

    text.textContent = todo.task;

    const actions = document.createElement("div");
    actions.className = "actions";

    // Complete button
    const completeBtn = document.createElement("button");
    completeBtn.className = "complete-btn";
    completeBtn.innerHTML = "✔";

    completeBtn.onclick = async () => {

        const updatedTodo = {
            task: todo.task,
            completed: !todo.completed
        };

        await fetch(`${API_URL}/${todo.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updatedTodo)
        });

        loadTodos();
    };

    // Delete button
    const deleteBtn = document.createElement("button");
    deleteBtn.className = "delete-btn";
    deleteBtn.innerHTML = "🗑";

    deleteBtn.onclick = async () => {

        await fetch(`${API_URL}/${todo.id}`, {
            method: "DELETE"
        });

        loadTodos();
    };

    actions.appendChild(completeBtn);
    actions.appendChild(deleteBtn);

    card.appendChild(text);
    card.appendChild(actions);

    todoList.appendChild(card);
}