let HOST = "http://localhost:8080";
let map = new Map();
let json;

getJson().then(createMap);


async function getJson() {
    let url = HOST + "/currencyValues";
    let response = await fetch(url);
    if (response.ok) {
        json = await response.json();
    } else {
        alert("HTTP mistake: " + response.status);
    }
}

function createMap() {
    JSON.parse(JSON.stringify(json), function (key, value) {
        map.set(key, value);
    });
}

function createResult() {
    let amount = document.getElementsByName("original_amount")[0].value;
    let firstCurName = document.getElementById("firstCurName").value;
    let secondCurName = document.getElementById("secondCurName").value;
    if (firstCurName === "" || secondCurName === "") return;

    let result = map.get(firstCurName) / map.get(secondCurName) * amount;
    let resultField = document.getElementsByName("received_amount")[0];
    resultField.value = result;
    console.log(result);
}






