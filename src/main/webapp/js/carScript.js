

fetchallCars();
document.getElementById("btn1").addEventListener("click", filterCars);
document.getElementById("btn2").addEventListener("click", filterCars);
document.getElementById("btn3").addEventListener("click", filterCars);

function fetchallCars() {

    let url = 'http://localhost:8080/jpareststarter/api/car/all';

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                maketable(data);
                console.log("test");
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            })
}
function maketable(array) {

    let carsarray = array.map(cars => `<tr><td></td><td>${cars.id}</td><td>${cars.year}</td><td>${cars.make}</td><td>${cars.model}</td><td>${cars.price}`);


    document.getElementById("tablebody").innerHTML = carsarray.join("");
}



function filterCars() {

    let buttonPressed = document.getElementById(event.target.id);
    let input = document.getElementById("price").value;
    let url = 'http://localhost:8080/jpareststarter/api/car/all';

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                if (buttonPressed.id === "btn1") {
                    let filter = data.filter(x => x.price < input);
                    maketable(filter);
                }
                if (buttonPressed.id === "btn2") {
                    let filter = data.filter(x => x.year <= input);
                    maketable(filter);
                }
                if (buttonPressed.id === "btn3") {
                    maketable(data);
                }
//                maketable(data);
//                console.log("test");
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            })


}


