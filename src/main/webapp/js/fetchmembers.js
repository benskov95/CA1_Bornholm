let getAllMembersBtn = document.getElementById("getAllMembersBtn");
getAllMembersBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    let url = 'http://localhost:8080/jpareststarter/api/groupmembers/all';
    let membersTable = document.getElementById("membersTable");
    fetch(url).then(res => res.json()).then(data => {
        let membersArray = data.map(member => `<tr><td>${member.name}</td><td>${member.studentId}</td><td>${member.favoriteSeries}</td></tr>`);
        membersTable.innerHTML = membersArray.join("");
    });
}
    fetchAllMembers();