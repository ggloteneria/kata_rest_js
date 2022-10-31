const formDelete = document.getElementById('delete_form');
formDelete.onsubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData(formDelete);
    let response = await fetch('/api/delete/{id}'+ formData.get("id"), {
        method: 'DELETE',
    })

    $("#modalDelete").modal("hide");
};


const formEdit = document.getElementById('edit_form');
formEdit.onsubmit = async (e) => {
    e.preventDefault();

    let formData = new FormData(formEdit);

    let response = await fetch('/api/edit/{id}"'+ formData.get("id"), {
        method: 'PUT',
        body: formData,
    });

    $('#modalEdit').modal('hide');
};

const newUserForm = document.getElementById('create_form');
newUserForm.onsubmit = async (e) => {
    e.preventDefault();

    let response = await fetch('/api/create_user', {
        method: 'POST',
        body: new FormData(newUserForm)
    });

    let result = await response.json();

};


fetch("http://localhost:8080/api/users").then((data)=>{
    //получаем json и отдаем его объект
    return data.json();
}).then((objJson) => {
    let tableData = "";
    //получаем из общего списка пользователей
    objJson.map((objUser) => {
        //добавляем в таблицу каждого пользователя
        tableData += `<tr>
        <td>${objUser.id}</td>
        <td>${objUser.name}</td>
        <td>${objUser.lastName}</td>
        <td>${objUser.age}</td>
        <td>${objUser.email}</td>
        <td>${objUser.username}</td>
        <td>${objUser.roles.map((objRole) => objRole.role)}</td>

        <td>
            <button type="button" class="btn btn-info text-white js-open-modal"
                data-toggle="modal" data-target="#modalEdit"
                data-bs-userId=${objUser.id}
                data-bs-userFirstName=${objUser.name}
                data-bs-userLastName=${objUser.lastName}
                data-bs-userAge=${objUser.age}
                data-bs-userEmail=${objUser.email}
                data-bs-userUsername=${objUser.username}
                data-bs-toggle="modalEdit"
                data-bs-target="#ModalEdit">Edit</button>
        </td>

        <td>
            <button type="button" class="btn btn-danger js-open-modal"
                data-toggle="modal" data-target="#modalDelete"
                data-bs-userId=${objUser.id}
                data-bs-userFirstName=${objUser.name}
                data-bs-userLastName=${objUser.lastName}
                data-bs-userAge=${objUser.age}
                data-bs-userEmail=${objUser.email}
                data-bs-userUsername=${objUser.username}
                data-bs-toggle="modalDelete"
                data-bs-target="#ModalDelete">Delete</button>
        </td>
        </tr>`;
    });
    document.getElementById("table_body").
        innerHTML = tableData;

    buttonWork();
}).catch((err) => {
    console.log(err);
})

function buttonWork() {

    let linkArray = document.querySelectorAll('.js-open-modal');

//обработка клика для модального окна
    linkArray.forEach(function (button) {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            let typeForm = button.getAttribute("data-bs-toggle")

            let form = document.getElementById(typeForm);
            //получение данных из кнопки
            const buttonUserId = button.getAttribute('data-bs-userId')
            const buttonUserFirstName = button.getAttribute('data-bs-userFirstName')
            const buttonUserLastName = button.getAttribute('data-bs-userLastName')
            const buttonUserAge = button.getAttribute('data-bs-userAge')
            const buttonUserEmail = button.getAttribute('data-bs-userEmail')
            const buttonUserUsername = button.getAttribute('data-bs-userUsername')


            //получение переменных из формы
            let modalUserId;
            let modalUserFirstName;
            let modalUserLastName;
            let modalUserAge;
            let modalUserEmail;
            let modalUserUsername;

            if (typeForm === "modalEdit"){
                modalUserId = form.querySelector('#id')
                modalUserFirstName = form.querySelector('#firstName')
                modalUserLastName = form.querySelector('#lastName')
                modalUserAge = form.querySelector('#age')
                modalUserEmail = form.querySelector('#email')
                modalUserUsername = form.querySelector('#username')
            } else {
                // modalUserId = form.querySelector('#id')
                modalUserFirstName = form.querySelector('#firstName0')
                modalUserLastName = form.querySelector('#lastName0')
                modalUserAge = form.querySelector('#age0')
                modalUserEmail = form.querySelector('#email0')
                modalUserUsername = form.querySelector('#username0')
            }

            //сохранение данных в переменные
            modalUserId.value = buttonUserId;
            modalUserFirstName.value = buttonUserFirstName;
            modalUserLastName.value = buttonUserLastName;
            modalUserAge.value = buttonUserAge;
            modalUserEmail.value = buttonUserEmail;
            modalUserUsername.value = buttonUserUsername;
        });
    });
}


