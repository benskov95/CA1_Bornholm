

var cararray = [];
fetchallCars();
document.getElementById("btn1").addEventListener("click", filterCars);
document.getElementById("btn2").addEventListener("click", filterCars);
document.getElementById("btn3").addEventListener("click", filterCars);
document.getElementById("btn4").addEventListener("click", filterCars);
document.getElementById("sort").addEventListener("click", sortBy);
function fetchallCars() {

    let url = 'http://localhost:8080/jpareststarter/api/car/all';

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                maketable(data);
                cararray = [...data];

                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
}
function maketable(array) {

    let carsarray = array.map(cars => `<tr><td></td><td>${cars.id}</td><td>${cars.year}</td><td>${cars.make}</td><td>${cars.model}</td><td>${cars.price}</tr>`);
    document.getElementById("tablebody").innerHTML = carsarray.join("");
}
function sortBy() {

    let buttonpressed = event.target.innerText;
    switch (buttonpressed) {
        case "year":
            let yearArray = cararray.sort(function (a, b) {
                return a.year - b.year;
            });
            maketable(yearArray);
            break;

        case "make":
            let makeArray = cararray.sort(function (a, b) {
                if (a.make < b.make) {
                    return -1;
                }
                if (a.make > b.make) {
                    return 1;
                }
                return 0;
            });
            maketable(makeArray);
            break;
            
            case "model":
            let modelArray = cararray.sort(function (a, b) {
                if (a.model < b.model) {
                    return -1;
                }
                if (a.model > b.model) {
                    return 1;
                }
                return 0;
            });
            maketable(modelArray);
            break;
            
        case "price":
            let priceArray = cararray.sort(function (a, b) {
                return a.price - b.price;
            });
            maketable(priceArray);
            break;

        default:
            break;
    }

}


function filterCars() {

    let buttonPressed = document.getElementById(event.target.id);
    console.log(buttonPressed)
    let input = document.getElementById("price").value;
    let url = 'http://localhost:8080/jpareststarter/api/car/all';

    switch (buttonPressed.id) {
        case "btn1":
            let filterPrice = cararray.filter(x => x.price < input);
            cararray = filterPrice;
            maketable(filterPrice);
            break;


        case "btn2":
            let filterYear = cararray.filter(x => x.year <= input);
            cararray = filterYear;
            maketable(filterYear);
            break;

        case "btn3":
            let filterMake = cararray.filter(x => x.make.toLowerCase() === input.toLowerCase());
            cararray = filterMake;
            maketable(filterMake);
            break;

        case "btn4":
            fetchallCars();
            break;

        default:
            break;


    }


}





