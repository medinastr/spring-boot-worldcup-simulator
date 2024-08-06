const formulario = document.querySelector("form")
const Ination = document.querySelector(".nation")

function record() {
    fetch("http://localhost:8080/record",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            method: "POST",
            body: JSON.stringify({
                nation: Ination.value,
                wins: 0,
                goals: 0,
                goalsConceded: 0
            })
        })
        .then(function (res) { console.log(res)})
        .catch(function (res) { console.log(res)})
};

function clean() {
    Ination.value = "";
}

formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    const nationData = {
        nation: Ination.value,
        wins: 0,
        goals: 0,
        goalsConceded: 0
    };

    record();
    clean();
});