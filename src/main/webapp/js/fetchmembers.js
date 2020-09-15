let getAllMembersBtn = document.getElementById(getAllMembersBtn);
getAllMembersBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    let url = 'https://bencat.dk/CA1_Bornholm/api/groupmembers/all';
    let allMembers = document.getElementById("allMembers");
    fetch(url).then(res => res.json()).then(data => {
        let membersArray = data.map(member => `<tr><td>${member.name}</td><td>${member.studentId}</td><td>${member.favoriteSeries}</td></tr>`);
        allMembers.innerHTML = `<table><thead><th>Names</th><th>Student ID</th><th>Favorite TV Series</th></thead>${membersArray.join("")}</table>`
    });
}