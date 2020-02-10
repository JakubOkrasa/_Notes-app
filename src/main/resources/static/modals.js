//TODO code review Karola

let note = {
    noteTitle: "",
    noteContent: "",
    set title(title) {
        this.noteTitle = title;
    },
    set content(content) {
        this.noteContent = content;
    }
};

//TODO let vs var vs const. Is let the best choice here?


function viewNote(id) {
    const response = fetch('view-modal?id=' + id, {
        method: 'GET',
        cache: 'no-cache',
        credentials: 'same-origin',
        referrerPolicy: 'no-referrer',
    });
    response
        .then((r) => r.json())
        .then((json) => {
                console.log(json);
                note.title = json.noteTitle;
                note.content = json.noteContent;
                document.getElementById("mod-title").innerHTML = json.noteTitle;
                document.getElementById("mod-content").innerHTML = json.noteContent;

            }
        );

}

function editNote() {
    console.log("editNote JS function call");
    document.getElementById("mod-title").innerHTML = "<input type='text' value='" + note.noteTitle + "'>";
    let content_textarea = document.createElement("TEXTAREA");
    content_textarea.value = note.noteContent;
    document.getElementById("mod-content").innerHTML="";
    document.getElementById("mod-content").insertAdjacentElement('afterbegin', content_textarea);
    document.getElementById("mod-btn").innerHTML = "<button class=\"btn btn-info\" onclick=\"updateNote()\" type=\"button\">Save</button>";
}

function updateNote() {
    console.log("updateNote JS function call");

}

//back to note look from before edit
function closeNote() {
    document.getElementById("mod-title").innerHTML = note.noteTitle;
    document.getElementById("mod-content").innerHTML = note.noteContent;
    document.getElementById("mod-btn").innerHTML = "<button class=\"btn btn-info\" onclick=\"editNote()\" type=\"button\">Edit</button>";
}
