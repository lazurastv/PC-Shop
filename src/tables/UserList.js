import { useState } from "react";

const tableHeaders = ["Id", "Imię", "Nazwisko", "Data urodzenia", "PESEL", "Adres email", "Numer telefonu", "Adres", "Numer karty kredytowej"];
const headerMapping = {
    "Id": 'id',
    "Imię": 'name',
    "Nazwisko": 'lastName',
    "Data urodzenia": 'birthDate',
    "PESEL": 'PESEL',
    "Adres email": 'email',
    "Numer telefonu": 'phoneNumber',
    "Adres": 'address',
    "Numer karty kredytowej": 'creditCardNumber'
};

function filterUsers(users, filters) {
    let newUsers = users.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newUsers = newUsers.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newUsers;
}

function generateXML(users) {
    let xml = '<?xml version="1.0" encoding="UTF-8"?>\n';
    xml += "<users>\n";
    for (const user of users) {
        xml += "<user>\n";
        for (const [key, value] of Object.entries(user)) {
            if (key === "birthDate") {
                xml += `\t<${key}>${value.slice(0, 10)}</${key}>\n`;
            } else {
                xml += `\t<${key}>${value}</${key}>\n`;
            }
        }
        xml += "</user>\n";
    }
    xml += "</users>\n";
    return xml;
}

function download(users) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/xml;charset=utf-8,' + encodeURIComponent(generateXML(users)));
    element.setAttribute('download', "użytkownicy_" + Date.now() + ".xml");

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

function upload(file) {
    const fileData = file.target.files[0];
    const reader = new FileReader();
    let content = "";
    reader.addEventListener('load', event => { content = event.target.result; console.log(content); });
    reader.readAsText(fileData);
}

export function UserList() {
    const [users, setUsers] = useState();
    const [filteredUsers, setFilteredUsers] = useState();
    const [filters, setFilters] = useState({});

    if (!users) {
        fetch("http://localhost:8080/api/user").then(response => response.json()).then(body => {
            const tmp = body.map(user => { return { ...user, birthDate: user["birthDate"].slice(0, 10) } });
            setUsers(tmp);
            setFilteredUsers(tmp);
        })
    }
    return (
        <main style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: "20px" }}>
            <div style={{ display: "flex", gap: "10px" }}>
                <button onClick={() => download(filteredUsers)}>Pobierz raport</button>
                <input id="selectedFile" style={{ display: 'none' }} type="file" accept="text/xml" onChange={(value) => upload(value)}></input>
                <button onClick={() => document.getElementById('selectedFile').click()}>Wczytaj z pliku</button>
            </div>
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                {
                    tableHeaders.map(header =>
                        <div key={header}>
                            <label htmlFor={header} style={{ marginRight: "10px" }}>{header}</label>
                            <input id={header} onChange={value => {
                                const newFilters = { ...filters };
                                newFilters[header] = value.target.value;
                                setFilters(newFilters);
                                setFilteredUsers(filterUsers(users, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredUsers?.length > 0 ?
                    <table>
                        <thead>
                            <tr>
                                {
                                    tableHeaders.map(header => <th key={header}>{header}</th>)
                                }
                            </tr>
                        </thead>
                        <tbody>
                            {
                                filteredUsers.map(user =>
                                    <tr key={user.id}>
                                        {Object.keys(user).map(header =>
                                            <td key={header}>{user[header]}</td>
                                        )}
                                    </tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak wyników.</p>
            }
        </main >
    );
}