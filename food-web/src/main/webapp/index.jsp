<!DOCTYPE html>
<html>
<head>
    <title>Food App</title>
</head>
<body>
<h1>Food Lookup</h1>

<button onclick="loadFoods()">Load Foods</button>
<pre id="output"></pre>

<script>
function loadFoods() {
    fetch('<%= request.getContextPath() %>/api/foods')
        .then(res => res.json())
        .then(data => {
            document.getElementById('output').textContent =
                JSON.stringify(data, null, 2);
        });
}
</script>

</body>
</html>