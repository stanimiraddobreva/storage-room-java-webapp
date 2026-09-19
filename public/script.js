fetch('http://localhost:8000/products')
    .then(response => response.json())
    .then(data => {
        const tbody = document.querySelector('#productTable tbody');
        data.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.name}</td>
                <td>${product.expiryDate}</td>
                <td>${product.manufacturer}</td>
                <td>${product.quantity}</td>
                <td>${product.unit}</td>
                <td>${product.category}</td>
                <td>${product.storageFee.toFixed(2)} лв.</td>
            `;
            tbody.appendChild(row);
        });
    });