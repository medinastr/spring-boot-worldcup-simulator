const formulario = document.querySelector("#nationForm");
const nationInputs = document.querySelectorAll(".nation");

function record(nationData) {
    fetch("http://localhost:8080/nations", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(nationData)
    })
    .then(function (res) {
        console.log(res);
    })
    .catch(function (res) {
        console.log(res);
    });
}

function clean() {
    nationInputs.forEach(input => input.value = "");
}

formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    nationInputs.forEach(input => {
        if (input.value.trim() !== "") {
            const nationData = {
                nationName: input.value,
                wins: 0,
                goals: 0,
                goalsConceded: 0
            };

            console.log(nationData);
            record(nationData);
        }
    });

    clean();
});
