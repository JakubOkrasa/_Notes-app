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
