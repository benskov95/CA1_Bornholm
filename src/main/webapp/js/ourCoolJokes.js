// REMOTE URL
//let url = "https://bencat.dk/movieproject/api/movie/";

// LOCAL URL
let url = "http://localhost:8080/jpareststarter/api/joke/";

    
let table = document.getElementById("fortable");
let jokeP = document.getElementById("joke");
let buttons = document.querySelectorAll("input.options");
let singleJoke = false;

buttons.forEach(function(element) {
    element.addEventListener("click", fetchJokes);
});

function createTableHeads() {
 let tableStart = "<table class=\"table\">";
 tableStart += "<tr><th>Joke</th><th>Punchline</th></tr>";
 return tableStart;
}

function toTable(fetchedJokes) {
    let tableString = createTableHeads();
    fetchedJokes.forEach(joke =>
    tableString += "<tr><td>" + joke.joke
            + "</td><td>" + joke.punchLine + "</td></tr>");
    tableString += "</table>";
    table.innerHTML = tableString;
    jokeP.innerHTML = "";
}

function toParagraph(fetchedJoke) {
    jokeP.innerHTML = fetchedJoke.joke + "<br>" + fetchedJoke.punchLine;
    table.innerHTML = "";
}

function determineEndpoint(element) {
    let input = document.getElementById("input");
    
    if (element.id === "getAll") {
        return "all";
    }
    if (element.id === "getByID") {
        singleJoke = true;
        return "by_id/" + input.value;
    }
    if (element.id === "getRandom") {
        singleJoke = true;
        return "random";
    }
}

function fetchJokes() {
    event.preventDefault();
    event.stopPropagation();
    let choice = document.getElementById(event.target.id);
    tempUrl = url;
    
   tempUrl += determineEndpoint(choice);
    
   fetch(tempUrl) 
  .then(res => res.json()) 
  .then(jokes => {
      if (singleJoke) {
          toParagraph(jokes);
          singleJoke = false;
      } else {
      toTable(jokes);
  }
    });
}

function loadPage() {
    fetch(url + "all") 
  .then(res => res.json()) 
  .then(jokes => {
      toTable(jokes);
    });
}

loadPage();