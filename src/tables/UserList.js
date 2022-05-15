import { useState } from "react";

function filterUsers(users, filters) {
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
    let newUsers = users.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newUsers = newUsers.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newUsers;
}

export function UserList() {
    const tableHeaders = ["Id", "Imię", "Nazwisko", "Data urodzenia", "PESEL", "Adres email", "Numer telefonu", "Adres", "Numer karty kredytowej"];

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
        <main style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
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
                users ?
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
                    <p>Brak użytkowników w bazie.</p>
            }
        </main >
    );
}