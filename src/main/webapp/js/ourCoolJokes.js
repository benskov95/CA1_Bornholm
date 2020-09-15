// REMOTE URL
//let url = "https://bencat.dk/movieproject/api/movie/";

// LOCAL URL
let url = "http://localhost:8080/jpareststarter/api/joke/";

    
let controller = document.getElementById("controller");
let table = document.getElementById("fortable");
let buttons = document.querySelectorAll("input.options");
let viewAllNoDto = false;

buttons.forEach(function(element) {
    element.addEventListener("click", fetchJokes, false);
});

function createTableHeadForAll() {
 let tableStart = "<table class=\"table\">";
 tableStart += "<tr><th>Joke</th><th>Punchline</th></tr>";
 return tableStart;
}

function toTable(fetchedJokes) {
    let tableString = createTableHeadForAll();
    let jokes = fetchedJokes.map(joke => "<tr><td>" + joke.joke + "</td><td>" + joke.punchLine + "</td></tr>");
    tableString += "</tr>" + jokes + "</table>";
    table.innerHTML = tableString;
}

function determineEndpoint(element) {
    var input = document.getElementById("input");
    
    if (element.id === "getAll") {
        return "all";
    }
    if (element.id === "getById") {
        return "by_id/" + input.value;
    }
    if (element.id === "getRandom") {
        return "random";
    }
}

function fetchJokes() {
    event.preventDefault();
    event.stopPropagation();
    var choice = document.getElementById(event.target.id);
    tempUrl = url;
    
   tempUrl += determineEndpoint(choice);
    
   fetch(tempUrl) 
  .then(res => res.json()) 
  .then(jokes => {
      toTable(jokes);
    });
}