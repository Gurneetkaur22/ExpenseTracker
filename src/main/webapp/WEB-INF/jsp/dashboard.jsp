<%@ include file="header.jsp" %>
<h2 class="text-center mb-4">Welcome, ${userName}</h2>

<ul class="nav nav-tabs" id="dashboardTabs">
    <li class="nav-item"><button class="nav-link active" data-bs-toggle="tab" data-bs-target="#add">Add Expense</button></li>
    <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#view">View Expenses</button></li>
    <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#category">Category Analysis</button></li>
    <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#monthly">Monthly</button></li>
    <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#weekly">Weekly</button></li>
    <li class="nav-item"><button class="nav-link text-danger" data-bs-toggle="tab" data-bs-target="#delete">Delete Expense</button></li>
</ul>

<div class="tab-content mt-3">
    <!-- Add Expense -->
    <div class="tab-pane fade show active" id="add">
        <form id="addForm">
            <div class="mb-3">
                <label>Category</label>
                <input type="text" id="category" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Amount</label>
                <input type="number" step="0.01" id="amount" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Date</label>
                <input type="date" id="date" class="form-control" required>
            </div>
            <button type="button" class="btn btn-primary" onclick="addExpense()">Add</button>
        </form>
        <div id="addMsg" class="mt-2"></div>
    </div>

    <!-- View Expenses -->
    <div class="tab-pane fade" id="view">
        <h5>All Expenses</h5>
        <table class="table table-bordered" id="expensesTable">
            <thead>
                <tr><th>ID</th><th>Category</th><th>Amount</th><th>Date</th></tr>
            </thead>
            <tbody></tbody>
        </table>
        <button class="btn btn-secondary" onclick="fetchExpenses()">Refresh</button>
    </div>

    <!-- Category Analysis -->
    <div class="tab-pane fade" id="category">
        <h5>Category-wise Summary</h5>
        <ul id="categoryList"></ul>
        <button class="btn btn-secondary" onclick="fetchCategory()">Refresh</button>
    </div>

    <!-- Monthly Total -->
    <div class="tab-pane fade" id="monthly">
        <h5>Monthly Total: <span id="monthlyTotal">0</span></h5>
        <input type="month" id="monthInput" class="form-control mb-2">
        <button class="btn btn-secondary" onclick="fetchMonthly()">Get Monthly Total</button>
    </div>

    <!-- Weekly Total -->
    <div class="tab-pane fade" id="weekly">
        <h5>Weekly Total: <span id="weeklyTotal">0</span></h5>
        <button class="btn btn-secondary" onclick="fetchWeekly()">Get Weekly Total</button>
    </div>

    <!-- Delete Expense -->
    <div class="tab-pane fade" id="delete">
        <form id="deleteForm">
            <div class="mb-3">
                <label>Expense ID</label>
                <input type="number" id="expenseId" class="form-control" required>
            </div>
            <button type="button" class="btn btn-danger" onclick="deleteExpense()">Delete</button>
        </form>
        <div id="deleteMsg" class="mt-2"></div>
    </div>
</div>

<script>
const apiUrl = 'http://localhost:8082/api/expenses'; // API Gateway base
const userId = '${userId}'; // Set this dynamically in your controller

// Add Expense
function addExpense() {
    const data = {
        category: document.getElementById('category').value,
        amount: parseFloat(document.getElementById('amount').value),
        date: document.getElementById('date').value,
        userId: userId
    };

    fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.json())
      .then(exp => {
          document.getElementById('addMsg').innerText = "Expense added: ID " + exp.id;
          document.getElementById('addForm').reset();
          fetchExpenses();
      });
}

// View Expenses
function fetchExpenses() {
    fetch(`${apiUrl}/user/${userId}`)
        .then(res => res.json())
        .then(expenses => {
            const tbody = document.querySelector('#expensesTable tbody');
            tbody.innerHTML = '';
            expenses.forEach(e => {
                tbody.innerHTML += `<tr>
                    <td>${e.id}</td>
                    <td>${e.category}</td>
                    <td>${e.amount}</td>
                    <td>${e.date}</td>
                </tr>`;
            });
        });
}

// Delete Expense
function deleteExpense() {
    const id = document.getElementById('expenseId').value;
    fetch(`${apiUrl}/${id}?userId=${userId}`, { method: 'DELETE' })
        .then(() => {
            document.getElementById('deleteMsg').innerText = "Deleted expense ID " + id;
            fetchExpenses();
        });
}

// Category Analysis
function fetchCategory() {
    fetch(`${apiUrl}/user/${userId}/category-analysis`)
        .then(res => res.json())
        .then(categories => {
            const list = document.getElementById('categoryList');
            list.innerHTML = '';
            for (let cat in categories) {
                list.innerHTML += `<li>${cat}: ${categories[cat]}</li>`;
            }
        });
}

// Monthly Total
function fetchMonthly() {
    const monthValue = document.getElementById('monthInput').value;
    if(!monthValue) return alert("Select month");
    const [year, month] = monthValue.split('-');
    fetch(`${apiUrl}/user/${userId}/monthly?year=${year}&month=${month}`)
        .then(res => res.text())
        .then(total => document.getElementById('monthlyTotal').innerText = total);
}

// Weekly Total
function fetchWeekly() {
    fetch(`${apiUrl}/user/${userId}/weekly`)
        .then(res => res.text())
        .then(total => document.getElementById('weeklyTotal').innerText = total);
}

// Load on page ready
fetchExpenses();
fetchCategory();
fetchMonthly();
fetchWeekly();
</script>

<%@ include file="footer.jsp" %>
