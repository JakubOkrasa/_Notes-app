// const form  = document.getElementsByTagName('form')[0];
//
// const noteTitle = document.getElementById('note-title'); //not sure if it's right method
// // const noteTitleError = document.getElementsByName('noteTitle + span.error'); //not sure if it's right method
// const noteTitleError = document.querySelector('#note-title + span.error');
//
//
// // form.addEventListener('submit', function(e) {
// //     if(noteTitle.value.length > 15) {
// //         noteTitleError.textContent = 'title is too long.'
// //         e.preventDefault();
// //     }
// // });
//
//
// function createNoteValidate() {
//     if(noteTitle.value.length > 15) {
//         noteTitleError.textContent = 'title is too long.'
//         e.preventDefault();
//     }
// }
//
// function myFunction() {
//     const x =document.getElementById("note-title").value;
//     let text;
//
//     // If x is Not a Number or less than one or greater than 10
//     if (x.length>15) {
//         text = "Input too long";
//         e.preventDefault();
//
//     } else {
//         text = "Input OK";
//     }
//     document.getElementById("note-title-err").innerHTML = text;
// }