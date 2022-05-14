import { useState } from "react";

export function UserList() {
    const [users, setUsers] = useState();
    const tableHeaders = ["Id", "Imię", "Nazwisko", "Data urodzenia", "PESEL", "Adres email", "Numer telefonu", "Adres", "Numer karty kredytowej"];
    if (!users) {
        fetch("http://localhost:8080/api/user").then(response => response.json()).then(body => setUsers(body));
    }
    return (
        <main>
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
                                users.map(user => <tr key={user.id}>{Object.keys(user).map(header => <td key={header}>{user[header]}</td>)}</tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak użytkowników w bazie.</p>
            }
        </main >
    );
}