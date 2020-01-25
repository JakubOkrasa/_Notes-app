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
                document.getElementById("mod-view-title").innerHTML = json.noteTitle;
                document.getElementById("mod-view-content").innerHTML = json.noteContent;
            }
        );
}
